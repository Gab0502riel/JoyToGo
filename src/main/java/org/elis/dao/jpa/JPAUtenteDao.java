package org.elis.dao.jpa;

import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

import jakarta.persistence.*;

public class JPAUtenteDao implements UtenteDao {
    private EntityManager em;

    public JPAUtenteDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Utente utente) {
        executeInTransaction(() -> em.persist(utente));
    }

    @Override
    public void update(Utente utente) {
        executeInTransaction(() -> em.merge(utente));
    }

    @Override
    public void delete(Long id) {
        executeInTransaction(() -> {
            Utente utente = em.find(Utente.class, id);
            if (utente != null) em.remove(utente);
        });
    }

    @Override
    public Utente findById(Long id) {
        return em.find(Utente.class, id);
    }

    @Override
    public Utente findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Utente u WHERE u.email = :email", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Utente findByEmailAndPassword(String email, String password) {
        try {
            return em.createQuery(
                "SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password", Utente.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    // Helper per transazioni
    private void executeInTransaction(Runnable operation) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            operation.run();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new RuntimeException("Operazione fallita", e);
        }
    }

	@Override
	public Utente getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> findByRuolo(Ruolo ruolo) {
		// TODO Auto-generated method stub
		return null;
	}
}