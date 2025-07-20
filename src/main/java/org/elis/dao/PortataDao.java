package org.elis.dao;

import java.util.List;
import org.elis.model.Portata;
import org.elis.model.Ristorante;

public interface PortataDao {
    void inserisci(Portata portata);
    void aggiorna(Portata portata);
    void elimina(Portata portata);
    List<Portata> trovaPerRistorante(Ristorante ristorante);
    Portata trovaPerId(int id);
}

