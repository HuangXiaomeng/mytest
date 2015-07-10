package com.armon.test.observer;

import java.util.Observable;
import java.util.Observer;

public class HouseObserver implements Observer {
	private String name;
	
	HouseObserver(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Float) {
			System.out.println(this.name + "观察到价格更改为：" + arg);
		}
	}

}
