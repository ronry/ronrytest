package com.ronrytest.hh;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class HHTestHelper {

    public static HTable  table               = null;

    public static byte[] FIRST_COLUM_FAMILY1 = Bytes.toBytes("columfms1");

    public static byte[] NAME_COLUM          = Bytes.toBytes("name");

    static {
        try {
            table = new HTable(HBaseConfiguration.create(), "testtable2");
        } catch (IOException e) {
        }
    }

    public static void init() {
    }

}
