package com.armon.test.java8;

public class User {
    public static enum Sex {
        MALE, FEMALE
    }

    public User(int id, String name, int age, Sex gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    private int id;
    private String name;
    private int age;
    private Sex gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }


}
