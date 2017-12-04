package com.monitor.jdbc;

/**
 * 
 * 包名：com.mop.jdbc <br/>
 * 类名：JdbcTest.java <br/>
 * 版本：version 1.0 <br/>
 * 作者：HeWei <br/>
 * 描述：测试数据库是否正常连接
 */
public class JdbcTest {

	public static void main(String[] args) {
		System.out.println(JdbcBaseDao.getConnection());
	}

}
