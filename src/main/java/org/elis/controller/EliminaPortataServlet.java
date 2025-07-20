package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.PortataDao;
import org.elis.model.Portata;

@WebServlet("/EliminaPortataServlet")
public class EliminaPortataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PortataDao portataDao;

    @Override
    public void init() throws ServletException {
        portataDao = DaoFactory.getDaoFactory().getPortataDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Portata portata = portataDao.trovaPerId(id);
                if (portata != null) {
                    portataDao.elimina(portata);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // opzionale: log dell'errore
            }
        }

        response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
    }
}
