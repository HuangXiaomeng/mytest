package com.armon.test.producer.blocking;

import com.armon.test.producer.wait.Storage;

public class Consumer extends Thread {
    private Storage storage;
    private int num;

    public Consumer(String name, Storage storage, int num) {
        super(name);
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        if (storage.size() < num) {
            System.out.printf(currentThread().getName() + " 需要%d个商品，当前仓库库存%d，等待生产...\n", num, storage.size());
        }
        int counter = num;
        while (counter-- > 0) {
            storage.take();
        }
        System.out.printf(currentThread().getName() + " 从仓库取走%d个商品，当前仓库容量=%d\n", num, storage.size());
    }
}
