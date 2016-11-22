package com.ronrytest.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常亮放在了方法区里,所以通过如下参数设置运行环境
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
