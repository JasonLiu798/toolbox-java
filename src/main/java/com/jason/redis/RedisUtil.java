package com.jason.redis;

import com.jason.common.RandomGenerator;
import com.jason.entity.ResCostTime;
import com.jason.entity.ResCostTimeCalc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * @author JianLong
 * @date 2014/11/6 9:10
 */
public class RedisUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);
    public Jedis jedis = null;
    /**
     * redis-cli -h 192.168.143.112 -p 6379
     */
    public static String ip = "192.168.143.112";
    public static int port = 6379;
    public static String[] key = {"TA", "TB", "TC"};
    //public static String keyB = "B";
    //public static String keyC = "C";

    public static String keyU = "TUnion";
    public static String keyManual = "TManual";

    public static String keyUnionTime = "keyUnionTime";
    public static String keyTotalTime = "keyTotalTime";
    public static String keyGet = "get";
    public static String keyCalc = "calc";
    public static String keyPut = "put";

    private static final int DESC = 0;
    private static final int ASC = 1;
    private static final int T_MAX = 0;
    private static final int T_MIN = 1;
    private static final int KEEP_DUP = 0;
    private static final int DEL_DUP = 0;


    //public static int total = 200000;


    public void setup() {
        jedis = new Jedis(ip, port);
        //权限认证
        //jedis.auth("admin");
    }


    /**
     * A 10W 随机数
     * B 10W 随机数
     * C 10W 随机数
     * Z = A~C的并集
     */
    public void zunionJavaSortPerformanceCompare() {
        Set<Tuple> tups = jedis.zrevrangeWithScores("aa", 0, 100);
        for (Tuple tup : tups) {
            tup.getElement();
            tup.getScore();
        }
    }

    public void addData(String key, int count) {
        long t = System.currentTimeMillis();
        int total = count * 2;
        for (int i = 0; i < total; i++) {
            int number = RandomGenerator.generateNum(total);
            jedis.zadd(key, number, String.valueOf(number));
        }
        long acount = jedis.zcard(key);
        long t1 = System.currentTimeMillis() - t;
        System.out.println(key + " count " + acount + " cost " + t1 + " ms");
    }

    public void initData(int count) {
        for (int i = 0; i < key.length; i++) {
            jedis.del(key[i]);
            addData(key[i], count);
        }
    }

    public HashMap<String, Long> getCount() {
        HashMap<String, Long> hm = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            hm.put(key[i], jedis.zcard(key[i]));
        }
        return hm;
    }

    /**
     * zunionstore
     * @param start
     * @param end
     * @return
     */
    public ResCostTime<List<String>> zunion(int start, int end) {
        //zinterstore(final String dstkey, final ZParams params, final String... sets);
        long t1 = System.currentTimeMillis();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MIN);
        jedis.del(keyU);
        this.zunionstoreManual(keyU, key[0], key[1], key[2]);
        //jedis.zunionstore(keyU, zp, key[0], key[1], key[2]);
        long ucount = jedis.zcard(keyU);
        System.out.println("ucount " + ucount);
        Set<String> set = jedis.zrevrange(keyU, start, end);
        //jedis.zr
        List<String> res = new LinkedList<>();

        for (String s : set) {
            res.add(s);
        }
        long unionTime = System.currentTimeMillis() - t1;
        HashMap<String, Long> times = new HashMap<>();
        times.put(keyUnionTime, unionTime);
        return new ResCostTime<>(res, times);
    }


    /**
     * 查看是否存在合并结果，存在则获取，不存在则合并
     * @param start
     * @param end
     * @return
     */
    public ResCostTime<List<String>> mergeAndGet(int start,int end){
        ResCostTime<List<String>> rct = new ResCostTime<>();
        if (jedis.exists(keyManual)) {
            long t0 = System.currentTimeMillis();
            long total = jedis.zcard(keyManual);
            List<String> res = null;
            if (total < end) {
                Set<String> sets = jedis.zrevrange(keyManual, start, end);
                res = this.set2List(sets);
            }
            rct.setData(res);
            long t1 = System.currentTimeMillis() - t0;

            HashMap<String, Long> times = new HashMap<>();
            times.put(keyCalc, t1);
            times.put(keyGet, t1);
            rct.setTime(times);
        } else {
            rct = zmanualUnion( start, end);
        }
        return rct;
    }

    /**
     * 手动合并多个zset至target zset
     * @param start
     * @param end
     * @return
     */
    public ResCostTime<List<String>> zmanualUnion(int start,int end){
        long t0 = System.currentTimeMillis();
        long count = Math.max(Math.max(jedis.zcard(key[0]), jedis.zcard(key[1])), jedis.zcard(key[2]));
        this.mergeSortedList(count, keyManual, DESC, DEL_DUP, key[0], key[1], key[2]);
        long t1 = System.currentTimeMillis();
        long tManualMerge = t1-t0;


        Set<String> sets = jedis.zrevrange(keyManual, start, end);
        List<String> res = set2List(sets);
        long t2 = System.currentTimeMillis();
        long tZrev = t2-t1;

        HashMap<String,Long> hm = new HashMap<>();
        hm.put(keyCalc,tManualMerge);
        hm.put(keyGet,tZrev);
        return new ResCostTime<>(res,hm);
    }

    /**
     * set转为list
     * @param set
     * @param <T>
     * @return
     */
    private<T> List<T> set2List(Set<T> set){
        List<T> list = new ArrayList<>(set.size());
        list.addAll(set);
        return list;
    }

    /**
     * 取各有序集count个数据，合并至targetKey
     *
     * @param count
     * @param keys
     */
    public void mergeSortedList(long count, String tgtKey, int order, int keepDuplicate, String... keys) {
        int totalCount = keys.length;
        List<List<Tuple>> tmp = new ArrayList<>(totalCount);
        for (String key : keys) {
            long listCount = 0;
            if (jedis.exists(key)) {
                listCount = jedis.zcard(key);
            } else {
                LOGGER.warn("mergesortedlist " + key + " not exist!");
                continue;
            }
            List<Tuple> tuples = new ArrayList<>();
            tuples.addAll(jedis.zrevrangeWithScores(key, 0, count - 1));
            tmp.add(tuples);
        }
        //List<String> res = new LinkedList<>();
        List<Tuple> tmpMergeList = new LinkedList<>();
        Tuple pre = null;
        boolean gotFirst = false;
        while (chekListsSize(tmp)) {
            Tuple tuple = null;
            if (order == DESC) {
                tuple = getListsMaxHead(tmp);
            } else if (order == ASC) {
                tuple = getListsMinHead(tmp);
            }
            if (tuple != null) {
                if (gotFirst && keepDuplicate==DEL_DUP) {
                    if (pre.getElement().equals(tuple.getElement())) {//内容相同的t直接略过不处理，pre不变
                        continue;
                    }
                }
                if (!gotFirst) {
                    gotFirst = true;
                }
                if (keepDuplicate==DEL_DUP) {
                    pre = tuple;
                }
                tmpMergeList.add(tuple);
            }
        }
        HashMap<String,Double> hm = listTuple2HashMap(tmpMergeList);
        jedis.del(tgtKey);
        jedis.zadd(tgtKey, hm);
    }

    /**
     * list tuple change to hashmap for zadd, batch operation
     * @param tuples
     * @return
     */
    public HashMap<String,Double> listTuple2HashMap(List<Tuple> tuples){
        HashMap<String, Double> hm = new HashMap();
        for (Tuple t:tuples) {
            hm.put(t.getElement(), t.getScore());
        }
        return hm;
    }

    /**
     * at least one list in lists size()>0
     * @param lists
     * @param <T>
     * @return
     */
    private <T> boolean chekListsSize(List<List<T>> lists) {
        boolean res = false;
        for (List<T> list : lists) {
            if (list.size() > 0) {
                res = true;
                break;
            }
        }
        return res;
    }




    /**
     * 有序集合并
     *
     * @return
     *
    public ResCostTime<List<String>> manualSortAdd(int start, int end, boolean keepDuplicate) {

        long ucount = jedis.zcard(keyU);
        long getT = System.currentTimeMillis();

        List<Tuple> la = new ArrayList<>();
        System.out.println("size " + la.size() + ",list " + la);
        la.addAll(jedis.zrevrangeWithScores(key[0], 0, end));
        List<Tuple> lb = new ArrayList<>();
        lb.addAll(jedis.zrevrangeWithScores(key[1], 0, end));
        List<Tuple> lc = new ArrayList<>();
        lc.addAll(jedis.zrevrangeWithScores(key[2], 0, end));

        long getTtotal = System.currentTimeMillis() - getT;

        List<String> res = new LinkedList<>();
        List<Tuple> tmp = new LinkedList<>();

        //合并有序列
        boolean aend = false;
        boolean bend = false;
        boolean cend = false;
        long calct1 = System.currentTimeMillis();
        Tuple pre = null;
        boolean gotFirst = false;
        while (la.size() > 0 || lb.size() > 0 || lc.size() > 0) {
            /** 判断其他数组是否已经加完 **
            if (la.size() == 0) {
                aend = true;
            }
            if (lb.size() == 0) {
                bend = true;
            }
            if (lc.size() == 0) {
                cend = true;
            }
            /** a b 已经加完， c加入剩下全部 **
            if (aend && bend) {
                tmp.addAll(lc);
                break;
            }
            /** b c 已经加完，a加入剩下全部 **
            if (bend && cend) {
                tmp.addAll(la);
                break;
            }
            /** a c 已经加完，b加入剩下全部 **
            if (cend && aend) {
                tmp.addAll(lb);
                break;
            }
            Tuple t = null;
            //t = this.getListsMaxHead(la, lb, lc);
            if (t != null) {
                if (gotFirst && !keepDuplicate) {
                    if (pre.getElement().equals(t.getElement())) {//内容相同的t直接略过不处理，pre不变
                        continue;
                    }
                }
                if (!gotFirst) {
                    gotFirst = true;
                }
                if (!keepDuplicate) {
                    pre = t;
                }
                tmp.add(t);
            }
        }
        long calct = System.currentTimeMillis() - calct1;

        long putt1 = System.currentTimeMillis();
        HashMap<String, Double> hm = new HashMap();
        for (int i = 0; i < tmp.size(); i++) {
            Tuple t = tmp.get(i);
            hm.put(t.getElement(), t.getScore());
        }
        end = end + 1;
        if (end >= tmp.size()) {
            end = tmp.size();
        }
        for (int i = start; i < end; i++) {
            res.add(tmp.get(i).getElement());
        }

        //put to redis
        jedis.del(keyManual);
        jedis.zadd(keyManual, hm);

        long putt = System.currentTimeMillis() - putt1;
        HashMap<String, Long> times = new HashMap<>();
        times.put(keyGet, getTtotal);
        times.put(keyCalc, calct);
        times.put(keyPut, putt);
        return new ResCostTime<>(res, times);
    }
    */

    public List testSet2ListIsSorted() {
        Set<Tuple> res = jedis.zrangeWithScores(key[0], 0, 1000);
        List<Tuple> l = new ArrayList<>(res.size());
        l.addAll(res);
        return l;
    }

    /**
     * 获取各list头元素最大的一个，并从list中删除
     * @param list
     * @return
     */
    private Tuple getListsMaxHead(List<List<Tuple>> list) {
        return getListsHead(T_MAX, list);
    }

    /**
     * 获取各list头元素最小的一个，并从list中删除
     * @param list
     * @return
     */
    private Tuple getListsMinHead(List<List<Tuple>> list) {
        return getListsHead(T_MIN, list);
    }

    /**
     * 获取 lists 头结点中最小的一个，并从list删除此结点
     * lists为空返回null
     * @param lists
     * @return
     */
    private Tuple getListsHead(int type, List<List<Tuple>> lists) {
        if (lists.size() <= 0) {
            return null;
        }
        Tuple tgtTuple = null;
        int idx = 0;
        for (int i = 0; i < lists.size(); i++) {
            List<Tuple> l = lists.get(i);
            if (l.size() > 0) {
                Tuple t = l.get(0);
                if (tgtTuple == null) {
                    //不存在tgt
                    tgtTuple = t;
                    idx = i;
                } else {
                    //tgt已经存在
                    if (type == T_MAX && t.compareTo(tgtTuple) < 0) {//取更小的
                        //System.out.println("T "+t+",Min "+minTuple);
                        tgtTuple = t;
                        idx = i;
                    } else if (type == T_MIN && t.compareTo(tgtTuple) > 0) {//取更大的
                        tgtTuple = t;
                        idx = i;
                    }
                }
            }
        }
        List<Tuple> tgtList = null;
        if (tgtTuple != null) {
            tgtList = lists.get(idx);
            tgtList.remove(0);
        }
        return tgtTuple;
    }


    public void testKeyFormat(String id, String value) {
        String key = "user:{%s}:ids";
        String nk = String.format(key, id);
        jedis.zadd(nk, Integer.parseInt(value), value);
    }


    public void zunionstoreManual(String dest,String ...keys){
        Set<Tuple> total = new HashSet<>();
        for(String key:keys){
            Set<Tuple> tmp = jedis.zrevrangeWithScores(key,0,jedis.zcard(key));
            total.addAll(tmp);
        }
        HashMap<String,Double> hm = set2HashMap(total);
        jedis.zadd(dest,hm);
    }

    private HashMap<String,Double> set2HashMap(Set<Tuple> set){
        HashMap<String,Double> hm = new HashMap<>();
        if(set==null||set.size()==0){
            return hm;
        }
        for(Tuple item:set){
            hm.put(item.getElement(), item.getScore());
        }
        return hm;
    }

    public void testCase(int type) {
        int testTimes = 10;
        int start = 900;
        int end = 950;
        List<ResCostTime> manualTimeStore = new LinkedList<>();
        for (int i = 0; i < testTimes; i++) {
            System.out.println((i + 1) + "'s execute");
            long t1 = System.currentTimeMillis();
            ResCostTime rct = null;

            if (type == 0) {
                rct = this.zunion(start, end);
                System.out.println(rct.getData());
            } else if (type == 1) {
                //rct = this.manualSortAdd(start, end, false);
                rct = this.zmanualUnion(start,end);//this.mergeAndGet(start,end);
                System.out.println(rct.getData());
            }
            long t = System.currentTimeMillis() - t1;
//            HashMap<String,Long> hm = new HashMap<>();
//            hm.put(keyTotalTime,t);
            HashMap hm = rct.getTime();
            hm.put(keyTotalTime, t);
            rct.setTime(hm);
            manualTimeStore.add(rct);
            //ResCostTime rct = new ResCostTime(null,hm);
            //manualTimeStore.add(rct);
            //System.out.println("time cost " + t);
        }
        //System.out.println(manualTimeStore);

        ResCostTimeCalc rc = new ResCostTimeCalc(manualTimeStore);
        if (type == 1) {
            System.out.println(rc.getFormatAllTime(keyGet));
            System.out.println(rc.getFormatAllTime(keyCalc));
            //System.out.println(rc.getFormatAllTime(keyPut));
            System.out.println(rc.getFormatAllTime(keyTotalTime));
        } else if (type == 0) {
            System.out.println(rc.getFormatAllTime(keyUnionTime));
            System.out.println(rc.getFormatAllTime(keyTotalTime));
        }
    }

    public void testExpireTime() throws InterruptedException {

        String key = "aa";
        int time = 200;
        jedis.del(key);
        jedis.setex(key, time,key);
        //jedis.zadd(key, 100, "asdfasdf");
//        jedis.expire(key, 200);
        System.out.println("time " + jedis.ttl(key));
        Thread.sleep(3000);
        System.out.println("after 3s " + jedis.ttl(key));
        jedis.set(key,"sdfsdf");
//        jedis.zadd(key, 200, "sdfdsf");
        System.out.println("after 3s " + jedis.ttl(key));
        Thread.sleep(3000);
        System.out.println("after 6s " + jedis.ttl(key));

    }


    public static void main(String[] args) {

        RedisUtil r = new RedisUtil();
        r.setup();
        try {
            r.testExpireTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        for(int i=0;i<10;i++) {
            r.testKeyFormat("123", String.valueOf(i));
        }*/

        //r.initData(1000);

//        r.testCase(0);
//        System.out.println("--------------------------");
//        r.testCase(1);

    }


}
