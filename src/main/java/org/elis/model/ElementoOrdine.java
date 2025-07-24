package org.elis.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento_ordine")
public class ElementoOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Integer quantita;
    private Double prezzo;
   

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
    }

    // Getter e Setter
    
    
    
    public Integer getId() {
        return id;
    }

    public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
