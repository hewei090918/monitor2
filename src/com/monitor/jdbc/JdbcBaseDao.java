package com.monitor.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcBaseDao {
	/** 返回一个与特定数据库的连接 */
	public static Connection getConnection() {
		String driverClassName = null;  
        String url = null;  
        String username = null;  
        String password = null;  
          
        //读取 jdbc.properties 文件  
        InputStream in = JdbcBaseDao.class.getResourceAsStream("db.properties");  
        Properties properties = new Properties();  
        try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
        driverClassName = properties.getProperty("driverClassName");  
        url = properties.getProperty("url");  
        username = properties.getProperty("username");  
        password = properties.getProperty("password");
        
        //方式一
        Connection connection = null;
        try {
        	//加载数据库驱动
			Class.forName(driverClassName);
			//获取数据库连接
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        //方式二
//        Driver driver = null;
//        Connection connection = null;
//		try {
//			driver = (Driver) Class.forName(driverClassName).newInstance();
//			Properties info = new Properties();  
//	        info.put("user", username);  
//	        info.put("password", password);  
//	        //通过 Driver 的 connect 方法获取数据库连接.  
//	        connection = driver.connect(url, info);
//	        
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}  
		
        return connection; 
	}
	
}
