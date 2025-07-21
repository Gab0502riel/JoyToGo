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
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(utente);
        et.commit();
    }

    @Override
    public void update(Utente utente) {
       
    }

    @Override
    public void delete(Long id) {
       
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