package com.armon.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestJdbc {
  // mysql
  // static String driverName = "com.mysql.jdbc.Driver";
  // static String jdbcUrl = "jdbc:mysql://10.11.128.51:3306/sentinel";
  // static String user = "root";
  // static String passwd = "mogujie";

  // hive
   static String driverName = "org.apache.hive.jdbc.HiveDriver";
   static String jdbcUrl = "jdbc:hive2://10.11.128.54:10000/testdb";
   static String user = "hive";
   static String passwd = "hive";

  // presto
//  static String driverName = "com.facebook.presto.jdbc.PrestoDriver";
//  static String jdbcUrl = "jdbc:presto://10.15.5.155:8080/hive/default";
//  static String user = "data";
//  static String passwd = "data";

  public static void main(String args[]) throws ClassNotFoundException,
      SQLException {

    Class.forName(driverName);
    Connection connection = DriverManager.getConnection(jdbcUrl, user, passwd);
//    String line = "select * from tmp_wuya_test_p where visit_date <> '$' ";
    String line = "use testdb; select * from a2; select * from a2 where c2=\'123;123\'";
    String[] cmds = splitHiveScript(line);
    for (String sql : cmds) {
      Statement statement = connection.createStatement();
      boolean hasResults = statement.execute(sql.trim());
      if (hasResults) {
        ResultSet rs = statement.getResultSet();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
          System.out.print(rs.getString(1));
          for (int j = 2; j <= columnCount; j++) {
            System.out.print(" " + rs.getString(j));
          }
          System.out.println();
        }
      }
    }
  }

  private static String[] splitHiveScript(String hql) {
    if (hql == null || hql.trim().isEmpty()) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    String[] lines = hql.split("\n");
    for (String line : lines) {
      line = line.trim();
      // 去除注释语句（以#开头并已#结尾）
      if (!line.isEmpty() && !line.startsWith("#") && !line.endsWith("#")
          && !line.startsWith("--")) {
        sb.append(line);
        sb.append("\n");
      }
    }

    // 块注释处理_追加(muming 2014.08.28)
    String sql = sb.toString().replaceAll("(?s)/\\*.*?\\*/", "");
    // 追加结束

    String replacedHql = sql;
    List<String> list = new ArrayList<String>();
    int cursor = 0;
    int flag = 0;
    char mask = '0';
    for (int index = 0, len = replacedHql.length(); index < len; index++) {
      char c = replacedHql.charAt(index);
      if (c == ';') {
        if (flag == 0) {
          String cmd = replacedHql.substring(cursor, index).trim();
          cursor = index + 1;
          if (!StringUtil.isBlank(cmd) && !cmd.startsWith("--")
              && !cmd.toUpperCase().replace("\t", " ").startsWith("SET ")) {
            list.add(cmd);
          }
        }
      } else if (c == '"' || c == '\'') {
        if (flag == 0) {
          mask = c;
          flag++;
        } else if (mask == c
            && (index == 0 || (index - 1 >= 0 && replacedHql.charAt(index - 1) != '\\'))) {
          flag = 0;
        }
      }
    }

    if (cursor < replacedHql.length()) {
      String cmd = replacedHql.substring(cursor, replacedHql.length()).trim();
      if (!cmd.isEmpty() && !cmd.startsWith("--")
          && !cmd.toUpperCase().replace("\t", " ").startsWith("SET ")) {
        list.add(cmd);
      }
    }

    return list.toArray(new String[list.size()]);
  }
}
