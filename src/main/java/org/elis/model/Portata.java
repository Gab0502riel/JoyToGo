package org.elis.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "portata")
public class Portata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nome;
    
    private double prezzo;
    private String descrizione;
    private String foto;
    
    @Column(name = "senza_glutine")
    private boolean senzaGlutine;
    
    @Column(name = "senza_lattosio")
    private boolean senzaLattosio;
    
    private boolean surgelato;

    // Relazione Many-to-One con Categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    
    // Relazione Many-to-One con Ristorante (aggiunto)
    @ManyToOne
    @JoinColumn(name = "id_ristorante")
    private Ristorante ristorante;

    // Relazione One-to-Many con ElementoOrdine
    @OneToMany(mappedBy = "portata")
    private List<ElementoOrdine> elementiOrdine = new ArrayList<>();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public boolean isSenzaGlutine() {
		return senzaGlutine;
	}
	public void setSenzaGlutine(boolean senzaGlutine) {
		this.senzaGlutine = senzaGlutine;
	}
	public boolean isSenzaLattosio() {
		return senzaLattosio;
	}
	public void setSenzaLattosio(boolean senzaLattosio) {
		this.senzaLattosio = senzaLattosio;
	}
	public boolean isSurgelato() {
		return surgelato;
	}
	public void setSurgelato(boolean surgelato) {
		this.surgelato = surgelato;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
    public Ristorante getRistorante() {
        return ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }
	
	public List<ElementoOrdine> getElementiOrdine() {
		return elementiOrdine;
	}
	public void setElementiOrdine(List<ElementoOrdine> elementiOrdine) {
		this.elementiOrdine = elementiOrdine;
	}  
    
}
	