package com.armon.test.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache2<K,V> {
    private LinkedList<K> list = new LinkedList<>();
    private Map<K, V> hashmap = new HashMap<>();
    private final int MAX_CACHE_SIZE;

    public LRUCache2(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
    }

    public void put(K key, V val) {
        if (!hashmap.containsKey(key)) {
            if (hashmap.size() >= MAX_CACHE_SIZE) {
                K last = list.removeLast();
                hashmap.remove(last);
            }
            list.addFirst(key);
        } else {
            //move to first
            list.remove(key);
            list.addFirst(key);
        }
        hashmap.put(key, val);
    }

    public V get(K key) {
        if (hashmap.containsKey(key)) {
            //move to fist
            list.remove(key);
            list.addFirst(key);
            return hashmap.get(key);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (K k : list) {
            sb.append(String.format("%s:%s ", k, hashmap.get(k)));
        }
        return sb.toString();
    }
}
