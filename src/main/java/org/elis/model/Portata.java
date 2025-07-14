package org.elis.model;

public class Portata {
	private int id;
	private String nome;
	private double prezzo;
	private String descrizione;
	private String foto;
	
	public Portata(int id, String nome, double prezzo, String descrizione, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.foto = foto;
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
		if(nome.length()>30||nome.length()<1)
			System.out.println("Inserire un nome valido");
		else
			this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		if(prezzo==0.0)
			System.out.println("Inserire un prezzo valido");
		else
			this.prezzo = prezzo;
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
	
	
	
}

	