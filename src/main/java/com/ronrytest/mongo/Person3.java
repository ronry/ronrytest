package com.ronrytest.mongo;

public class Person3 {
private String id;
	
	private String name;
	
	private int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	private Address2 address;

	public Address2 getAddress() {
		return address;
	}

	public void setAddress(Address2 address) {
		this.address = address;
	}
	
}
