package com.ronrytest.hh;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseInsertTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();

        HTable table = new HTable(conf, "testtable");

        Put put = new Put(Bytes.toBytes("row" + System.currentTimeMillis()));
        put.add(Bytes.toBytes("columfms1"), Bytes.toBytes("name"), Bytes.toBytes("ronry" + System.currentTimeMillis()));

        table.put(put);

    }

}
