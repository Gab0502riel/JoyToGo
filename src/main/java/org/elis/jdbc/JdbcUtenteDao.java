package org.elis.jdbc;

import java.sql.Connection;
import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Utente;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcUtenteDao implements UtenteDao{
	
	private MysqlDataSource dataSource;
	
	public JdbcUtenteDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public Utente getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByEmailPassword(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Utente utente) throws Exception {
		try(Connection connection = dataSource.getConnection())
		{
			String query = "INSERT INTO utente(nome, cognome, email, password, sesso) VALUES(?, ?, ?, ?, ?)";
		}
		
	}
	
 // crei il corpo al metodo astratto per la query
}
