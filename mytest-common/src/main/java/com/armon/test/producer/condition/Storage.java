package com.armon.test.producer.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    public static final int SIZE = 100;
    public static final Lock lock = new ReentrantLock();
    public static final Condition notFull = lock.newCondition();
    public static final Condition notEmpty = lock.newCondition();

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
