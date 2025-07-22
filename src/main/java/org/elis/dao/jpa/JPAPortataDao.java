package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import org.elis.dao.PortataDao;
import org.elis.model.Categoria;
import org.elis.model.Portata;
import org.elis.model.Ristorante;

public class JPAPortataDao implements PortataDao {

    private EntityManager em;

    public JPAPortataDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void inserisci(Portata portata) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(portata);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Errore durante l'inserimento della portata", e);
        }
    }

    @Override
    public void elimina(Portata portata) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Portata toDelete = em.merge(portata); // Assicura che l'entità sia managed
            em.remove(toDelete);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Errore durante l'eliminazione della portata", e);
        }
    }



    @Override
    public void aggiorna(Portata portata) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(portata);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Errore durante l'aggiornamento della portata", e);
        }
    }


    @Override
    public Portata trovaPerId(int id) {
        return em.find(Portata.class, id);
    }

	@Override
	public void insert(Portata t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(t);
		et.commit();
		
	}

	@Override
	public void delete(Portata t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(t);
		et.commit();
		
	}

	@Override
	public Portata getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Portata> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Portata> findByCategoria(Categoria c) {
		List<Portata> portate = new ArrayList<>();
		Query q = em.createQuery("SELECT c FROM Categoria c WHERE c.id = :id");
			q.setParameter("id", c.getId());
			Categoria cat = (Categoria)q.getSingleResult();
			portate.addAll(cat.getPortate());
		return portate;
	}
}

