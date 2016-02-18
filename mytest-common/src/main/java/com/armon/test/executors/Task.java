package com.armon.test.executors;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

public class Task implements Runnable {

    private DateTime initDate;
    private String name;

    public Task(String name) {
        this.initDate = DateTime.now();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
        try {
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(),
                    name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
