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
import org.elis.model.Utente;

@WebServlet("/OrdiniUtenteServlet")
public class OrdiniUtenteServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("UtenteLog") : null;

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/LoginUtente.jsp");
            return;
        }

        OrdineDao ordineDao = JPADaoFactory.getInstance().getOrdineDao();
        List<Ordine> ordini = ordineDao.findByUtente(utente.getId());

        request.setAttribute("ordiniUtente", ordini);
        request.getRequestDispatcher("/WEB-INF/jsp_private/VisualizzaOrdiniUtente.jsp").forward(request, response);
    }
}
