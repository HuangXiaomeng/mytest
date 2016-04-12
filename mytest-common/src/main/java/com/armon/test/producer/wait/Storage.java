package com.armon.test.producer.wait;

import java.util.LinkedList;

public class Storage {
    public static final int SIZE = 100;
    private LinkedList<Object> objects = new LinkedList<>();

    public void put(Object o) {
        objects.add(o);
    }

    public Object take() {
        return objects.remove();
    }

    public int size() {
        return objects.size();
    }
}
