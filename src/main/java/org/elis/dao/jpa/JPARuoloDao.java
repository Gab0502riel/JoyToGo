package org.elis.dao.jpa;

import org.elis.dao.RuoloDao;
import org.elis.model.Ruolo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

public class JPARuoloDao implements RuoloDao {
    private EntityManager em;

    public JPARuoloDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Ruolo findByUtenteId(long utenteId) {
        try {
            return em.createQuery(
                    "SELECT u.ruolo FROM Utente u WHERE u.id = :id", Ruolo.class)
                    .setParameter("id", utenteId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void aggiungiRuolo(Ruolo ruolo) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ruolo);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante inserimento del ruolo", e);
        }
    }
    
    @Override
    public Ruolo findByNome(String nome) {
        try {
            return em.createQuery("SELECT r FROM Ruolo r WHERE r.nome = :nome", Ruolo.class)
                     .setParameter("nome", nome)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

