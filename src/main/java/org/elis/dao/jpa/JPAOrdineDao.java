package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

import org.elis.dao.OrdineDao;
import org.elis.model.Ordine;
import org.elis.model.StatoOrdine;

public class JPAOrdineDao implements OrdineDao {

    private EntityManager em;

    public JPAOrdineDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Ordine> findByRistoranteAndStato(long idRistorante, StatoOrdine stato) {
        TypedQuery<Ordine> query = em.createQuery(
            "SELECT o FROM Ordine o WHERE o.ristorante.id = :idRistorante AND o.stato = :stato", Ordine.class);
        query.setParameter("idRistorante", idRistorante);
        query.setParameter("stato", stato);
        return query.getResultList();
    }

    @Override
    public List<Ordine> findByRistorante(long idRistorante) {
        TypedQuery<Ordine> query = em.createQuery(
            "SELECT o FROM Ordine o WHERE o.ristorante.id = :idRistorante", Ordine.class);
        query.setParameter("idRistorante", idRistorante);
        return query.getResultList();
    }
    
    @Override
    public List<Ordine> findByUtente(long idUtente) {
        TypedQuery<Ordine> query = em.createQuery(
            "SELECT o FROM Ordine o WHERE o.utente.id = :idUtente ORDER BY o.dataOra DESC", Ordine.class);
        query.setParameter("idUtente", idUtente);
        return query.getResultList();
    }


    @Override
    public boolean cambiaStato(long idOrdine, StatoOrdine nuovoStato) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ordine ordine = em.find(Ordine.class, idOrdine);
            if (ordine != null) {
                ordine.setStato(nuovoStato);
                em.merge(ordine);
                tx.commit();
                return true;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Integer getLastId() {
    	Query q = em.createQuery("Select o.id from Ordine o ORDER BY id DESC LIMIT 1");
    	return (Integer)q.getSingleResult();
    }
    

	@Override
	public void insert(Ordine t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(t);
		et.commit();
	}

	@Override
	public void delete(Ordine t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(t);
		et.commit();
		
	}

	@Override
	public Ordine getById(Long id) throws Exception {
		return em.find(Ordine.class, id);
	}

	@Override
	public List<Ordine> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
