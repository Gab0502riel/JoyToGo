package org.elis.jdbc;

import org.elis.dao.DaoFactory;
import org.elis.dao.OrdineDao;
import org.elis.dao.PortataDao;
import org.elis.dao.RistoranteDao;
import org.elis.dao.UtenteDao;

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
			dataSource.setDatabaseName("joytogodb");
		}
		return dataSource;
	}

	@Override
	public UtenteDao getUtenteDao() {
		return new JdbcUtenteDao(getDataSource());
	}

	@Override
	public RistoranteDao getRistoranteDao() {
		return new JdbcRistoranteDao(getDataSource());
	}

	@Override
	public OrdineDao getOrdineDao() {
		return new JdbcOrdineDao(getDataSource());
	}

	@Override
	public PortataDao getPortataDao() {
		return new JdbcPortataDao(getDataSource());
	}
	
}
