package org.elis.jdbc;

import org.elis.dao.PortataDao;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcPortataDao implements PortataDao{

	
	private MysqlDataSource dataSource;

	public JdbcPortataDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
}
