package com.armon.test.test;

public class Hello {

    public static void main(String[] args) {
        for (String str : args) {
            System.out.println("hadoop distcp -p -i hdfs://10.11.2.182:9000" + str + "/visit_date=2016-07-31 " + str + "/visit_date=2016-07-31");
        }

    }

}
