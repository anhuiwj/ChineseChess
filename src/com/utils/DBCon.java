package com.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	private static String driverClass = null; // 数据库驱动class
	private static String url = null; // 数据库url
	private static String username = null;// 数据库用户名
	private static String password = null;// 数据库密码
	private static Connection conn = null;

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Properties prop = new Properties();
			prop.load(DBCon.class.getResourceAsStream("/jdbc.properties"));
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			Class.forName(driverClass); // 加载驱动程序
			conn = DriverManager.getConnection(url, username, password); // 创建数据库连接
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
