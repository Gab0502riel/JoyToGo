package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.jdbc.JdbcUtenteDao;
import org.elis.model.Utente;

/**
 * Servlet implementation class inserisciUtente
 */
@WebServlet("/inserisciUtente")
public class inserisciUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private JdbcUtenteDao UtenteDao;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setPassword(password);
		
		UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
		try {
			utenteDao.insert(utente);
		}catch (Exception e) {
			
		};
	}

}
