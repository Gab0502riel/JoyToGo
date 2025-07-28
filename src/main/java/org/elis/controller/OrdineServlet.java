package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.dao.OrdineDao;
import org.elis.model.ElementoOrdine;
import org.elis.model.Ordine;
import org.elis.model.Ristorante;
import org.elis.model.Utente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Servlet implementation class OrdineServlet
 */
@WebServlet("/OrdineServlet")
public class OrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/OrdineEffettuatoServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    BufferedReader reader = request.getReader();
	    StringBuilder json = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        json.append(line);
	    }

	    Gson gson = new GsonBuilder()
		        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
		        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
		        .create();
	    Type tipoLista = new TypeToken<List<ElementoOrdine>>(){}.getType();
	    List<ElementoOrdine> carrello = gson.fromJson(json.toString(), tipoLista);

	    double totale = 0;

	    for (ElementoOrdine p : carrello) {
	        Double subTotale = p.getPrezzo() * p.getQuantita();
	        totale += subTotale;
	        System.out.println("Portata: " + p.getNome() + " x" + p.getQuantita() + " = " + subTotale + "€");
	    }

	    System.out.println("Totale ordine: " + totale + "€");
	    
	    HttpSession session = request.getSession();
	    Utente utente = (Utente) session.getAttribute("UtenteLog");
	    Ristorante ristorante = (Ristorante) session.getAttribute("ristoranteScelto");
	    Ordine ordine = new Ordine();
	    
	    ordine.setUtente(utente);
	    ordine.setRistorante(ristorante);
	    ordine.setElementi(carrello);
	    session.setAttribute("ordineEffettuato", ordine);
	    
	    OrdineDao oDao= DaoFactory.getDaoFactory().getOrdineDao();
	    try {
			oDao.insert(ordine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    response.sendRedirect(request.getContextPath()+"/OrdineEffettuatoServlet");
	    
	    
	    response.setStatus(HttpServletResponse.SC_OK);
	}


}
