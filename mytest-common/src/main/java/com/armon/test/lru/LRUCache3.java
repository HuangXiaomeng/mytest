/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2017 All Rights Reserved.
 *
 * Author: guangming
 * Create Date: 2017年3月21日 上午1:42:49
 */

package com.armon.test.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author guangming
 *
 */
public class LRUCache3<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache3(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
