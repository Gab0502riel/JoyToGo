package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
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
}

