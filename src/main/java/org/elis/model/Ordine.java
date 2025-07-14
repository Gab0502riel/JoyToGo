package org.elis.model;

public class Ordine {
	private int id;
	private String r_testo;
	private int r_voto;

    private enum Stato {
        ATTESA_CONFERMA("In attesa di conferma"),
        CONFERMATO("Confermato"),
        RIFIUTATO("Rifiutato"),
        RITIRATO("Ritirato"),
        ANNULLATO("Annullato");

        private final String descStato;

        Stato(String descStato) {
            this.descStato = descStato;
        }

        public String getDescStato() {
            return descStato;
        }
    }
	
	public Ordine(int id, String r_testo, int r_voto) {
		this.id = id;
		this.r_testo = r_testo;
		this.r_voto = r_voto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getR_testo() {
		return r_testo;
	}

	public void setR_testo(String r_testo) {
		this.r_testo = r_testo;
	}

	public int getR_voto() {
		return r_voto;
	}

	public void setR_voto(int r_voto) {
		this.r_voto = r_voto;
	}
	
	
	
}