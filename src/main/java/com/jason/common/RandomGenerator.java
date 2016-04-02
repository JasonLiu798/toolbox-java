package com.jason.common;

import com.jason.net.IPUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 随机生成数类，测试用
 *
 * @author JianLong
 * @date 2014/10/15 14:58
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
	 * 随机生成0~range范围内随机数
	 *
	 * @param range
	 * @return
	 */
    public static <T extends Number> T generateNum(T range) {
        Random random = new Random(System.currentTimeMillis());
        Class<T> cls = (Class<T>) range.getClass();
        T res = null;
        Object tmp = null;
        if (cls == java.lang.Integer.class) {
            int irange = Integer.parseInt(range.toString());
            tmp = Math.abs(random.nextInt()) % irange;
        } else if (cls == java.lang.Long.class) {
            int irange = Integer.parseInt(range.toString());
            tmp = Math.abs(random.nextLong()) % irange;
        }
        res = (T)tmp;
        return res;
    }


    /**
     * generate number start~end
     * @param start
     * @param end
     * @return
     */
    public static <T extends Number> T generateNum(T start, T end) {
        T res = null;
        try {
            T minres = NumberUtil.minus(end, start);
            T range =  NumberUtil.incr(minres);
            T gn = generateNum(range);
            res = NumberUtil.add(start,gn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

	/**
	 * generate n distinct number
	 * @param n
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<Integer> generateDistinctBatchNum(int n, int start, int end) {
		Set<Integer> set = new HashSet<>();
		int i = set.size();
		while (i < n) {
			int num = generateNum(start, end);
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
	 * generate n number,maybe get same number
	 * @param n
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Integer> generateBatchNum(int n, int start, int end) {
		List<Integer> res = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			int num = generateNum(start, end);
			try {
				Thread.sleep(1);// sleep
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			res.add(num);
		}
		return res;
	}

	/**
	 * generate normanl miu = Expect ,sigma2 = variance
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
		} while (x <= 0); // delete less than 0
		return x;
	}

	/**
	 * 生成不同的n个正太分布数
	 *
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
	 * 
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
	 * 泊松分布
	 * 
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

    /**
     * 获取国内随机IP地址
     * 注：适用于32位操作系统
     */
    public static String generateChinaIP(){
        String ip = null;
        long[][] iplongs = {
                {607649792, 608174079},
                {1038614528, 1039007743},
                {1783627776, 1784676351},
                {35023872, 2035154943},
                {78801920, 2079064063},
                {950089216, -1948778497},
                {425539072, -1425014785},
                {236271104, -1235419137},
                {70113536, -768606209},
                {69376768, -564133889}
        };

        int key = RandomGenerator.generateNum(0, 9);
        long start = iplongs[key][0];
        long end = iplongs[key][0];
        ip = IPUtil.long2ip(RandomGenerator.generateNum(start, end));
        //36.56.0.0-36.63.255.255
        //61.232.0.0-61.237.255.255
        //106.80.0.0-106.95.255.255
        //121.76.0.0-121.77.255.255
        //123.232.0.0-123.235.255.255
        //139.196.0.0-139.215.255.255
        //171.8.0.0-171.15.255.255
        //182.80.0.0-182.92.255.255
        //210.25.0.0-210.47.255.255
        //222.16.0.0-222.95.255.255
        return ip;
    }


    public static void main(String[] args) {
        Float res= generateNum(123.123f);
//        Long res = RandomGenerator.<Long>generateNum(1l,12l);
        System.out.println(res);
//        System.out.println(generateMD5());
		// System.out.println(normRand(95,5));
		// System.out.println(generateDistinctBatchNormalNum(10,95,5));
		// System.out.println(generateDistinctBatchNormalNum(10, 95, 5));
	}
}