package org.elis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;
import org.elis.dao.RuoloDao;
import org.elis.dao.UtenteDao;
import org.elis.model.Ristorante;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HomepageUtenteServlet")
public class HomePageUtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            System.out.println("Sessione assente o utente non loggato, redirect login.");
            response.sendRedirect(request.getContextPath() + "/jsp_public/Login.jsp");
            return;
        }

        Utente utenteInSessione = (Utente) session.getAttribute("utente");
        System.out.println("Utente in sessione: " + utenteInSessione.getEmail());

        RistoranteDao ristoranteDao = DaoFactory.getRistoranteDao());
        UtenteDao utenteDao = DaoFactory.getUtenteDao();
        RuoloDao ruoloDao = DaoFactory.getRuoloDao();

        try {
            List<Ristorante> ristoranti = ristoranteDao.findAll();
            if (ristoranti == null) {
                System.out.println("Lista ristoranti è null, la sostituisco con lista vuota");
                ristoranti = new ArrayList<>();
            }
            request.setAttribute("ristoranti", ristoranti);

            Utente utenteAggiornato = utenteDao.findByEmail(utenteInSessione.getEmail());
            if (utenteAggiornato == null) {
                System.out.println("Utente aggiornato non trovato nel DB, redirect login.");
                response.sendRedirect(request.getContextPath() + "/jsp_public/Login.jsp");
                return;
            }
            request.setAttribute("utente", utenteAggiornato);

            Ruolo ruolo = ruoloDao.findByUtenteId(utenteAggiornato.getId());
            if (ruolo == null) {
                System.out.println("Ruolo non trovato, continuo senza ruolo.");
            }
            request.setAttribute("ruolo", ruolo);

            String jspPath = "/WEB-INF/jsp_private/HomepageUtente.jsp";
            if (request.getServletContext().getResource(jspPath) == null) {
                System.out.println("ERRORE: JSP non trovata: " + jspPath);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Pagina utente non trovata");
                return;
            }

            System.out.println("Forward alla JSP protetta");
            request.getRequestDispatcher(jspPath).forward(request, response);

        } catch (Exception e) {
            System.out.println("Eccezione durante caricamento dati:");
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento dati");
        }
    }
}




