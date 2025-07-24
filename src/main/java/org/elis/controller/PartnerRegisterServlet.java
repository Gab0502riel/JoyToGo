package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.dao.RistoranteDao;

import org.elis.dao.UtenteDao;
import org.elis.dao.CategoriaDao;
import org.elis.model.Categoria;
import org.elis.model.Ristorante;
import org.elis.model.Ruolo;
import org.elis.model.Sesso;
import org.elis.model.Utente;

@WebServlet("/PartnerRegisterServlet")
@MultipartConfig
public class PartnerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
    private final RistoranteDao ristoranteDao = DaoFactory.getDaoFactory().getRistoranteDao();
    private final CategoriaDao categoriaDao = DaoFactory.getDaoFactory().getCategoriaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	request.getRequestDispatcher("/jsp_pubbliche/registrazioneRistoratore.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("name");
            String cognome = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sesso = request.getParameter("gender");
            String telefono = request.getParameter("phone");
            String nomeRistorante = request.getParameter("business");
            String indirizzo = request.getParameter("address");
            String citta = request.getParameter("city");
           
            Part filePart = request.getPart("foto");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            String fotoRelativePath = "uploads/" + fileName;

            if(utenteDao.findByEmail(email)!=null) {
    			response.sendRedirect(request.getContextPath()+"/PartnerRegisterServlet?error=emailEsistente");
    			return;
    		}
    		if(!isValidPassword(password)) {
    			response.sendRedirect(request.getContextPath()+"/PartnerRegisterServlet?error=passwordNonValida");
    			return;
    		}
    		
            
            // 1. CREA E SALVA UTENTE
            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPassword(password);
            utente.setSesso(Sesso.valueOf(sesso));
            utente.setRuolo(Ruolo.RISTORATORE);
            utente.setFoto(fotoRelativePath);
            utenteDao.insert(utente);
            
            String[] categoriaIds = request.getParameterValues("categories[]");
            System.out.println(categoriaIds);
            List<Categoria> categorieRistorante = new ArrayList<>();

            if (categoriaIds != null) {
                for (String idStr : categoriaIds) {
                    try {
                        int id = Integer.parseInt(idStr);
                        Categoria cat = categoriaDao.findById(id);  // ← usa findById della tua DAO
                        if (cat != null) categorieRistorante.add(cat);
                    } catch (NumberFormatException e) {
                        // log o ignora categoria non valida
                    	e.printStackTrace();
                    }
                }
            }


            // 2. CREA E SALVA RISTORANTE CON PROPRIETARIO
            Ristorante ristorante = new Ristorante();
            ristorante.setNome(nomeRistorante);
            ristorante.setIndirizzo(indirizzo);
            ristorante.setCitta(citta);
            ristorante.setTelefono(telefono);
            ristorante.setProprietario(utente);
            ristorante.setCategorie(categorieRistorante); 
            ristoranteDao.insert(ristorante);
            request.getSession().setAttribute("utente", utente);

           
            
            

            response.sendRedirect(request.getContextPath() + "/LoginRistoratoreServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
            request.getRequestDispatcher("/jsp_pubbliche/registrazioneRistoratore.jsp").forward(request, response);
        }
    }
    
    public static boolean isValidPassword(String password) {
		if (password == null || password.length() < 8) return false;

		boolean haMaiuscola = password.matches(".*[A-Z].*");
		boolean haMinuscola = password.matches(".*[a-z].*");
		boolean haNumero = password.matches(".*\\d.*");
		boolean haSpeciale = password.matches(".*[\\W_].*");

		return haMaiuscola && haMinuscola && haNumero && haSpeciale;
	}
}