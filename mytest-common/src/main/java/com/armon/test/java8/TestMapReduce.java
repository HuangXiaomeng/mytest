/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2016 All Rights Reserved.
 *
 * Author: guangming
 * Create Date: 2016年8月4日 下午5:55:21
 */

package com.armon.test.java8;

import java.util.List;

import com.armon.test.java8.Task.STATUS;
import com.google.common.collect.Lists;

/**
 * @author guangming
 *
 */
public class TestMapReduce {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Task> tasks = Lists.newArrayList(
                new Task(STATUS.OPEN, 5),
                new Task(STATUS.OPEN, 13),
                new Task(STATUS.CLOSED, 8)
                );

        long sumPointsOfOpenTasks = tasks
                .stream()
                .filter(task -> task.getStatus() == STATUS.OPEN)
                .mapToInt(task -> task.getPoints())
                .sum();
        System.out.println("Sum points of open tasks is " + sumPointsOfOpenTasks);

        long sumPointsOfOpenTasksWithParallel = tasks
                .stream()
                .parallel()
                .filter(task -> task.getStatus() == STATUS.OPEN)
                .map( task -> task.getPoints())
                .reduce(0, (a,b) -> a+b);
        System.out.println("Sum points of open tasks with parallel is " + sumPointsOfOpenTasksWithParallel);


    }

}
