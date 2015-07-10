package com.armon.test.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    @Value(value = "${mycomponent.arg.string}")
    private String arg1;

    @Value(value = "${mycomponent.arg.int}")
    private String arg2;

    @Value(value = "${mycomponent.arg.float}")
    private String arg3;

    public static String arg11;
    public static String arg22;
    public static String arg33;

    @PostConstruct
    public void init(){
        arg11 = arg1;
        arg22 = arg2;
        arg33 = arg3;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getArg3() {
        return arg3;
    }

}
