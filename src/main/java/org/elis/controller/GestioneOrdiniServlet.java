package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.elis.dao.OrdineDao;
import org.elis.dao.jpa.JPADaoFactory;
import org.elis.model.Ordine;
import org.elis.model.Ristorante;

@WebServlet("/GestioneOrdiniServlet")
public class GestioneOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("ristoratoreLog") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginPageServlet");
            return;
        }

        Ristorante ristoranteLoggato = (Ristorante) session.getAttribute("ristoratoreLog");

        OrdineDao ordineDao = JPADaoFactory.getInstance().getOrdineDao();
        List<Ordine> ordini = ordineDao.findByRistorante(ristoranteLoggato.getId());

        request.setAttribute("ordini", ordini);
        request.getRequestDispatcher("/WEB-INF/jsp_private/GestioneOrdini.jsp").forward(request, response);
    }
}


