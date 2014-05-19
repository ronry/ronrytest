package com.ronrytest.mongo;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

public class Person2 extends Person implements DBObject{
	
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public Object put(String key, Object v) {
		System.out.println("put(String key, Object v)");
		return null;
	}

	@Override
	public void putAll(BSONObject o) {
		// TODO Auto-generated method stub
		System.out.println("putAll(BSONObject o)");
	}

	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub
		System.out.println("putAll(Map m)");
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeField(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsField(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markAsPartialObject() {
		System.out.println("markAsPartialObject");
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPartialObject() {
		System.out.println("isPartialObject");
		// TODO Auto-generated method stub
		return false;
	}
	
}
