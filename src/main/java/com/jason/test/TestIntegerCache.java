package com.jason.test;

/**
 * @author JianLong
 * @date 2016/2/15 16:57
 */
import java.lang.reflect.Field;
import java.util.Random;

public class TestIntegerCache {
    public static void main(String[] args)
            throws Exception {
        //http://blog.jooq.org/2013/10/17/add-some-entropy-to-your-jvm/
        // Extract the IntegerCache through reflection
        Class<?> clazz = Class.forName(
                "java.lang.Integer$IntegerCache");
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);

        // Rewrite the Integer cache
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Integer(
                    new Random().nextInt(cache.length));
        }

        // Prove randomness
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
        System.out.println("----");
        System.out.println((Integer)100);
        System.out.println((Integer)200);

    }
}
