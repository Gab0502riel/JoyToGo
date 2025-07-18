package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
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
        em.persist(portata);
    }

    @Override
    public void aggiorna(Portata portata) {
        em.merge(portata);
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

