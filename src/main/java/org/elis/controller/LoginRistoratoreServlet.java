package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.UtenteDao;
import org.elis.dao.RuoloDao;
import org.elis.model.Utente;
import org.elis.model.Ruolo;

@WebServlet("/LoginRistoratoreServlet")
public class LoginRistoratoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
    private final RuoloDao ruoloDao = DaoFactory.getDaoFactory().getRuoloDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostra la pagina di login
        response.sendRedirect(request.getContextPath() + "/jsp_public/LoginRistoratore.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Utente utente = utenteDao.findByEmailAndPassword(email, password);

            if (utente != null) {
                Ruolo ruolo = ruoloDao.findByUtenteId(utente.getId());

                if (ruolo != null && "RISTORATORE".equalsIgnoreCase(ruolo.getNome())) {
                    // ✅ Login corretto
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", utente);
                    session.setAttribute("ruolo", ruolo);

                    // Redirect alla homepage del ristoratore
                    response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp_private/loggedHomepageRistoratore.jsp");
                } else {
                    // Non è un ristoratore
                    request.setAttribute("errore", "Accesso consentito solo ai ristoratori.");
                    request.getRequestDispatcher("/jsp_public/LoginRistoratore.jsp").forward(request, response);
                }
            } else {
                // Credenziali errate
                request.setAttribute("errore", "Email o password errati.");
                request.getRequestDispatcher("/jsp_public/LoginRistoratore.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante il login: " + e.getMessage());
            request.getRequestDispatcher("/jsp_public/LoginRistoratore.jsp").forward(request, response);
        }
    }
}
