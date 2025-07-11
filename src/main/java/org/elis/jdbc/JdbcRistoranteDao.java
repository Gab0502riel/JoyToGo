package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.elis.dao.RistoranteDao;
import org.elis.model.Ristorante;
import org.elis.model.Utente;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Statement;

public class JdbcRistoranteDao implements RistoranteDao{
	
	private MysqlDataSource dataSource;
	
	int idUtente;

	public JdbcRistoranteDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}


	@Override
	public void insert(Utente utente) throws Exception {
		try(Connection connection = dataSource.getConnection()){
			String queryUtente = "INSERT INTO utente(nome, cognome, email, password,sesso, id_ruolo) VALUES(?, ?, ?, ?, ?, 3)";
			PreparedStatement psUtente = connection.prepareStatement(queryUtente, Statement.RETURN_GENERATED_KEYS);
			psUtente.setString(1, utente.getNome());
			psUtente.setString(2, utente.getCognome());
			psUtente.setString(3, utente.getEmail());
			psUtente.setString(4, utente.getPassword());
			psUtente.setString(5, utente.getSesso().name());
			psUtente.executeUpdate();
			
			 int affectedRows = psUtente.executeUpdate();

			    if (affectedRows == 0) {
			        throw new SQLException("Inserimento utente fallito, nessuna riga modificata.");
			    }

			    try (ResultSet generatedKeys = psUtente.getGeneratedKeys()) {
			        if (generatedKeys.next()) {
			            idUtente = generatedKeys.getInt(1);
			        } else {
			            throw new SQLException("Inserimento utente fallito, nessun ID ottenuto.");
			        }
			    }

			} catch (SQLException e) {
			    e.printStackTrace();
			    throw new RuntimeException("Errore durante l'inserimento dell'utente", e);
			}
		}
	
	
	@Override
	public void insert(Ristorante ristorante) throws Exception {
		try(Connection connection = dataSource.getConnection()){
			String queryRistorante = "INSERT INTO ristorante(nome, telefono, indirizzo, citta, id_utente) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(queryRistorante);
			ps.setString(1, ristorante.getNome());
			ps.setString(2, ristorante.getTelefono());
			ps.setString(3, ristorante.getIndirizzo());
			ps.setString(4, ristorante.getCitta());
			ps.setInt(5, idUtente);
			ps.executeUpdate();
		}
		
	}

	@Override
	public Ristorante getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ristorante> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
