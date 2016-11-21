
package com.armon.test.producer.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread {
    private Storage storage;
    private int num;
    private final int size = Storage.SIZE;
    private final Lock lock = Storage.lock;
    private final Condition notFull = Storage.notFull;
    private final Condition notEmpty = storage.notEmpty;

    public Producer(String name, Storage storage, int num) {
        super(name);
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (storage.size() + num > size) {
                System.out.printf(currentThread().getName() + " 仓库总量=%d, 当前仓库空间=%d，需要生产%d，空间不够，等待消费...\n",
                        Storage.SIZE, storage.size(), num);
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int counter = num;
            while (counter-- > 0) {
                storage.put(new Object());
            }
            notEmpty.signalAll();
            System.out.printf(currentThread().getName() + " 向仓库添加%d个商品，当前仓库容量%d\n", num,  storage.size());
        } finally {
            lock.unlock();
        }
    }
}
