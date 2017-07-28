package com.armon.test.test;

public class Hello {

    public static void main(String[] args) {
//        String msg = "建表限制：ABCDEFG；缺失权限：123456";
        String msg = "缺失权限：123456";
        if (msg.contains("缺失权限")) {
            msg = msg.substring(msg.indexOf("缺失权限"));
            System.out.println(msg);
        } else {
            System.out.println("OK");
        }
    }

}
