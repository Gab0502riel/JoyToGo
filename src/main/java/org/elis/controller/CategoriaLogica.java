package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.dao.CategoriaDao;
import org.elis.dao.DaoFactory;
import org.elis.model.Categoria;
import org.elis.model.Utente;

/**
 * Servlet implementation class CategoriaLogica
 */
@WebServlet("/CategoriaLogica")
public class CategoriaLogica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome_categoria");
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		System.out.println(nome);
		CategoriaDao cDao = DaoFactory.getDaoFactory().getCategoriaDao();
		Categoria cat = new Categoria();
		cat.setNome(nome);
		cat.setRistorante(u.getRistorante());
		try {
			cDao.insert(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
	}

}
