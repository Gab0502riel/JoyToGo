package org.elis.dao;

import org.elis.model.Categoria;
import org.elis.model.Ristorante;
import org.elis.model.Utente;
public interface RistoranteDao extends GeneralDao<Ristorante>{
	
	void insert(Ristorante ristorante) throws Exception;
	void insert(Utente utente) throws Exception;
	void insert(Categoria categoria) throws Exception;
}
