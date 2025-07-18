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
            String nome = request.getParameter("name");
            String cognome = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sesso = request.getParameter("gender");
            String telefono = request.getParameter("phone");
            String nomeRistorante = request.getParameter("business");
            String indirizzo = request.getParameter("address");
            String citta = request.getParameter("city");

            Ruolo ruolo = ruoloDao.findByNome("Ristoratore");
            if (ruolo == null) {
                throw new RuntimeException("Ruolo 'Ristoratore' non trovato.");
            }

            // 1. CREA E SALVA UTENTE
            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPassword(password);
            utente.setSesso(Sesso.valueOf(sesso));
            utente.setRuolo(ruolo);
            utenteDao.insert(utente);

            // 2. CREA E SALVA RISTORANTE CON PROPRIETARIO
            Ristorante ristorante = new Ristorante();
            ristorante.setNome(nomeRistorante);
            ristorante.setIndirizzo(indirizzo);
            ristorante.setCitta(citta);
            ristorante.setTelefono(telefono);
            ristorante.setProprietario(utente);
            ristoranteDao.insert(ristorante);

            response.sendRedirect(request.getContextPath() + "/LoginRistoratoreServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
            request.getRequestDispatcher("/jsp_pubbliche/registrazioneRistoratore.jsp").forward(request, response);
        }
    }
}