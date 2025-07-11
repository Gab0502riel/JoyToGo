package org.elis.model;

public class Ristorante {
	private int id;
	private String nome;
	private String telefono;
	private String indirizzo;
	private String citta;
	
	public Ristorante() {}

	public Ristorante(int id, String nome, String telefono, String indirizzo, String citta) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
}
