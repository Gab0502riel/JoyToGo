package org.elis.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nome;

    // Relazione con Portata (1 categoria -> N portate)
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Portata> portate = new ArrayList<>();

    // Relazione con Ristorante (N categorie <-> N ristoranti)
    @ManyToMany(mappedBy = "categorie")
    private Set<Ristorante> ristoranti = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Portata> getPortate() {
		return portate;
	}

	public void setPortate(List<Portata> portate) {
		this.portate = portate;
	}

	public Set<Ristorante> getRistoranti() {
		return ristoranti;
	}

	public void setRistoranti(Set<Ristorante> ristoranti) {
		this.ristoranti = ristoranti;
	}

    // Getter & Setter
}