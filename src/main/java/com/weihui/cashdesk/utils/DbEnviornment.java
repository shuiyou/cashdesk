package com.weihui.cashdesk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbEnviornment {

    public static Connection con;
    public static Statement statement;
    static String host;
    static String database;
    static String username;
    static String pwd;


    public synchronized boolean Connect(String host, String database, String username, String pwd) {
        OraclePasswd oPasswd = new OraclePasswd();
        DbEnviornment.host = host;
        DbEnviornment.database = database;
        DbEnviornment.username = username;
      /*  if (username.toUpperCase().equals("LIANTIAO")) {
            DbEnviornment.username = "MAYUNFEI";
        }*/

        DbEnviornment.pwd = oPasswd.getPasswd(DbEnviornment.username);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        String url = "jdbc:oracle:thin:@" + DbEnviornment.host + ":" + DbEnviornment.database;
        try {
            con = DriverManager.getConnection(url, DbEnviornment.username, DbEnviornment.pwd);
            statement = con.createStatement(1004, 1008);
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return (statement != null);
    }

    private synchronized boolean ConnectToMysql(String host, String database, String username, String pwd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://" + host + "/" + database;
        try {
            con = DriverManager.getConnection(url, username, pwd);
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (statement != null);
    }

    private synchronized boolean ConnectToSqlServer(String host, String database, String username, String pwd) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://" + host + ";" + "DatabaseName=" + database;
        try {
            con = DriverManager.getConnection(url, username, pwd);
            statement = con.createStatement(1005, 1007);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (statement != null);
    }

}
