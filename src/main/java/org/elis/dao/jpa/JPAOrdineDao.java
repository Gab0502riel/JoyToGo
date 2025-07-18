package org.elis.dao.jpa;

import jakarta.persistence.EntityManager;
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
    public boolean cambiaStato(long idOrdine, StatoOrdine nuovoStato) {
        Ordine ordine = em.find(Ordine.class, idOrdine);
        if (ordine != null) {
            ordine.setStato(nuovoStato);
            em.merge(ordine);
            return true;
        }
        return false;
    }
}
