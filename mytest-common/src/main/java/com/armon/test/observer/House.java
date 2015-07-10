package com.armon.test.observer;

import java.util.Observable;

public class House extends Observable {
	private float price;
	
	House(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
		super.setChanged(); // 设置变化点
		super.notifyObservers(price); // 通知所有观察者价格变化
	}
	
	@Override
	public String toString() {
		return "房子价格为：" + this.price;
	}
}
