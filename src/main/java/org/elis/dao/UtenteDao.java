package org.elis.dao;

import java.util.List;

import org.elis.model.Ruolo;
import org.elis.model.Utente;

public interface UtenteDao extends GeneralDao<Utente> {
	void insert(Utente utente);
    void update(Utente utente);
    void delete(Long id);
    Utente findById(Long id);
    Utente findByEmail(String email);
    List<Utente> findByRuolo(Ruolo ruolo);
    Utente findByEmailAndPassword(String email, String password);

}