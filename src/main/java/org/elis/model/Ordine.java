package org.elis.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StatoOrdine stato;

    private String rTesto;
    private Integer rVoto;

    @ManyToOne
    @JoinColumn(name = "id_ristorante")
    private Ristorante ristorante;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<ElementoOrdine> elementi = new ArrayList<>();

    // --- GETTER e SETTER ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    public String getRTesto() {
        return rTesto;
    }

    public void setRTesto(String rTesto) {
        this.rTesto = rTesto;
    }

    public Integer getRVoto() {
        return rVoto;
    }

    public void setRVoto(Integer rVoto) {
        this.rVoto = rVoto;
    }

    public Ristorante getRistorante() {
        return ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<ElementoOrdine> getElementi() {
        return elementi;
    }

    public void setElementi(List<ElementoOrdine> elementi) {
        this.elementi = elementi;
    }
}
