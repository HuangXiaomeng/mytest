package com.armon.test.producer.wait;

public class Producer extends Thread {

    private Storage storage;
    private int num;

    public Producer(String name, Storage storage, int num) {
        super(name);
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        synchronized (storage) {
            while (storage.size() + num > Storage.SIZE) {
                System.out.printf(currentThread().getName() + " 仓库总量=%d, 当前仓库空间=%d，需要生产%d，空间不够，等待消费...\n",
                        Storage.SIZE,storage.size(),num);
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int counter = num;
            while (counter-- > 0) {
                storage.put(new Object());
            }
            storage.notifyAll();
            System.out.printf(currentThread().getName() + " 向仓库添加%d个商品，当前仓库容量%d\n", num,  storage.size());
        }
    }


}
