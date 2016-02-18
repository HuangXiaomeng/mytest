package com.armon.test.disruptor;

public class Record {
	private String data;
	
	public Record(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
