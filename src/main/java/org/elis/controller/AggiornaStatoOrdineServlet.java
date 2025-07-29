package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.dao.OrdineDao;
import org.elis.dao.jpa.JPADaoFactory;
import org.elis.model.StatoOrdine;

@WebServlet("/AggiornaStatoOrdineServlet")
public class AggiornaStatoOrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Recupero parametri dal form
            String idOrdineStr = request.getParameter("idOrdine");
            String nuovoStatoStr = request.getParameter("nuovoStato");

            if (idOrdineStr == null || nuovoStatoStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametri mancanti");
                return;
            }

            long idOrdine = Long.parseLong(idOrdineStr);
            StatoOrdine nuovoStato = StatoOrdine.valueOf(nuovoStatoStr);

            // Recupero DAO e aggiorno stato
            OrdineDao ordineDao = JPADaoFactory.getInstance().getOrdineDao();
            boolean successo = ordineDao.cambiaStato(idOrdine, nuovoStato);

            if (!successo) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ordine non trovato");
                return;
            }

            // Redirect alla pagina gestione ordini
            response.sendRedirect(request.getContextPath() + "/GestioneOrdiniServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'aggiornamento dello stato.");
            request.getRequestDispatcher("/WEB-INF/jsp_private/GestioneOrdini.jsp").forward(request, response);
        }
    }
}
