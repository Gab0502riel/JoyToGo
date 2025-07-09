package org.elis.jdbc;

import org.elis.dao.OrdineDao;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcOrdineDao implements OrdineDao{
	private MysqlDataSource dataSource;

	public JdbcOrdineDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	
}
