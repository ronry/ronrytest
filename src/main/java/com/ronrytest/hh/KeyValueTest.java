package com.ronrytest.hh;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class KeyValueTest {
	
	public static void main(String[] args) {
        Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("cfm3"), Bytes.toBytes("colume21"), Bytes.toBytes("1234567"));
        put.add(Bytes.toBytes("cfm1"), Bytes.toBytes("colume11"), 111, Bytes.toBytes("1234567"));
        put.add(Bytes.toBytes("cfm1"), Bytes.toBytes("colume11"), 222, Bytes.toBytes("1234567"));
        put.add(Bytes.toBytes("cfm1"), Bytes.toBytes("colume12"), Bytes.toBytes("1234567"));
        put.add(Bytes.toBytes("cfm2"), Bytes.toBytes("colume21"), Bytes.toBytes("1234567"));

        System.out.println(put.getFamilyMap());

        // 会是2个，有两个 timestamp
        System.out.println(put.get(Bytes.toBytes("cfm1"), Bytes.toBytes("colume11")));

        System.out.println(put.heapSize());

        System.out.println(put.numFamilies());
	}

}
