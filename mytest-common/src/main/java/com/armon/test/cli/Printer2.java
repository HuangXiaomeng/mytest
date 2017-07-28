package com.armon.test.cli;

import java.util.concurrent.CountDownLatch;

public class Printer2 {
    private static int length = 10000;
    private static Object LOCK = new Object();
    private static int index = 1;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(2);
        Thread printer1 = new Thread() {
            public void run() {
                while (index <= length) {
                    synchronized (LOCK) {
                        if (index % 2 == 1) {
                            System.out.println("Printer1-" + index);
                            index++;
                            LOCK.notify();
                        } else {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                countDown.countDown();
            }
        };

        Thread printer2 = new Thread() {
            public void run() {
                while (index <= length) {
                    synchronized (LOCK) {
                        if (index % 2 == 0) {
                            System.out.println("Printer2-" + index);
                            index++;
                            LOCK.notify();
                        } else {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
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
