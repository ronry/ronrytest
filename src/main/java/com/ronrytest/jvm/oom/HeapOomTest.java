package com.ronrytest.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class HeapOomTest {
	
	private byte[] data=new byte[1024*1024];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<HeapOomTest> dataList=new ArrayList<HeapOomTest>();
		
		while(true){
			dataList.add(new HeapOomTest());
		}
	}

}
