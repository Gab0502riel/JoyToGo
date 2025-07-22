package org.elis.dao;

import java.util.List;
import org.elis.model.Categoria;
import org.elis.model.Ristorante;

public interface CategoriaDao extends GeneralDao<Categoria>{
    List<Categoria> trovaPerRistorante(Ristorante ristorante);
    Categoria findById(int id); 

}
