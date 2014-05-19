package com.ronrytest.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * run with param: -XX:PermSize=1m -XX:MaxPermSize=1m
 * @author ronry
 *
 */
public class RuntimeConstantPoolOomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		
		int i=0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}

}
