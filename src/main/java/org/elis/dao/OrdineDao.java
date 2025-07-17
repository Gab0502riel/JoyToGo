package org.elis.dao;

import java.util.List;

import org.elis.model.Ordine;
import org.elis.model.StatoOrdine;

public interface OrdineDao {

	List<Ordine> findByRistoranteAndStato(long idRistorante, StatoOrdine confermato);

	List<Ordine> findByRistorante(long idRistorante);

	boolean cambiaStato(long idOrdine, StatoOrdine nuovoStato);

}
