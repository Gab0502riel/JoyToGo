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
        response.sendRedirect(request.getContextPath() + "/jsp_pubbliche/LoginRistoratore.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Utente utente = utenteDao.findByEmailAndPassword(email, password);

            if (utente != null) {
            	Ruolo ruolo = utente.getRuolo();

                if (ruolo != null && "RISTORATORE".equalsIgnoreCase(ruolo.getNome())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", utente);
                    session.setAttribute("ruolo", ruolo);

                    response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");

                } else {
                    request.setAttribute("errore", "Accesso consentito solo ai ristoratori.");
                    request.getRequestDispatcher("/jsp_pubbliche/LoginRistoratore.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errore", "Email o password errati.");
                request.getRequestDispatcher("/jsp_pubbliche/LoginRistoratore.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante il login: " + e.getMessage());
            request.getRequestDispatcher("/jsp_pubbliche/LoginRistoratore.jsp").forward(request, response);
        }
    }
}


