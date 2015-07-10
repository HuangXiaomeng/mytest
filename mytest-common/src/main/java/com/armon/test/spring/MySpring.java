package com.armon.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpring {
	public static void main(String[] args) {
		String resource = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(resource);
		MyBean bean = (MyBean) ac.getBean("mybean");

		System.out.println(bean.getName());
		System.out.println(bean.getAge());

        System.out.println(MyComponent.arg11);
        System.out.println(MyComponent.arg22);
        System.out.println(MyComponent.arg33);

        A a = new A();
        System.out.println(a.getComponent().getArg1());
	}

	static class A {
	    @Autowired
	    MyComponent myComponent;

	    public MyComponent getComponent () {
	        return myComponent;
	    }
	}
}
