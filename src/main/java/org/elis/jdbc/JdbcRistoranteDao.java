package org.elis.jdbc;

import org.elis.dao.RistoranteDao;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcRistoranteDao implements RistoranteDao{
	private MysqlDataSource dataSource;

	public JdbcRistoranteDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	
}
