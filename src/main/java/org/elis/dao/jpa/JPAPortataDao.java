package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.elis.dao.PortataDao;
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
    public List<Portata> trovaPerRistorante(Ristorante ristorante) {
        TypedQuery<Portata> query = em.createQuery(
            "SELECT p FROM Portata p WHERE p.ristorante = :ristorante", Portata.class);
        query.setParameter("ristorante", ristorante);
        return query.getResultList();
    }


    @Override
    public Portata trovaPerId(int id) {
        return em.find(Portata.class, id);
    }
}

