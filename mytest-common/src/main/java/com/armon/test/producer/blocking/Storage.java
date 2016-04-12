package com.armon.test.producer.blocking;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage {
    public static final int SIZE = 100;

    private Queue<Object> objects = new LinkedBlockingQueue<>(SIZE);

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
