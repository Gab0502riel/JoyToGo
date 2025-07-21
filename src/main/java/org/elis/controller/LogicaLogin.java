package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.UtenteDao;
import org.elis.model.Utente;

/**
 * Servlet implementation class LogicaLogin
 */
@WebServlet("/LogicaLogin")
public class LogicaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteDao udao = DaoFactory.getDaoFactory().getUtenteDao();
		
		Utente u = udao.findByEmail(email);
		if(u!=null) {
			if(u.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("UtenteLog", u);
				response.sendRedirect(request.getContextPath() + "/HomePageUtenteServlet");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/LoginPageServlet?error=PasswordErrata");
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/LoginPageServlet?error=UtenteInesistente");
		}
	}

}
