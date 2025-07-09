package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PartnerRegisterServlet")
public class PartnerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PartnerRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/jsp_pubbliche/registrazioneRistoratore.jsp");
	}

}

