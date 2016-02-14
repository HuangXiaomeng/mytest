/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2015 All Rights Reserved.
 *
 * Author: guangming
 * Create Date: 2015年9月28日 下午3:26:34
 */

package com.armon.test.eventbus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * @author guangming
 *
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        EventBus eventBus = new AsyncEventBus(threadPool);
        MultipleListener multiListener1 = new MultipleListener("listener1");
        MultipleListener multiListener2 = new MultipleListener("listener2");

        eventBus.register(multiListener1);
//        eventBus.register(multiListener2);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(100));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800));

        System.out.println("LastInteger:"+multiListener1.getLastInteger());
        System.out.println("LastLong:"+multiListener1.getLastLong());

    }

}
