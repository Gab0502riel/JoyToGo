package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.jdbc.JdbcUtenteDao;
import org.elis.model.Sesso;
import org.elis.model.Utente;

import org.elis.dao.UtenteDao;
@WebServlet("/inserisciUtente")
public class inserisciUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("name");
		String cognome = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sesso = request.getParameter("gender");

		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setPassword(password);
		
		   try {
		        if (sesso == null || sesso.isBlank()) {
		            System.out.println("ERRORE: il campo 'gender' è nullo o vuoto");
		            throw new IllegalArgumentException("Parametro 'gender' mancante o vuoto");
		        }

		        utente.setSesso(Sesso.valueOf(sesso));

		        UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
		        utenteDao.insert(utente);

		        request.getRequestDispatcher("jsp_pubbliche/login.jsp").forward(request, response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.sendRedirect(request.getContextPath() + "/HomePageServlet");
		    }
	}

}
