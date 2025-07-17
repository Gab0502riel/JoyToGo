package org.elis.controller.ristorante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.dao.OrdineDao;
import org.elis.dao.RistoranteDao;
import org.elis.model.Ordine;
import org.elis.model.Ruolo;
import org.elis.model.StatoOrdine;
import org.elis.model.Utente;

@WebServlet("/ristorante/ordini/*")
public class GestioneOrdiniServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RistoranteDao ristoranteDao;
    private OrdineDao ordineDao;

    @Override
    public void init() {
        this.ristoranteDao = DaoFactory.getDaoFactory().getRistoranteDao();
        this.ordineDao = DaoFactory.getDaoFactory().getOrdineDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        // 1. Controllo autenticazione e autorizzazione
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        if (!isRistoratoreAutorizzato(utente, resp)) return;

        // 2. Recupera gli ordini con filtri opzionali
        String pathInfo = req.getPathInfo(); // /in-attesa, /confermati, ecc
        List<Ordine> ordini = getOrdiniFiltrati(utente.getRistorante().getId(), pathInfo);

        // 3. Preparazione dati per la vista
        req.setAttribute("ordini", ordini);
        req.setAttribute("statiOrdine", StatoOrdine.values());
        
        // 4. Forward alla vista protetta
        req.getRequestDispatcher("/WEB-INF/jsp_private/ristorante/gestione-ordini.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        if (!isRistoratoreAutorizzato((Utente) req.getSession().getAttribute("utente"), resp)) return;

        // 1. Processa l'azione
        String azione = req.getParameter("azione");
        long idOrdine = Long.parseLong(req.getParameter("idOrdine"));
        
        // 2. Esegui l'operazione con transazione
        try {
            processaAzioneOrdine(idOrdine, azione);
            req.getSession().setAttribute("flashMessage", 
                new FlashMessage("success", "Operazione completata con successo"));
        } catch (IllegalStateException e) {
            req.getSession().setAttribute("flashMessage", 
                new FlashMessage("danger", e.getMessage()));
        }

        // 3. Redirect per evitare ricariche
        resp.sendRedirect(req.getContextPath() + "/ristorante/ordini");
    }

    // === METODI PRIVATI DI SUPPORTO ===
    private boolean isRistoratoreAutorizzato(Utente utente, HttpServletResponse resp) 
        throws IOException {
        
        if (utente == null || utente.getRuolo() != Ruolo.RISTORATORE) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    private List<Ordine> getOrdiniFiltrati(long idRistorante, String filtro) {
        return switch (filtro != null ? filtro : "") {
            case "/in-attesa" -> ordineDao.findByRistoranteAndStato(idRistorante, StatoOrdine.IN_ATTESA_DI_CONFERMA);
            case "/confermati" -> ordineDao.findByRistoranteAndStato(idRistorante, StatoOrdine.CONFERMATO);
            default -> ordineDao.findByRistorante(idRistorante);
        };
    }

    private void processaAzioneOrdine(long idOrdine, String azione) {
        StatoOrdine nuovoStato = switch (azione) {
            case "accetta" -> StatoOrdine.CONFERMATO;
            case "rifiuta" -> StatoOrdine.RIFIUTATO;
            case "completa" -> StatoOrdine.RITIRATO;
            default -> throw new IllegalStateException("Azione non valida");
        };
        
        if (!ordineDao.cambiaStato(idOrdine, nuovoStato)) {
            throw new IllegalStateException("Impossibile aggiornare lo stato dell'ordine");
        }
    }
}
