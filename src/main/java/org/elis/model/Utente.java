package org.elis.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cognome;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('M', 'F', 'A')")
    private Sesso sesso;

    private String foto;

    private Ruolo ruolo;
    
    @OneToOne(mappedBy = "proprietario")
    private Ristorante ristorante;
    
    @OneToMany(mappedBy="utente")
    private List<Ordine> ordini;
    
    // --- GETTER E SETTER ---

    public long getId() { return id; }
    public void setId(long id) { this.id = (int) id; }

    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public Ruolo getRuolo() { return ruolo; }
    public void setRuolo(Ruolo ruolo) { this.ruolo = ruolo; }

    public Ristorante getRistorante() {
        return ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }
}
