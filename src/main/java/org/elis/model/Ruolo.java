package org.elis.model;

public class Ruolo {
    private int id;
    private String nome; // "Admin", "Utente", "Ristoratore"
    private int idUtente;

    public Ruolo() {}

    public Ruolo(String nome, int idUtente) {
        this.nome = nome;
        this.idUtente = idUtente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
