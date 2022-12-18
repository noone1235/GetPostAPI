package com.chary.main.model;

import com.chary.main.annotaions.ChangeName;

public class JsonStudent {
	private int id;
	@ChangeName(value="StudentData")
	private String data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public JsonStudent(int id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
	
	
}
