package org.elis.controller.ristorante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;
import org.elis.model.Portata;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

@WebServlet("/ristorante/aggiungi-portata")
public class AggiungiPortataServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // 1. Autenticazione (esempio semplificato)
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null || !utente.getRuolo().equals(Ruolo.RISTORATORE)) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // 2. Recupera parametri
        long idRistorante = Long.parseLong(request.getParameter("idRistorante"));
        Portata portata = new Portata();
        portata.setNome(request.getParameter("nome"));
        portata.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        
        // 3. Salvataggio
        RistoranteDao dao = DaoFactory.getDaoFactory().getRistoranteDao();
        boolean successo = dao.aggiungiPortata(idRistorante, portata);
        
        // 4. Feedback
        if (successo) {
            session.setAttribute("alert", "Portata aggiunta con successo!");
        } else {
            session.setAttribute("alert", "Errore nell'aggiunta!");
        }
        response.sendRedirect("gestione-menu.jsp");
    }
}