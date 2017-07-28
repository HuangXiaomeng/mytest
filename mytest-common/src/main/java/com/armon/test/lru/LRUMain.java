package com.armon.test.lru;

public class LRUMain {

    public static void main(String[] args) throws Exception {
        System.out.println("start...");

        lruCache1();
        lruCache2();
        lruCache3();

        System.out.println("over...");
    }

    static void lruCache1() {
        System.out.println();
        System.out.println("===========================LRUCache1===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        lru.get(2);
        System.out.println(lru.toString());
        System.out.println();
    }

    static void lruCache2() {
        System.out.println();
        System.out.println("===========================LRUCache2===========================");
        LRUCache2<Integer, String> lru = new LRUCache2(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        lru.get(2);
        System.out.println(lru.toString());
        System.out.println();
    }

    static void lruCache3() {
        System.out.println();
        System.out.println("===========================LRUCache3===========================");
        LRUCache3<Integer, String> lru = new LRUCache3(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());

        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        lru.get(2);
        System.out.println(lru.toString());
        System.out.println();
    }

}
