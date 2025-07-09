package org.elis.dao;

import org.elis.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
	private static DaoFactory instance;
	private static final String impl = "Jdbc";
	
	public abstract UtenteDao getUtenteDao();
	public abstract RistoranteDao getRistoranteDao();
	public abstract OrdineDao getOrdineDao();
	public abstract PortataDao getPortataDao();
	
	public static DaoFactory getDaoFactory() {
		if(instance==null)
		{
			switch(impl)
			{
			case "Jdbc":
				instance = JdbcDaoFactory.getInstance();
				break;
			}
		}
		return instance;
	}
}
