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
import org.elis.model.Ristorante;
import org.elis.model.Utente;

@WebServlet("/AggiungiPortataServlet")
@MultipartConfig
public class AggiungiPortataServlet extends HttpServlet {
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

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente.getRistorante() == null) {
            response.sendRedirect(request.getContextPath()+"/LoginRistoratoreServlet");
            return;
        }

        // Recupera le categorie per quel ristorante
        List<Categoria> categorie = categoriaDao.trovaPerRistorante(utente.getRistorante());
        request.setAttribute("categorie", categorie);

        request.getRequestDispatcher("/WEB-INF/jsp_private/AggiungiPortata.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Parametri base
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String prezzoStr = request.getParameter("prezzo");
        String idCategoriaStr = request.getParameter("categoria");

        double prezzo;
        int idCategoria;

        try {
            prezzo = Double.parseDouble(prezzoStr);
            idCategoria = Integer.parseInt(idCategoriaStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Prezzo o categoria non validi.");
            doGet(request, response);
            return;
        }

        // 2. Allergeni
        boolean senzaGlutine = false;
        boolean senzaLattosio = false;
        String[] allergeni = request.getParameterValues("allergeni");
        if (allergeni != null) {
            for (String a : allergeni) {
                if (a.equals("gluten_free")) senzaGlutine = true;
                if (a.equals("lactose_free")) senzaLattosio = true;
            }
        }

        // 3. Upload immagine
        Part filePart = request.getPart("foto");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);
        String fotoRelativePath = "uploads/" + fileName;

        // 4. Ristorante dell’utente
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente.getRistorante() == null) {
        	response.sendRedirect(request.getContextPath()+"/LoginRistoratoreServlet");
            return;
        }

        // 5. Crea e salva portata
        Categoria categoria = categoriaDao.findById(idCategoria);
        if (categoria == null) {
            request.setAttribute("errore", "Categoria non trovata.");
            doGet(request, response);
            return;
        }


        Portata portata = new Portata();
        portata.setNome(nome);
        portata.setDescrizione(descrizione);
        portata.setPrezzo(prezzo);
        portata.setFoto(fotoRelativePath);
        portata.setSenzaGlutine(senzaGlutine);
        portata.setSenzaLattosio(senzaLattosio);
        portata.setSurgelato(false); // oppure gestibile da checkbox
        portata.setRistorante(utente.getRistorante());
        portata.setCategoria(categoria);

        portataDao.inserisci(portata);

        // 6. Redirect alla homepage
        response.sendRedirect(request.getContextPath() + "/HomepageRistoratoreServlet");
    }
}


