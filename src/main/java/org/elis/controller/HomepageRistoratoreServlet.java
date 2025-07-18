package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.dao.PortataDao;
import org.elis.model.Portata;
import org.elis.model.Ristorante;
import org.elis.model.Utente;

@WebServlet("/HomepageRistoratoreServlet")
public class HomepageRistoratoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null || utente.getRuolo() == null || !utente.getRuolo().getNome().equalsIgnoreCase("RISTORATORE")) {
            response.sendRedirect(request.getContextPath() + "/jsp_pubbliche/LoginRistoratore.jsp");
            return;
        }

        Ristorante ristorante = utente.getRistorante();
        if (ristorante == null) {
            request.setAttribute("errore", "Nessun ristorante associato all’utente.");
            request.getRequestDispatcher("/WEB-INF/jsp_private/HomepageRistoratore.jsp").forward(request, response);
            return;
        }

        PortataDao portataDao = DaoFactory.getDaoFactory().getPortataDao();
        List<Portata> portate = portataDao.trovaPerRistorante(ristorante);

        request.setAttribute("portate", portate);
        request.getRequestDispatcher("/WEB-INF/jsp_private/HomepageRistoratore.jsp").forward(request, response);
    }
}

