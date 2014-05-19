package com.ronrytest.nio.rchat;

import java.util.HashMap;
import java.util.Map;

public class UserService {

	private static Map<String, String> users = new HashMap<String, String>();

	static {
		users.put("jinlianglv", "1111112");
		users.put("ronry", "1111112");
		users.put("ronry2", "1111112");
		users.put("ronry3", "1111112");
		users.put("ronry4", "1111112");
		users.put("ronry5", "1111112");
		users.put("ronry6", "1111112");
		users.put("ronry7", "1111112");
		users.put("ronry8", "1111112");
	}

	public boolean auth(String userName, String pwd) {
		return users.containsKey(userName) && users.get(userName).equals(pwd);
	}

}
