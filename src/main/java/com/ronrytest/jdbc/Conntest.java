package com.ronrytest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conntest
{
  public static void main(String[] paramArrayOfString)
  {
    Connection localConnection = null;
    Statement localStatement = null;
    ResultSet localResultSet = null;
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException localClassNotFoundException) {
      System.err.println("mydb(): " + localClassNotFoundException.getMessage());
    }
    try
    {
      localConnection = DriverManager.getConnection("jdbc:oracle:thin:@172.22.29.12:1521:ocndb", "alibaba", "nqQw4F35Wpgw");
    } catch (SQLException localSQLException1) {
      System.err.println("conn:" + localSQLException1.getMessage());
    }

    if (localConnection != null) System.out.println("connection successful"); else
      System.out.println("connection failure");
    try
    {
      localStatement = localConnection.createStatement();
    }
    catch (SQLException localSQLException2) {
      System.err.println("createStatement();" + localSQLException2.getMessage());
    }

    try
    {
      localResultSet = localStatement.executeQuery("SELECT alibaba.SEQ_WP_COMPONENT_INSTANCE.nextVal FROM dual");
    }
    catch (SQLException localSQLException3) {
      System.err.println("stmt.excuteQuery();" + localSQLException3.getMessage());
    }

    try
    {
      while (localResultSet.next())
      {
        System.out.println("seq: " + localResultSet.getString(1));
      }
    }
    catch (SQLException localSQLException4)
    {
      System.err.println("A ERROR is failure" + localSQLException4.getMessage());
    }

    try
    {
      if (localResultSet != null)
        localResultSet.close();
    }
    catch (SQLException localSQLException11)
    {
    	localSQLException11.printStackTrace();
    } finally {
      if (localStatement != null) try {
          localStatement.close();
        } catch (SQLException localSQLException16) {
        	localSQLException16.printStackTrace();
        } finally {
          if (localConnection != null) try {
              localConnection.close();
            } catch (SQLException localSQLException17) {
              localSQLException17.printStackTrace();
            }
        }
    }
  }
}