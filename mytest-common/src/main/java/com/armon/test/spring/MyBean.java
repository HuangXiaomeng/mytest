package com.armon.test.spring;

public class MyBean {
	private String name;
	private int age;

	public MyBean(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void init() {
		System.out.println("init...");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
