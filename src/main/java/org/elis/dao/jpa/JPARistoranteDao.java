package org.elis.dao.jpa;

import java.util.List;

import org.elis.dao.RistoranteDao;
import org.elis.model.Categoria;
import org.elis.model.Ordine;
import org.elis.model.Portata;
import org.elis.model.Ristorante;
import org.elis.model.StatoOrdine;
import org.elis.model.Utente;

import jakarta.persistence.*;

public class JPARistoranteDao implements RistoranteDao {
    private final EntityManager em;
    
    public JPARistoranteDao(EntityManager em) {
        this.em = em;
    }
    
    private void executeInTransaction(Runnable operation) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            operation.run();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Operazione fallita", e);
        }
    }
    
    @Override
    public boolean registraRistorante(Ristorante r, long idProprietario) {
        executeInTransaction(() -> {
            Utente proprietario = em.find(Utente.class, idProprietario);
            if (proprietario == null) throw new IllegalArgumentException("Utente non trovato");
            r.setProprietario(proprietario);
            em.persist(r);
        });
        return true;
    }
    
    @Override
    public boolean aggiungiPortata(long idRistorante, Portata p) {
        executeInTransaction(() -> {
            Ristorante r = em.find(Ristorante.class, idRistorante);
            if (r == null) throw new IllegalArgumentException("Ristorante non trovato");
            p.setRistorante(r);
            em.persist(p);
        });
        return true;
    }
    
    @Override
    public boolean rimuoviPortata(long idPortata) {
        executeInTransaction(() -> {
            Portata p = em.find(Portata.class, idPortata);
            if (p != null) em.remove(p);
        });
        return true;
    }
    
    @Override
    public boolean aggiungiCategoria(long idRistorante, long idCategoria) {
        executeInTransaction(() -> {
            Ristorante r = em.find(Ristorante.class, idRistorante);
            Categoria c = em.find(Categoria.class, idCategoria);
            if (r != null && c != null) {
                r.getCategorie().add(c);
                em.merge(r);
            }
        });
        return true;
    }
    
    @Override
    public boolean rimuoviCategoria(long idRistorante, long idCategoria) {
        executeInTransaction(() -> {
            Ristorante r = em.find(Ristorante.class, idRistorante);
            Categoria c = em.find(Categoria.class, idCategoria);
            if (r != null && c != null) {
                r.getCategorie().remove(c);
                em.merge(r);
            }
        });
        return true;
    }
    
    @Override
    public boolean cambiaStatoOrdine(long idOrdine, StatoOrdine nuovoStato) {
        executeInTransaction(() -> {
            Ordine o = em.find(Ordine.class, idOrdine);
            if (o != null) {
                o.setStato(nuovoStato);
                em.merge(o);
            }
        });
        return true;
    }
    
    @Override
    public List<Ordine> getOrdini(long idRistorante) {
        return em.createQuery(
            "SELECT o FROM Ordine o WHERE o.ristorante.id = :idRistor", 
            Ordine.class)
            .setParameter("idRistor", idRistorante)
            .getResultList();
    }

}