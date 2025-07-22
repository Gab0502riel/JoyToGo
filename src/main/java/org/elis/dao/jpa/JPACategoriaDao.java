package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

import org.elis.dao.CategoriaDao;
import org.elis.model.Categoria;
import org.elis.model.Ristorante;

public class JPACategoriaDao implements CategoriaDao {

    private EntityManager em;

    public JPACategoriaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Categoria> trovaPerRistorante(Ristorante ristorante) {
        TypedQuery<Categoria> query = em.createQuery(
            "SELECT c FROM Categoria c WHERE c.ristorante = :ristorante", Categoria.class);
        query.setParameter("ristorante", ristorante);
        return query.getResultList();
    }
    @Override
    public Categoria findById(int id) {
        return em.find(Categoria.class, id);
    }

	@Override
	public void insert(Categoria t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(t);
		et.commit();
	}

	@Override
	public void delete(Categoria t) throws Exception {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(t);
		et.commit();
		
	}

	@Override
	public Categoria getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

