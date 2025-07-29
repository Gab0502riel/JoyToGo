package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.model.Ordine;

@WebServlet("/OrdineEffettuatoServlet")
public class OrdineEffettuatoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Ordine ordine = (session != null) ? (Ordine) session.getAttribute("ordineEffettuato") : null;

        if (ordine == null) {
            request.setAttribute("erroreOrdine", true);
        } else {
            request.setAttribute("ordine", ordine);  // 🔴 FONDAMENTALE: PASSAGGIO ALLA JSP
        }

        request.getRequestDispatcher("/WEB-INF/jsp_private/OrdineEffettuato.jsp").forward(request, response);
    }
}

