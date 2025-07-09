package org.elis.dao;

import org.elis.model.Utente;

public interface UtenteDao extends GeneralDao<Utente> {
	
	Utente findByEmailPassword(String email, String password) throws Exception;
	//crei metodo astratto per la query
}
