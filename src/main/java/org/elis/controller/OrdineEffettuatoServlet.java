package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.OrdineDao;
import org.elis.model.Ordine;

@WebServlet("/OrdineEffettuatoServlet")
public class OrdineEffettuatoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	OrdineDao oDao= DaoFactory.getDaoFactory().getOrdineDao();
    	Integer INTid = oDao.getLastId();
    	int idInt = INTid;
    	Long id = (long) idInt;
    	try {
			Ordine o = oDao.getById(id);
			request.setAttribute("ordine", o);
			request.getRequestDispatcher("/WEB-INF/jsp_private/OrdineEffettuato.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}

