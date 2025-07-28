package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.model.Ordine;

@WebServlet("/OrdineEffettuatoServlet")
public class OrdineEffettuatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		HttpSession session = request.getSession();
		Ordine ordine = (Ordine) session.getAttribute("ordineEffettuato");

		request.setAttribute("ordine", ordine); 
		request.getRequestDispatcher("/WEB-INF/jsp_private/OrdineEffettuato.jsp").forward(request, response);
	}
}

