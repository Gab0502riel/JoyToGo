package org.elis.dao;

import java.util.List;
import org.elis.model.Categoria;
import org.elis.model.Ordine;
import org.elis.model.Portata;
import org.elis.model.Ristorante;
import org.elis.model.StatoOrdine;
import org.elis.model.Utente;

public interface RistoranteDao {
    boolean registraRistorante(Ristorante r, long idProprietario);
    boolean aggiungiPortata(long idRistorante, Portata p);
    boolean rimuoviPortata(long idPortata);
    boolean aggiungiCategoria(long idRistorante, long idCategoria);
    boolean rimuoviCategoria(long idRistorante, long idCategoria);
    boolean cambiaStatoOrdine(long idOrdine, StatoOrdine nuovoStato);
    List<Ordine> getOrdini(long idRistorante);
    List<Ristorante> findAll();
    void insert(Ristorante ristorante);
    void aggiorna(Ristorante ristorante);

}