package com.armon.test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.armon.test.java8.User.Sex;

public class TestCollectors {

    private static List<User> users = Arrays.asList(
            new User(1, "张三", 12,User.Sex.MALE),
            new User(2, "李四", 21, User.Sex.FEMALE),
            new User(3,"王五", 32, User.Sex.MALE),
            new User(4, "赵六", 32, User.Sex.FEMALE));

    public static void main(String[] args) {
        //获取所有用户的平均年龄
        System.out.println("\n=======");
        double avg = users.parallelStream().mapToInt(User::getAge)
                .average().getAsDouble();
        System.out.println("所有用户平均年龄: " + avg);

        //获取所有用户的年龄总和
        System.out.println("\n=======");
        double sum = users.parallelStream().mapToInt(User::getAge)
                .reduce(0, (x, y) -> x + y); // 可以简写为.sum()
        System.out.println("所有用户年龄和: " + sum);

        //获取所有男性用户的平均年龄
        System.out.println("\n=======");
        double maleAvg = users.stream().filter(p -> p.getGender() == Sex.MALE)
                .mapToInt(User::getAge).average().getAsDouble();
        System.out.println("所有男性用户平均年龄: " + maleAvg);

        //获取年龄大于12的用户列表
        List<User> list = users.parallelStream().filter(p -> p.getAge() > 12)
                .collect(Collectors.toList());
        System.out.println("年龄大于12的用户列表：" + list);

        //按照是否成年分组
        System.out.println("\n=======");
        Map<Boolean, List<User>> ageMap = users.stream().collect(
//                Collectors.partitioningBy(p -> p.getAge() > 18));
                Collectors.groupingBy(p -> p.getAge() > 18));
        System.out.println("未成年组：" + ageMap.get(false));
        System.out.println("成年组：" + ageMap.get(true));

        //按性别统计用户数
        System.out.println("\n=======");
        Map<User.Sex, Integer> map = users.parallelStream().collect(
                Collectors.groupingBy(User::getGender,
                        Collectors.summingInt(p -> 1)));
        System.out.println("按性别统计用户数：" + map);

        //按性别获取用户名称
        System.out.println("\n=======");
        Map<User.Sex, List<String>> map2 = users.stream()
                .collect(
                        Collectors.groupingBy(
                                User::getGender,
                                Collectors.mapping(User::getName,
                                        Collectors.toList())));
        System.out.println("按性别获取用户名称：" + map2);

        //按性别求年龄的总和
        System.out.println("\n=======");
        Map<User.Sex, Integer> map3 = users.stream().collect(
                Collectors.groupingBy(User::getGender,
                        Collectors.reducing(0, User::getAge, Integer::sum)));
        System.out.println("按性别求年龄总和：" + map3);

        //按性别求年龄的平均值
        System.out.println("\n=======");
        Map<User.Sex, Double> map4 = users.stream().collect(
                Collectors.groupingBy(User::getGender,
                        Collectors.averagingInt(User::getAge)));
        System.out.println("按性别求年龄的平均值：" + map4);

    }

}
