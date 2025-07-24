package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;
import org.elis.model.Ristorante;

/**
 * Servlet implementation class ListaPortateRistoranteServlet
 */
@WebServlet("/ListaPortateRistoranteServlet")
public class ListaPortateRistoranteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RistoranteDao rDao= DaoFactory.getDaoFactory().getRistoranteDao();
		String indirizzo = request.getParameter("indirizzo");
		Ristorante r = rDao.findByIndirizzo(indirizzo);
		if(r!=null) {
			request.setAttribute("ristoranteScelto", r);
			request.getRequestDispatcher("/WEB-INF/jsp_private/ListaPortateRistorante.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/HomepageUtente?error=ristoranteNonTrovato");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
