package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.elis.dao.CategoriaDao;
import org.elis.dao.DaoFactory;
import org.elis.dao.PortataDao;
import org.elis.model.Categoria;
import org.elis.model.Portata;
import org.elis.model.Utente;

@WebServlet("/ModificaPortataServlet")
@MultipartConfig
public class ModificaPortataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private PortataDao portataDao;
	    private CategoriaDao categoriaDao;

	    @Override
	    public void init() throws ServletException {
	        portataDao = DaoFactory.getDaoFactory().getPortataDao();
	        categoriaDao = DaoFactory.getDaoFactory().getCategoriaDao();
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
	                    request.setAttribute("portata", portata);

	                    Utente utente = (Utente) request.getSession().getAttribute("utente");
	                    if (utente != null) {
	                        List<Categoria> categorie = categoriaDao.trovaPerRistorante(utente.getRistorante());
	                        request.setAttribute("categorie", categorie);
	                    }

	                    request.getRequestDispatcher("/WEB-INF/jsp_private/ModificaPortata.jsp").forward(request, response);
	                    return;
	                }
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	            }
	        }

	        response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        request.setCharacterEncoding("UTF-8");

	        try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            String nome = request.getParameter("nome");
	            String descrizione = request.getParameter("descrizione");
	            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
	            int idCategoria = Integer.parseInt(request.getParameter("categoria"));

	            boolean senzaGlutine = false;
	            boolean senzaLattosio = false;
	            String[] allergeni = request.getParameterValues("allergeni");
	            if (allergeni != null) {
	                for (String a : allergeni) {
	                    if (a.equals("gluten_free")) senzaGlutine = true;
	                    if (a.equals("lactose_free")) senzaLattosio = true;
	                }
	            }

	            Portata portata = portataDao.trovaPerId(id);
	            if (portata == null) {
	                response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
	                return;
	            }

	            // Aggiorna i campi
	            portata.setNome(nome);
	            portata.setDescrizione(descrizione);
	            portata.setPrezzo(prezzo);
	            portata.setSenzaGlutine(senzaGlutine);
	            portata.setSenzaLattosio(senzaLattosio);
	            portata.setSurgelato(false); // O gestibile da form

	            Categoria categoria = new Categoria();
	            categoria.setId(idCategoria);
	            portata.setCategoria(categoria);

	            // Gestione opzionale della nuova immagine
	            Part filePart = request.getPart("foto");
	            if (filePart != null && filePart.getSize() > 0) {
	                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
	                File uploadDir = new File(uploadPath);
	                if (!uploadDir.exists()) uploadDir.mkdir();

	                String filePath = uploadPath + File.separator + fileName;
	                filePart.write(filePath);
	                String fotoRelativePath = "uploads/" + fileName;
	                portata.setFoto(fotoRelativePath);
	            }

	            portataDao.aggiorna(portata);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
	    }
	}
