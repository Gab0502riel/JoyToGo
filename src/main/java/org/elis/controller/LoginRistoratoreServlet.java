package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import org.elis.dao.UtenteDAO;
import org.elis.dao.UtenteDAOImpl;
import org.elis.dao.RuoloDAO;
import org.elis.dao.RuoloDAOImpl;
import org.elis.model.Utente;
import org.elis.model.Ruolo;

@WebServlet("/LoginRistoratoreServlet")
public class LoginRistoratoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UtenteDAO utenteDAO = new UtenteDAOImpl();
    private RuoloDAO ruoloDAO = new RuoloDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Mostra la pagina di login ristoratore
        response.sendRedirect(request.getContextPath() + "/jsp_public/LoginRistoratore.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Utente utente = utenteDAO.findByEmailAndPassword(email, password);

            if (utente != null) {
                Ruolo ruolo = ruoloDAO.findByUtenteId(utente.getId());

                if (ruolo != null && "Ristoratore".equalsIgnoreCase(ruolo.getNome())) {
                    // ✅ Login valido e ruolo corretto
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", utente);
                    session.setAttribute("ruolo", ruolo);

                    // Reindirizza alla homepage del ristoratore
                    response.sendRedirect(request.getContextPath() + "/jsp_public/HomepageRistoratore.jsp");
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
