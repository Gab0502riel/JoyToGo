package org.elis.dao.jpa;

import org.elis.dao.CategoriaDao;
import org.elis.dao.jpa.JPACategoriaDao;

import org.elis.dao.DaoFactory;
import org.elis.dao.OrdineDao;
import org.elis.dao.PortataDao;
import org.elis.dao.RistoranteDao;
import org.elis.dao.UtenteDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPADaoFactory extends DaoFactory {
    private static JPADaoFactory instance;
    private final EntityManagerFactory emf;
    
    private JPADaoFactory() {
        this.emf = Persistence.createEntityManagerFactory("joytogoPU");
    }
    
    public static synchronized JPADaoFactory getInstance() {
        if(instance == null) {
            instance = new JPADaoFactory();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public UtenteDao getUtenteDao() {
        return new JPAUtenteDao(getEntityManager());
    }
    
    @Override
    public RistoranteDao getRistoranteDao() {
        return new JPARistoranteDao(getEntityManager());
    }
    
    @Override
    public OrdineDao getOrdineDao() {
        return new JPAOrdineDao(getEntityManager());
    }

    
    @Override
    public PortataDao getPortataDao() {
        return new JPAPortataDao(getEntityManager());
    }
    
    public CategoriaDao getCategoriaDao() {
    	return new JPACategoriaDao(getEntityManager());
    }
    
    public void close() {
        if(emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}