package org.elis.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    // Relazione: Molte categorie appartengono a un solo ristorante
    @ManyToOne
    @JoinColumn(name = "id_ristorante", nullable = false)
    private Ristorante ristorante;

    // Relazione: Una categoria può avere molte portate
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Portata> portate = new ArrayList<>();

    
    
    public Categoria() {
		
	}

	// --- GETTER E SETTER ---

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

    public Ristorante getRistorante() {
        return ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    public List<Portata> getPortate() {
        return portate;
    }

    public void setPortate(List<Portata> portate) {
        this.portate = portate;
    }
}
