package com.armon.test.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
  public static void main(String[] args) {
      int x = 4;
      System.out.println(x >> 2 & 1);
      System.out.println(x >> 1 & 1);
      System.out.println(x & 1);
    Map<Integer, String> map = new HashMap<Integer, String>();
    map.put(123, "one two three");
    map.put(21, "two one");
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");

    Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
    Map.Entry<Integer, String> entry = null;
    while (it.hasNext()) {
      entry = it.next();
    }
    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

    Boolean btrue = Boolean.TRUE;
    if (btrue == true) {
      System.out.println("true");
    }

  }
}
