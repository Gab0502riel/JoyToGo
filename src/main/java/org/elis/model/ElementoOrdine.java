package org.elis.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento_ordine")
public class ElementoOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantita;

    @Column(name = "data_ora", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataOra = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "id_portata")
    private Portata portata;

    // Costruttori
    public ElementoOrdine() {}

    public ElementoOrdine(Integer quantita, Ordine ordine, Portata portata) {
        this.quantita = quantita;
        this.ordine = ordine;
        this.portata = portata;
        this.dataOra = LocalDateTime.now();
    }

    // Getter e Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Portata getPortata() {
        return portata;
    }

    public void setPortata(Portata portata) {
        this.portata = portata;
    }
}
