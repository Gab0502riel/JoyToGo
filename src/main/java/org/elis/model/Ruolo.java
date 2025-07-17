package org.elis.model;

public enum Ruolo {
    ADMIN,
    UTENTE,
    RISTORATORE;
   
    public String getNome() {
        return name();
    }
}