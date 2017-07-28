package com.armon.test.test;

public class Main {
    public static int count = 0;

    public static void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        getAndInc();
    }

    public static void main(String[] args) throws InterruptedException {

        //同时启动1000个线程，去进行i++计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Main.inc();
                }
            }).start();
        }

        Thread.sleep(5000);
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Main.count);
    }

    public static synchronized void getAndInc() {
        count++;
    }
}
