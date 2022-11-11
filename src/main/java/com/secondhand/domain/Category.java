package com.secondhand.domain;

import java.io.Serializable;

/*
 * Category Domain Class
 * */
@SuppressWarnings("serial")
public class Category implements Serializable {

	/* Private Fields */
	private int catId;
	private String name;

	/* JavaBeans Properties */
	public int getcatId() {
		return catId;
	}

	public void setcatId(int catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* Public Methods */
	public String toString() {
		return "ī�װ� : " + getcatId() + " (" + getName() + ")";
	}
}