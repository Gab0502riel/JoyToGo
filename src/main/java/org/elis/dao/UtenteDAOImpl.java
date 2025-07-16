package org.elis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.elis.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {

    public int salvaUtente(Utente u) throws Exception {
        String sql = "INSERT INTO utente(nome, cognome, email, password, sesso, foto) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connessione.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getSesso());
            ps.setString(6, u.getFoto());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // id utente generato
            }

        } catch (SQLException e) {
            throw new Exception("Errore nel salvataggio utente: " + e.getMessage(), e);
        }

        return -1;
    }

    public Utente findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM utente WHERE email = ?";
        try (Connection conn = Connessione.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setSesso(rs.getString("sesso"));
                u.setFoto(rs.getString("foto"));
                return u;
            }

        } catch (SQLException e) {
            throw new Exception("Errore nella ricerca dell'utente", e);
        }
        return null;
    }

    // Nuovo metodo per login con email e password
    @Override
    public Utente findByEmailAndPassword(String email, String password) throws Exception {
        String sql = "SELECT * FROM utente WHERE email = ? AND password = ?";
        try (Connection conn = Connessione.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setSesso(rs.getString("sesso"));
                u.setFoto(rs.getString("foto"));
                return u;
            }

        } catch (SQLException e) {
            throw new Exception("Errore nella ricerca dell'utente per email e password", e);
        }
        return null;
    }


}

