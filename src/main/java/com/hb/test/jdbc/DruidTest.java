package com.hb.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidTest extends Thread {
	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test(){
		for(int i=0;i<200;i++){
			new DruidTest().start();
		}
	}
	
	private OracleDataSource dataSource = new OracleDataSource();
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			try {
				addData(dataSource.getConn());
				Thread.sleep((long)(1000*Math.random()));
			} catch (SQLException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addData(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
		PreparedStatement stst = conn.prepareStatement("insert into a values('1','bbb','女')");
		stst.execute();
		conn.commit();
		System.out.println("增加1个");
	}
	
}

/**
 * 连接到oracle数据库
  * @date 2018年9月20日 上午11:05:18
 */
class OracleDataSource {
	private DruidDataSource dataSource;
	
	public OracleDataSource(){
		dataSource = new DruidDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUsername("HB_TEST_DB_1");
		dataSource.setPassword("tiger");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		dataSource.setMinIdle(1);
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(50);
	}
	
	public Connection getConn() throws SQLException{
		return dataSource.getConnection(50000).getConnection();
	}
	
}
