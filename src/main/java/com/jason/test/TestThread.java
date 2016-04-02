package com.jason.test;

/**
 * @author JianLong
 * @date 2016/2/17 16:41
 */
public class TestThread {

    public static void main(String[] args) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }){
            public void run() {
                System.out.println("thread");
            }
        }.start();
    }
}
