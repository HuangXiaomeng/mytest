package com.armon.test.test;

public abstract class ClassB extends ClassA {
  public void print() {
    System.out.println(this.getClass().getName());
  }
}
