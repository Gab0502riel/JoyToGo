package org.elis.dao;

import java.sql.SQLException;

import org.elis.model.Ruolo;

public interface RuoloDAO {
    void aggiungiRuolo(Ruolo ruolo) throws SQLException;
    Ruolo findByUtenteId(int idUtente) throws SQLException;

}