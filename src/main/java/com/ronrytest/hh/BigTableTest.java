package com.ronrytest.hh;

import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * <pre>
 * 如果只读的时候，数据量对时间影响不大么，100000和10000000 scan基本都是60-100ms
 * 如果是同时读写，则耗时会翻倍，基本是100-150 ms 好像也不会。。。
 * </pre>
 * 
 * @author ronry 2014年7月15日 下午5:18:17
 */
public class BigTableTest {

    private static char[] ZERO_CHAR           = new char[] { '0', '0', '0', '0', '0', '0', '0', '0', '0' };

    private static int    no                  = 9260000;

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

            // System.out.println("add " + rowKey);
        }
    }

    public static String getRowKey(int aNo) {
        String sNo = String.valueOf(aNo);
        return new String(ZERO_CHAR, 0, 10 - sNo.length()) + sNo;
    }

    public static void main(String[] args) throws Exception {
        final Random random = new Random();

        System.out.println("scan without inserting ");
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        HbaseCRUDTest.testScan(getRowKey(random.nextInt(no)));
                    }

                } catch (Exception e) {
                }
            }
        }) {
        }.start();

        Thread.sleep(10000);

        System.out.println("scan while inserting ");

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        HbaseCRUDTest.testScan(getRowKey(random.nextInt(no)));
                    }

                } catch (Exception e) {
                }
            }
        }) {
        }.start();
        // testInsert(10000);

        System.out.println("insert ended !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
