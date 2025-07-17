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
    @Column(columnDefinition = "ENUM('In attesa di conferma', 'Confermato', 'Annullato', 'Ritirato', 'Rifiutato')")
    private String stato;
    
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
}