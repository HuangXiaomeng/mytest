package com.armon.test.automic;

public class TestAutomic {

    public static void main(final String[] args) {
        final P1 v = new P1();

        // 线程 1：设置 b = 0
        final Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    v.set1();
                }
            };
        };
        t1.start();

        // 线程 2：设置 b = -1
        final Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    v.set2();
                }
            };
        };
        t2.start();

        // 线程 3：检查 0 != b && -1 != b
        final Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    v.check();
                }
            };
        };
        t3.start();
    }

}
