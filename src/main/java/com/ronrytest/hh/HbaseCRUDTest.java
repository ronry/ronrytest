package com.ronrytest.hh;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseCRUDTest {

    private static HTable table = null;

    private static byte[] FIRST_COLUM_FAMILY1 = Bytes.toBytes("columfms1");

    private static byte[] NAME_COLUM = Bytes.toBytes("name");

    static {
        try {
            table = new HTable(HBaseConfiguration.create(), "testbigtable");
        } catch (IOException e) {
        }
    }

    public static void testInsert() throws Exception {
        Put put = new Put(Bytes.toBytes("row" + (Long.MAX_VALUE - System.currentTimeMillis())));
        put.add(FIRST_COLUM_FAMILY1, NAME_COLUM, Bytes.toBytes("ronry" + System.currentTimeMillis()));

        table.put(put);

        System.out.println("insert success");
    }

    public static void testScan(String startRow) throws Exception {
        ResultScanner scan = table.getScanner(new Scan(Bytes.toBytes("row0000000000")));
        long begin = System.currentTimeMillis();
        Result[] results = scan.next(100);

        if (results != null && results.length > 0) {
            for (Result result : results) {
                Bytes.toString((result.getRow()));
                // System.out.println("value:" + Bytes.toString((result.getRow())));
            }
        } else {
            System.out.println("no data");
        }

        System.out.println("used " + (System.currentTimeMillis() - begin));

        scan.close();

        scan = table.getScanner(new Scan(Bytes.toBytes("row"+startRow)));
        begin = System.currentTimeMillis();
        results = scan.next(100);

        if (results != null && results.length > 0) {
            for (Result result : results) {
                // Bytes.toString((result.getRow()));
                // System.out.println("value:" + Bytes.toString((result.getRow())));
            }
        } else {
            System.out.println("no data");
        }

        System.out.println(startRow + " scan used " + (System.currentTimeMillis() - begin));

        scan.close();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // testInsert();
        testScan("00008017549");
    }

}
