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
    public void insert(Ristorante ristorante) {
    	EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(ristorante);
		et.commit();
    }
    
    @Override
    public void aggiorna(Ristorante ristorante) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(ristorante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante l'aggiornamento del ristorante", e);
        }
    }

    
    @Override
    public List<Ristorante> findAll() {
        return em.createQuery("SELECT r FROM Ristorante r", Ristorante.class).getResultList();
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

	@Override
	public void delete(Ristorante t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ristorante getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ristorante> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ristorante findByIndirizzo(String indirizzo) {
		Query q = em.createQuery("select r from Ristorante r where r.indirizzo=:indirizzo");
		q.setParameter("indirizzo", indirizzo);
		return (Ristorante)q.getSingleResult();
	}

}