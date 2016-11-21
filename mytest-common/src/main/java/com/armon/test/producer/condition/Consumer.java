package com.armon.test.producer.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
    private Storage storage;
    private int num;
    private final Lock lock = Storage.lock;
    private final Condition notFull = Storage.notFull;
    private final Condition notEmpty = storage.notEmpty;

    public Consumer(String name, Storage storage, int num) {
        super(name);
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (storage.size() < num) {
                System.out.printf(currentThread().getName() + " 需要%d个商品，当前仓库库存%d，等待生产...\n", num, storage.size());
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int counter = num;
            while (counter-- > 0) {
                storage.take();
            }
            notFull.signalAll();
            System.out.printf(currentThread().getName() + " 从仓库取走%d个商品，当前仓库容量=%d\n", num, storage.size());
        } finally {
            lock.unlock();
        }
    }
}
