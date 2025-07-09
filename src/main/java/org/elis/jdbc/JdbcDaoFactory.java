package org.elis.jdbc;

import org.elis.dao.DaoFactory;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcDaoFactory extends DaoFactory{
	
	private static JdbcDaoFactory instance;
	
	private JdbcDaoFactory() {
		super();
	}
	
	public static JdbcDaoFactory getInstance() {
		if(instance==null)instance = new JdbcDaoFactory();
		return instance;
	}
	
	private static MysqlDataSource dataSource;
	private static MysqlDataSource getDataSource() {
		if(dataSource==null)
		{
			dataSource=new MysqlDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setServerName("localhost");
			dataSource.setDatabaseName("joytogoDB");
		}
		return dataSource;
	}
	
}
