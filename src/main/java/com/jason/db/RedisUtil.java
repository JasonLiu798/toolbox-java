package com.jason.db;

import com.jason.common.RandomGenerator;
import com.jason.entity.ResCostTime;
import com.jason.entity.ResCostTimeCalc;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import java.util.*;

/**
 * @author JianLong
 * @date 2015/11/6 9:10
 */
public class RedisUtil {

    public Jedis jedis = null;
    /**
     * redis-cli -h 192.168.143.112 -p 6379
     */
    public static String ip = "192.168.143.112";
    public static int port = 6379;
    public static String[] key = {"TA","TB","TC"};
    //public static String keyB = "B";
    //public static String keyC = "C";

    public static String keyU = "TUnion";
    public static String keyManual = "TManual";

    public static String keyUnionTime = "keyUnionTime";
    public static String keyTotalTime = "keyTotalTime";
    public static String keyGet = "get";
    public static String keyCalc = "calc";
    public static String keyPut = "put";


    //public static int total = 200000;


    public void setup() {
        jedis = new Jedis( ip , port);
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

    public void addData(String key,int count){
        int total = count*2;
        for(int i=0;i<total;i++) {
            int number = RandomGenerator.generateNum(total);
            jedis.zadd(key, number,String.valueOf(number) );
        }
        long acount = jedis.zcard(key);
        System.out.println(key+" count "+acount);
    }

    public void initData(int count){
        for(int i=0;i<key.length;i++) {
            addData(key[i],count);
        }
    }

    public HashMap<String,Long> getCount(){
        HashMap<String, Long> hm = new HashMap<>();
        for(int i=0;i<key.length;i++) {
            hm.put(key[i], jedis.zcard(key[i]));
        }
        return hm;
    }

    public ResCostTime<List<String>> zunion(){
        //zinterstore(final String dstkey, final ZParams params, final String... sets);
        long t1 = System.currentTimeMillis();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MIN);
        jedis.del(keyU);
        jedis.zunionstore(keyU, zp, key[0], key[1], key[2]);
        long ucount = jedis.zcard(keyU);
        System.out.println("ucount "+ucount);
        Set<String> set = jedis.zrange(keyU, 1, ucount / 100);
        //jedis.zr
        List<String> res = new LinkedList<>();

        for(String s:set){
            res.add(s);
        }
        long unionTime = System.currentTimeMillis() - t1;
        HashMap<String,Long> times  = new HashMap<>();
        times.put(keyUnionTime, unionTime);
        return new ResCostTime<> (null, times);
    }

    public List testSet2ListIsSorted(){
        Set<Tuple> res = jedis.zrangeWithScores(key[0], 0, 1000);
        List<Tuple> l = new ArrayList<>(res.size());
        l.addAll(res);

        return l;
    }

