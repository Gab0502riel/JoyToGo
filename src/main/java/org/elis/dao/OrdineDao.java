package org.elis.dao;

import java.util.List;

import org.elis.model.Ordine;
import org.elis.model.StatoOrdine;

public interface OrdineDao extends GeneralDao<Ordine>{

	List<Ordine> findByRistoranteAndStato(long idRistorante, StatoOrdine confermato);

	List<Ordine> findByRistorante(long idRistorante);
	
	List<Ordine> findByUtente(long idUtente);


	boolean cambiaStato(long idOrdine, StatoOrdine nuovoStato);

}
