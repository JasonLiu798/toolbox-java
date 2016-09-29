package com.jason798.number;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.*;

/**
 * 随机生成数类，测试用
 *
 * @author JasonLiu798
 * @date 2014/01/08 14:58
 */
public class RandomGenerator {

    /**
     * 通过系统时钟种子生成随机int变量，生成此数字的32位 MD5字符串
     *
     * @return
     */
    public static String generateMD5() {
        Random random = new Random(System.currentTimeMillis());
        String randStr = String.valueOf(random.nextInt());
        return DigestUtils.md5Hex(randStr);
    }

    /**
     * 随机生成 0 ~ range-1 范围内随机数
     * @param range
     * @return
     */
    public static int generateInt(int range) throws InterruptedException {
        int res = 0;
        Random random = new Random(System.currentTimeMillis());
        Thread.sleep(1);
        res = Math.abs(random.nextInt()) % range;
//		random.nextGaussian();
        return res;
    }
    public static long generateLong(long range) throws InterruptedException {
        long res = 0;
        Random random = new Random(System.currentTimeMillis());
        Thread.sleep(1);
        res = Math.abs(random.nextLong()) % range;
        return res;
    }


    /**
     * 随机生成start~end范围内随机数
     * @param start
     * @param end
     * @return
     */
    public static int generateInt(int start, int end) throws InterruptedException {
        int range = end - start + 1;
        int res = start + generateInt(range);
        return res;
    }

    public static long generateLong(long start, long end) throws InterruptedException {
        long range = end - start + 1;
        long res = start + generateLong(range);
        return res;
    }

        /**
         * 随机生成长度为N的数字，range(10...0,99...9)
         * @param N
         * @return
         * @throws InterruptedException
         */
    public static int generateLengthNNum(int N) throws InterruptedException {
        int num = 9;
        for(int i=0;i<N-1;i++){
            num = num*10 + 9;
        }
        StringBuilder rawNum = new StringBuilder(String.valueOf(generateInt(num)));
        if(rawNum.length()<N){
            for(int i=rawNum.length();i<N;i++){
                rawNum .append(String.valueOf(generateInt(9)));
            }
        }
        return Integer.parseInt(rawNum.toString());
    }

    /**
     * 随机生成手机号
     * @return
     * @throws InterruptedException
     */
    public static String generateMobile() throws InterruptedException{
        int [] prefix = {13,14,15,17,18};
        int prefixNum = prefix[generateInt(prefix.length)];
        int lastNum = generateLengthNNum(9);
        return String.valueOf(prefixNum) + String.valueOf(lastNum);
    }

    /**
     * 生成N个mobile
     * @param N
     * @return
     * @throws InterruptedException
     */
    public static Set<String>  generateDistinctMobileN(int N) throws InterruptedException{
        Set<String> set = new HashSet<>();
        while(set.size()<N){
            String mobile = generateMobile();
            set.add(mobile);
        }
        return set;
    }

    public static List<String>  generateMobileN(int N) throws InterruptedException{
        List<String> set = new ArrayList<>(N);
        for(int i=0;i<N;i++){
            String mobile = generateMobile();
            set.add(mobile);
        }
        return set;
    }
    /**
     * 生成不同的n个，大小从start~end的数
     * @param n
     * @param start
     * @param end
     * @return
     */
    public static Set<Integer> generateDistinctBatchNum(int n, int start, int end) throws InterruptedException {
        Set<Integer> set = new HashSet<>();
        while (set.size() < n) {
            int num = generateInt(start, end);
            Thread.sleep(1);// sleep 随机时间，以便产生的system.currentTimeMillis不同
            set.add(num);
        }
        return set;
    }

    /**
     * 生成n个数，可能相同
     * @param n
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> generateBatchNum(int n, int start, int end) throws InterruptedException {
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int num = generateInt(start, end);
            try {
                Thread.sleep(num % 1000);// sleep
                // 随机时间，以便产生的system.currentTimeMillis不同
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res.add(num);
        }
        return res;
    }

    /**
     * 随机生成正太分布数 miu = 期望值 sigma2 = 方差
     * @param miu
     * @param sigma2
     * @return
     */
    public static double generateNormalRand(double miu, double sigma2) {
        double N = 12;
        double x = 0, temp = N;
        do {
            x = 0;
            for (int i = 0; i < N; i++)
                x = x + (Math.random());
            x = (x - temp / 2) / (Math.sqrt(temp / 12));
            x = miu + x * Math.sqrt(sigma2);
        } while (x <= 0); // 在此我把小于0的数排除掉了
        return x;
    }

    /**
     * 生成不同的n个正太分布数
     * @param n
     * @param miu
     * @param sigma2
     * @return
     */
    public static Set<Long> generateDistinctBatchNormalNum(int n, int miu, int sigma2) {
        Set<Long> set = new HashSet<>();
        int i = set.size();
        while (i < n) {
            long num = Math.round(generateNormalRand(miu, sigma2));
            try {
                Thread.sleep(num % 1000);// sleep
                // 随机时间，以便产生的system.currentTimeMillis不同
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            set.add(num);
            i = set.size();
        }
        return set;
    }

    /**
     * 生成n个正太分布数，可能相同
     * @param n
     * @param miu
     * @param sigma2
     * @return
     */
    public static List<Long> generateBatchNormalNum(int n, int miu, int sigma2) {
        List<Long> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Long num = Math.round(generateNormalRand(miu, sigma2));
            try {
                Thread.sleep(num % 1000);// sleep
                // 随机时间，以便产生的system.currentTimeMillis不同
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res.add(num);
        }
        return res;
    }

    /**
     * 随机生成泊松分布
     * @param Lamda
     * @return
     */
    public double psRand(double Lamda) {
        double x = 0, b = 1, c = Math.exp(-Lamda), u;
        do {
            u = Math.random();
            b *= u;
            if (b >= c)
                x++;
        } while (b >= c);
        return x;
    }


}