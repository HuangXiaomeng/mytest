package com.armon.test.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutors {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task " + i);
            es.execute(task);
        }
        es.shutdown();
    }
}
