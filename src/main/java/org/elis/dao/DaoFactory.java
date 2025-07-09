package org.elis.dao;

import org.elis.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
	private static DaoFactory instance;
	private static final String impl = "Jdbc";
	
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