    public ResCostTime<List<String>> manualSortAdd(){
        long ucount = jedis.zcard(keyU);
        long getT = System.currentTimeMillis();
        List<Tuple> la = new ArrayList<>();
        la.addAll(jedis.zrangeWithScores(key[0], 0, ucount / 3));
        List<Tuple> lb = new ArrayList<>();
        lb.addAll(jedis.zrangeWithScores(key[1], 0, ucount / 3));
        List<Tuple> lc = new ArrayList<>();
        lc.addAll(jedis.zrangeWithScores(key[2], 0, ucount / 3));
        long getTtotal = System.currentTimeMillis() - getT;

        List<String> res = new LinkedList<>();
        List<Tuple> tmp = new LinkedList<>();

        //合并有序列
        boolean aend = false;
        boolean bend = false;
        boolean cend = false;
        long calct1 = System.currentTimeMillis();
        while(la.size()>0||lb.size()>0||lc.size()>0){
            if(la.size()==0) {
                aend = true;
            }
            if(lb.size()==0) {
                bend = true;
            }
            if(lc.size() == 0 ) {
                cend = true;
            }
            /** a b 已经加完， c加入剩下全部 **/
            if(aend && bend){
                tmp.addAll(lc);
                break;
            }
            /** b c 已经加完，a加入剩下全部 **/
            if( bend&& cend){
                tmp.addAll(la);
                break;
            }
            /** a c 已经加完，b加入剩下全部 **/
            if(cend && aend){
                tmp.addAll(lb);
                break;
            }
            Tuple t = null;
            t = getMin(la,lb,lc);
            if(t!=null) {
                tmp.add(t);
            }
        }
        HashMap<String,Double> hm = new HashMap();
        for(int i=0;i<tmp.size();i++){
            Tuple t = tmp.get(i);
            hm.put(t.getElement(),t.getScore());
        }

        for(int i=1;i<ucount/100;i++){
            res.add(tmp.get(i).getElement());
        }
        long calct = System.currentTimeMillis() - calct1;
        long putt1 = System.currentTimeMillis();

        //put to redis
        jedis.del(keyManual);
        jedis.zadd(keyManual, hm);
        long putt = System.currentTimeMillis() - putt1;
        HashMap<String,Long> times  = new HashMap<>();
        times.put(keyGet,getTtotal);
        times.put(keyCalc,calct);
        times.put(keyPut,putt);
        return new ResCostTime<> (null, times);
    }


    /**
     * 获取 lists 头结点中最小的一个，并从list删除此结点
     * lists为空返回null
     * @param lists
     * @return
     */
    public Tuple getMin(List<Tuple> ...lists){
        if(lists.length<=0){
            return null;
        }
        //List<Tuple> compareList = new LinkedList<>();
        List<Tuple> minList = null;
        Tuple minTuple = null;
        for(int i=0;i<lists.length;i++){
            List<Tuple> l = lists[i];
            if(l.size()>0){
                Tuple t = l.get(0);
                if(minTuple == null){//不存在最小
                    minTuple = t;
                    minList = l;
                }else{//最小已经存在
                    if(t.compareTo(minTuple)<0){//取更小的
                        //System.out.println("T "+t+",Min "+minTuple);
                        minTuple = t;
                        minList = l;
                    }
                }
            }
        }
        if(minTuple!=null && minList!=null){
            minList.remove(0);
        }
        return minTuple;
    }



    public static void main(String[] args) {

        RedisUtil r = new RedisUtil();
        r.setup();
        //r.initData(1000);

        int type = 1;

        int testTimes = 10;
        List<ResCostTime> manualTimeStore = new LinkedList<>();
        for( int i=0;i<testTimes;i++) {
            System.out.println((i+1)+"'s execute");
            long t1 = System.currentTimeMillis();
            ResCostTime rct = null;

            if( type ==0) {
                rct = r.zunion();
            }else if (type == 1){
                rct = r.manualSortAdd();
            }
            long t = System.currentTimeMillis() - t1;
//            HashMap<String,Long> hm = new HashMap<>();
//            hm.put(keyTotalTime,t);
            HashMap hm = rct.getTime();
            hm.put(keyTotalTime,t);
            rct.setTime(hm);
            manualTimeStore.add( rct );
            //ResCostTime rct = new ResCostTime(null,hm);
            //manualTimeStore.add(rct);
            //System.out.println("time cost " + t);
        }
        //System.out.println(manualTimeStore);

        ResCostTimeCalc rc = new ResCostTimeCalc(manualTimeStore);
        if(type==1) {
            System.out.println(rc.getFormatAllTime(keyGet));
            System.out.println(rc.getFormatAllTime(keyCalc));
            System.out.println(rc.getFormatAllTime(keyPut));
            System.out.println(rc.getFormatAllTime(keyTotalTime));
        }else if(type==0){
            System.out.println(rc.getFormatAllTime(keyUnionTime));
            System.out.println(rc.getFormatAllTime(keyTotalTime));
        }


    }



}
