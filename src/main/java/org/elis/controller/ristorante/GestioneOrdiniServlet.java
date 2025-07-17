package org.elis.controller.ristorante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;
import org.elis.model.StatoOrdine;

/**
 * Servlet implementation class GestioneOrdiniServlet
 */
@WebServlet("/ristorante/gestione-ordini")
public class GestioneOrdiniServlet extends HttpServlet {
    
    private RistoranteDao ristoranteDao;

    @Override
    public void init() {
        this.ristoranteDao = DaoFactory.getDaoFactory().getRistoranteDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // 1. Recupera parametri
        long idOrdine;
        try {
            idOrdine = Long.parseLong(request.getParameter("idOrdine"));
        } catch (NumberFormatException e) {
            response.sendError(400, "ID ordine non valido");
            return;
        }
        
        String azione = request.getParameter("azione");
        
        // 2. Determina il nuovo stato
        StatoOrdine nuovoStato;
        switch (azione) {
            case "accetta":
                nuovoStato = StatoOrdine.CONFERMATO;
                break;
            case "rifiuta":
                nuovoStato = StatoOrdine.RIFIUTATO;
                break;
            case "ritira":
                nuovoStato = StatoOrdine.RITIRATO;
                break;
            default:
                response.sendError(400, "Azione non valida");
                return;
        }
        
        // 3. Esegui l'operazione
        boolean successo = ristoranteDao.cambiaStatoOrdine(idOrdine, nuovoStato);
        
        // 4. Redirect con feedback
        if (successo) {
            response.sendRedirect("dashboard.jsp?status=success&action=" + azione);
        } else {
            response.sendRedirect("dashboard.jsp?status=error&action=" + azione);
        }
    }
}
