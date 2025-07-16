package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.model.Utente;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        request.getSession().setAttribute("utenteLoggato", utente);

        
        if (utente != null) {
            String html = "<h3 class='user-info-title'>Dati utente</h3>"
                    + "<div class='user-info'>"
                    + "<div class='user-field'><strong>Nome:</strong> " + utente.getNome() + "</div>"
                    + "<div class='user-field'><strong>Cognome:</strong> " + utente.getCognome() + "</div>"
                    + "<div class='user-field'><strong>Genere:</strong> " + utente.getSesso() + "</div>"
                    + "<div class='user-field'><strong>Email:</strong> " + utente.getEmail() + "</div>"
                    + "</div>";
            response.getWriter().write(html);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("<p>Utente non autenticato</p>");
        }
    }
}