package com.armon.test.producer.condition;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Producer p1 = new Producer("p1", storage, 10);
        Producer p2 = new Producer("p2", storage, 10);
        Producer p3 = new Producer("p3", storage, 10);
        Producer p4 = new Producer("p4", storage, 10);
        Producer p5 = new Producer("p5", storage, 10);
        Producer p6 = new Producer("p6", storage, 10);
        Producer p7 = new Producer("p7", storage, 80);

        Consumer c1 = new Consumer("c1", storage, 50);
        Consumer c2 = new Consumer("c2", storage, 20);
        Consumer c3 = new Consumer("c3", storage, 30);

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
