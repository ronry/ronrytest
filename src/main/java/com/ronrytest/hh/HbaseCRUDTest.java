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
            table = new HTable(HBaseConfiguration.create(), "testtable");
        } catch (IOException e) {
        }
    }

    public static void testInsert() throws Exception {
        Put put = new Put(Bytes.toBytes("row" + (Long.MAX_VALUE - System.currentTimeMillis())));
        put.add(FIRST_COLUM_FAMILY1, NAME_COLUM, Bytes.toBytes("ronry" + System.currentTimeMillis()));

        table.put(put);

        System.out.println("insert success");
    }

    public static void testScan() throws Exception {
        ResultScanner scan = table.getScanner(new Scan(Bytes.toBytes("row000")));
        Result[] results = scan.next(2);

        if (results != null && results.length > 0) {
            for (Result result : results) {
                System.out.println("value:" + Bytes.toString((result.getValue(FIRST_COLUM_FAMILY1, NAME_COLUM))));
            }
        } else {
            System.out.println("no data");
        }

        scan.close();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // testInsert();
        testScan();
    }

}
