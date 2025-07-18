package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutUtenteServlet")
public class LogoutUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutUtenteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // distrugge la sessione
        }
        response.sendRedirect(request.getContextPath() + "/jsp_public/Login.jsp");
    }
}