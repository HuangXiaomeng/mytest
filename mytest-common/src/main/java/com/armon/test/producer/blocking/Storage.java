package com.armon.test.producer.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage {
    public static final int SIZE = 100;

    private BlockingQueue<Object> objects = new LinkedBlockingQueue<>(SIZE);

    public void put(Object o) {
        try {
            objects.put(o);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object take() {
        try {
            return objects.take();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public int size() {
        return objects.size();
    }
}
