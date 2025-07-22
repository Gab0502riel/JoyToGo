package org.elis.dao;

import java.sql.SQLException;

import org.elis.model.Ruolo;

public interface RuoloDao extends GeneralDao<Ruolo>{
    void aggiungiRuolo(Ruolo ruolo) throws SQLException;
    Ruolo findByUtenteId(long utenteId) throws SQLException;
    Ruolo findByNome(String nome);


}