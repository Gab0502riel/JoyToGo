package org.elis.dao;

import java.util.List;
import org.elis.model.Categoria;
import org.elis.model.Ristorante;

public interface CategoriaDao {
    List<Categoria> trovaPerRistorante(Ristorante ristorante);
    Categoria findById(int id); 

}
