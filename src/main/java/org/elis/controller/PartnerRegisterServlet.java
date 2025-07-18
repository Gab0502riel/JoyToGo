package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;
import org.elis.dao.RuoloDao;
import org.elis.dao.UtenteDao;
import org.elis.model.Ristorante;
import org.elis.model.Ruolo;
import org.elis.model.Sesso;
import org.elis.model.Utente;

@WebServlet("/PartnerRegisterServlet")
public class PartnerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
	private final RistoranteDao ristoranteDao = DaoFactory.getDaoFactory().getRistoranteDao();
	private final RuoloDao ruoloDao = DaoFactory.getDaoFactory().getRuoloDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. Leggi parametri dal form
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String sesso = request.getParameter("gender");
			String telefono = request.getParameter("phone");

			String nomeRistorante = request.getParameter("business");
			String indirizzo = request.getParameter("address");
			String citta = request.getParameter("city");

			// 2. Recupera ruolo RISTORATORE
			Ruolo ruolo = ruoloDao.findByNome("RISTORATORE");
			if (ruolo == null) {
				throw new RuntimeException("Ruolo 'RISTORATORE' non trovato.");
			}

			// 3. Crea e salva ristorante
			Ristorante ristorante = new Ristorante();
			ristorante.setNome(nomeRistorante);
			ristorante.setIndirizzo(indirizzo);
			ristorante.setCitta(citta);
			ristorante.setTelefono(telefono);
			ristoranteDao.insert(ristorante);

			// 4. Crea e salva utente
			Utente utente = new Utente();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setEmail(email);
			utente.setPassword(password);
			utente.setSesso(Sesso.valueOf(sesso));
			utente.setRuolo(ruolo);
			utente.setRistorante(ristorante);
			utenteDao.insert(utente);

			// 5. Redirect al login ristoratore
			response.sendRedirect(request.getContextPath() + "/jsp_pubbliche/LoginRistoratore.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
			request.getRequestDispatcher("/jsp_pubbliche/registrazioneRistoratore.jsp").forward(request, response);
		}
	}
}



