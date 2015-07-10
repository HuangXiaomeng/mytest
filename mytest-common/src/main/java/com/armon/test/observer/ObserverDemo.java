package com.armon.test.observer;

public class ObserverDemo {
	public static void main(String[] args) {
		House h1 = new House(100);
		HouseObserver ob1 = new HouseObserver("购房者A");
		HouseObserver ob2 = new HouseObserver("购房者B");
		HouseObserver ob3 = new HouseObserver("购房者C");
		h1.addObserver(ob1);
		h1.addObserver(ob2);
		h1.addObserver(ob3);
		System.out.println(h1);
		
		h1.setPrice(200);
		System.out.println(h1);
		
	}
}
