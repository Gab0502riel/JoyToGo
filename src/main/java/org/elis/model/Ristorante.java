package org.elis.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Ristorante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String indirizzo;
    
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente proprietario;
    
    @ManyToMany
    @JoinTable(
        name = "ristorante_categoria",
        joinColumns = @JoinColumn(name = "id_ristorante"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorie = new HashSet<>();
    
    @OneToMany(mappedBy = "ristorante")
    private List<Portata> portate = new ArrayList<>();
    
    @OneToMany(mappedBy = "ristorante")
    private List<Ordine> ordini = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Utente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Utente proprietario) {
		this.proprietario = proprietario;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	public List<Portata> getPortate() {
		return portate;
	}

	public void setPortate(List<Portata> portate) {
		this.portate = portate;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
    
    
}