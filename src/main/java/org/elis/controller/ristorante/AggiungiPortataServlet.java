package org.elis.controller.ristorante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.elis.dao.CategoriaDao;
import org.elis.dao.PortataDao;
import org.elis.dao.jpa.JPACategoriaDao;
import org.elis.dao.jpa.JPADaoFactory;
import org.elis.dao.jpa.JPAPortataDao;
import org.elis.model.*;

import jakarta.persistence.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ristorante/aggiungi-portata")
public class AggiungiPortataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ottieni EntityManager da JPADaoFactory
        JPADaoFactory daoFactory = JPADaoFactory.getInstance();
        EntityManager em = daoFactory.getEntityManager();
        PortataDao portataDao = new JPAPortataDao(em);
        CategoriaDao categoriaDao = new JPACategoriaDao(em);

        try {
            Utente utente = (Utente) request.getSession().getAttribute("utenteAutenticato");
            if (utente == null || utente.getRuolo() != Ruolo.RISTORATORE) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            // Recupera le categorie del ristorante
            Ristorante ristorante = utente.getRistorante();
            List<Categoria> categorie = categoriaDao.trovaPerRistorante(ristorante);
            request.setAttribute("categorie", categorie);

            // Se è modifica, carica la portata
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isBlank()) {
                int id = Integer.parseInt(idStr);
                Portata portata = portataDao.trovaPerId(id);
                request.setAttribute("portata", portata);
            }

            // Mostra la form
            request.getRequestDispatcher("/WEB-INF/jsp_private/ristorante/form_portata.jsp").forward(request, response);

        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JPADaoFactory daoFactory = JPADaoFactory.getInstance();
        EntityManager em = daoFactory.getEntityManager();
        PortataDao portataDao = new JPAPortataDao(em);

        try {
            em.getTransaction().begin();

            Utente utente = (Utente) request.getSession().getAttribute("utenteAutenticato");
            if (utente == null || utente.getRuolo() != Ruolo.RISTORATORE) {
                throw new ServletException("Utente non autenticato o non ristoratore");
            }

            Ristorante ristorante = utente.getRistorante();

            // Leggi parametri dal form
            String idStr = request.getParameter("id");
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            String foto = request.getParameter("foto");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            boolean senzaGlutine = request.getParameter("senzaGlutine") != null;
            boolean senzaLattosio = request.getParameter("senzaLattosio") != null;
            boolean surgelato = request.getParameter("surgelato") != null;
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            Categoria categoria = em.find(Categoria.class, idCategoria);

            // Nuova o esistente
            Portata portata = (idStr == null || idStr.isBlank())
                    ? new Portata()
                    : portataDao.trovaPerId(Integer.parseInt(idStr));

            // Imposta dati
            portata.setNome(nome);
            portata.setDescrizione(descrizione);
            portata.setFoto(foto);
            portata.setPrezzo(prezzo);
            portata.setSenzaGlutine(senzaGlutine);
            portata.setSenzaLattosio(senzaLattosio);
            portata.setSurgelato(surgelato);
            portata.setCategoria(categoria);
            portata.setRistorante(ristorante);

            if (portata.getId() == null) {
                portataDao.inserisci(portata);
                request.getSession().setAttribute("alert", "Portata aggiunta con successo!");
            } else {
                portataDao.aggiorna(portata);
                request.getSession().setAttribute("alert", "Portata modificata con successo!");
            }

            em.getTransaction().commit();
            response.sendRedirect(request.getContextPath() + "/ristorante/lista-portate.jsp");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new ServletException("Errore durante il salvataggio della portata", e);
        } finally {
            em.close();
        }
    }
}

