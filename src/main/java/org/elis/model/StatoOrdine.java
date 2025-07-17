package org.elis.model;

public enum StatoOrdine {
    IN_ATTESA_DI_CONFERMA("In attesa di conferma"),
    CONFERMATO("Confermato"),
    ANNULLATO("Annullato"),
    RITIRATO("Ritirato"),
    RIFIUTATO("Rifiutato");

    private final String descrizione;

    StatoOrdine(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}