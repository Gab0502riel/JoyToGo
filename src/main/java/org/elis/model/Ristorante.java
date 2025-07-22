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
    private String telefono;
    private String citta;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente proprietario;

    @OneToMany(mappedBy="ristorante")
    private List<Categoria> categorie = new ArrayList<>();

    @OneToMany(mappedBy = "ristorante")
    private List<Ordine> ordini = new ArrayList<>();

    // --- GETTER e SETTER ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public Utente getProprietario() { return proprietario; }
    public void setProprietario(Utente proprietario) { this.proprietario = proprietario; }

    public List<Categoria> getCategorie() { return categorie; }
    public void setCategorie(List<Categoria> categorie) { this.categorie = categorie; }

   
    public List<Ordine> getOrdini() { return ordini; }
    public void setOrdini(List<Ordine> ordini) { this.ordini = ordini; }
}
