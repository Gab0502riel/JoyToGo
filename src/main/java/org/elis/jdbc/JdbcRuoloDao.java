package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.elis.dao.RuoloDao;
import org.elis.model.Ruolo;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JdbcRuoloDao implements RuoloDao {
	
	private MysqlDataSource dataSource;

    public JdbcRuoloDao(MysqlDataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	@Override
    public void aggiungiRuolo(Ruolo ruolo) throws SQLException {
        String sql = "INSERT INTO ruolo (nome, id_utente) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ruolo.getNome());
            stmt.setInt(2, ruolo.getIdUtente());
            stmt.executeUpdate();
        }
    }
    @Override
    public Ruolo findByUtenteId(int idUtente) throws SQLException {
        String sql = "SELECT * FROM ruolo WHERE id_utente = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUtente);
            var rs = ps.executeQuery();

            if (rs.next()) {
                Ruolo ruolo = new Ruolo();
                ruolo.setId(rs.getInt("id"));
                ruolo.setNome(rs.getString("nome"));
                ruolo.setIdUtente(rs.getInt("id_utente"));
                return ruolo;
            }

        } catch (SQLException e) {
            throw new SQLException("Errore nel recupero del ruolo per l'utente con ID: " + idUtente, e);
        }

        return null;
    }
}
