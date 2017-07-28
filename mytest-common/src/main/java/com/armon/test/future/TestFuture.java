package com.armon.test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.joda.time.DateTime;

public class TestFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "abc";
            }
        });

        System.out.println("start:" + new DateTime(System.currentTimeMillis()).toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(future.get());
        System.out.println("end:" + new DateTime(System.currentTimeMillis()).toString("yyyy-MM-dd HH:mm:ss"));
    }

}
