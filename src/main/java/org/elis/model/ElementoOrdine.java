package org.elis.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento_ordine")
public class ElementoOrdine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer quantità;
    
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataOra;
    
    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Ordine ordine;
    
    @ManyToOne
    @JoinColumn(name = "id_portata")
    private Portata portata;
}