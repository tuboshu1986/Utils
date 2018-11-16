package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SqlServerJdbc
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        String connUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=D:\\access\\1506075725563.mdb;PWD=T1340016287934JJ7B51";
        String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
        Properties props = new Properties();
        props.setProperty("charSet", "gbk");//处理中文乱码
        
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(connUrl,props);
        
        PreparedStatement ps = conn.prepareStatement("select top 10 * from [通话_] a");
        ResultSet rs = ps.executeQuery();
        int columnCount = rs.getMetaData().getColumnCount();
        while(rs.next()){
            for (int i = 0; i < columnCount; i++)
            {
                System.out.print(rs.getObject(i+1)+",");
            }
            System.out.println();
        }
        
        conn.close();
    }
}
