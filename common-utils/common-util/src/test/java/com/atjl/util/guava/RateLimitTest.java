package com.atjl.util.guava;


import com.google.common.util.concurrent.RateLimiter;

public class RateLimitTest {


    public static void demo() {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
    }

    public static void demoSudden() {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(10));
//        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));

    }


    public static void main(String[] args) {
//        demo();
        long st = System.currentTimeMillis();
        demoSudden();

        long cost = System.currentTimeMillis() - st;
        System.out.println("cost " + cost);

    }
}
