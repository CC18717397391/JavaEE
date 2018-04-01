package com.yizhan.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;


public class DbUtil {
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
	
	//连接池对象
	private static DataSource dataSource = null;
	
	//将对象和线程绑定
	private static ThreadLocal<Connection> connLocal = new ThreadLocal<Connection>();
	
	static{
		try{
			Properties props = new Properties();
			//加载配置参数
			props.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			//用dbcp组件创建DataSource对象
			dataSource = BasicDataSourceFactory.createDataSource(props);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*获取已有的connection*/
	public static Connection getConnection() throws SQLException{
		Connection conn = connLocal.get();
		if(conn == null || conn.isClosed()){
			conn = dataSource.getConnection();
			connLocal.set(conn);
		}
		return conn;	
	}
	
	/*自动关闭connection*/
	public static void closeConnection() {
		//获取和当前线程相关的Connection
		Connection conn = connLocal.get();
		//清除和线程绑定的conn
		connLocal.set(null);
		//为下一次调用getConnection要新建
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(PreparedStatement stm,ResultSet rs){
		try {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/*手动关闭connection*/
	public static void close(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}
	
	/*回退*/
	public static void rollback() throws SQLException{
		//获取和当前线程相关的Connection
		Connection conn = connLocal.get();
		if(conn!=null){
			conn.rollback();
		}
	}
	
}