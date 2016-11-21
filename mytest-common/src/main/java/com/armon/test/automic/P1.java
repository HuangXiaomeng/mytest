package com.armon.test.automic;

public class P1 {

    private volatile long b = 0;

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;
    }

    public void check() {
        System.out.println(b);

        if (0 != b && -1 != b) {
            System.err.println("Error");
        }
    }
}
