package com.armon.test.cli;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Printer {
    private static AtomicInteger index = new AtomicInteger(1);
    private static int length = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(2);

        Thread printer1 = new Thread() {
            public void run() {
                int tmp = index.get();
                while ((tmp = index.get()) <= length) {
                    if (tmp % 2 == 1) {
                        System.out.println("Printer1-" + tmp);
                        index.getAndIncrement();
                    }
                }
                countDown.countDown();
            }
        };

        Thread printer2 = new Thread() {
            public void run() {
                int tmp = index.get();
                while ((tmp = index.get()) <= length) {
                    if (tmp % 2 == 0) {
                        System.out.println("Printer2-" + tmp);
                        index.getAndIncrement();
                    }
                }
                countDown.countDown();
            }
        };

        long start = System.currentTimeMillis();
        printer1.start();
        printer2.start();
        countDown.await();
        long end = System.currentTimeMillis();
        System.out.println("cost " + (end-start) + "ms");
    }
}
