package org.elis.dao;

import java.util.List;

import org.elis.model.Categoria;
import org.elis.model.Portata;
import org.elis.model.Ristorante;

public interface PortataDao extends GeneralDao<Portata>{
    void inserisci(Portata portata);
    void aggiorna(Portata portata);
    void elimina(Portata portata);
    List<Portata> findByCategoria(Categoria c);
    Portata trovaPerId(int id);
}

