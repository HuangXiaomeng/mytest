package com.armon.test.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

public class MultipleListener {
    public Integer lastInteger;
    public Long lastLong;
    public String name;

    MultipleListener(String name) {
      this.name = name;
    }

    @Subscribe
    @AllowConcurrentEvents
    public void listenInteger(Integer event) throws InterruptedException {
        lastInteger = event;
        System.out.println("current thread: " + Thread.currentThread().getName() + ", listenInteger");
        Thread.sleep(5000);
        System.out.println(name + ": event Integer:"+lastInteger);
    }

    @Subscribe
    public void listenLong(Long event) throws InterruptedException {
        lastLong = event;
        System.out.println("current thread: " + Thread.currentThread().getName() + ", listenLong");
        Thread.sleep(5000);
        System.out.println(name + ": event Long:"+lastLong);
    }

    public Integer getLastInteger() {
        return lastInteger;
    }

    public Long getLastLong() {
        return lastLong;
    }
}