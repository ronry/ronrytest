/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * 类MysqlJdbcSimpleTest.java的实现描述：TODO 类实现描述
 * 
 * @author ronry 2011-5-28 下午11:14:22
 */
public class MysqlJdbcSimpleTest {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/information_schema", "root", "12345");

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("select * from SCHEMATA limit 10");

        ResultSetMetaData meta = rs.getMetaData();

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.print(meta.getColumnName(i));
            System.out.print("       ");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(rs.getObject(i));
                System.out.print("       ");
            }
            System.out.println();
        }

        if (rs != null) {
            rs.close();
            rs = null;
        }

        if (statement != null) {
            statement.close();
            statement = null;
        }

        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}
