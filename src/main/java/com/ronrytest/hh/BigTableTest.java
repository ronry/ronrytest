package com.ronrytest.hh;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class BigTableTest {

    private static char[] ZERO_CHAR           = new char[] { '0', '0', '0', '0', '0', '0', '0', '0', '0' };

    private static int    no                  = 8220000;

    private static HTable table               = null;

    private static byte[] FIRST_COLUM_FAMILY1 = Bytes.toBytes("columfms1");

    private static byte[] NAME_COLUM          = Bytes.toBytes("name");

    static {
        try {
            Configuration conf = HBaseConfiguration.create();
            // HBaseHelper helper = HBaseHelper.getHelper(conf);
            // helper.dropTable("testbigtable");
            // helper.createTable("testbigtable", "columfms1");
            table = new HTable(conf, "testbigtable");
        } catch (Exception e) {
        }
    }

    public static void testInsert(int count) throws Exception {

        for (int i = 0; i < count; i++) {
            no++;
            String rowKey=getRowKey(no);
            Put put = new Put(Bytes.toBytes("row" + rowKey));
            put.add(FIRST_COLUM_FAMILY1, NAME_COLUM, Bytes.toBytes("ronry" + System.currentTimeMillis()));

            table.put(put);

            System.out.println("add " + rowKey);
        }
    }

    public static String getRowKey(int aNo) {
        String sNo = String.valueOf(aNo);
        return new String(ZERO_CHAR, 0, 10 - sNo.length()) + sNo;
    }

    public static void main(String[] args) throws Exception {
        testInsert(1000000);
    }

}
