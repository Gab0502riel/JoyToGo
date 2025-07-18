package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.elis.dao.DaoFactory;
import org.elis.dao.UtenteDao;
import org.elis.model.Utente;
import org.elis.model.Sesso;

@WebServlet("/inserisciUtente")
public class inserisciUtente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parametri del form
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String sesso = request.getParameter("gender");

        // Crea oggetto utente
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setPassword(password);

        try {
            // Controllo sul campo "gender"
            if (sesso == null || sesso.isBlank()) {
                System.out.println("ERRORE: il campo 'gender' è nullo o vuoto");
                throw new IllegalArgumentException("Parametro 'gender' mancante o vuoto");
            }

            // Conversione String → Enum
            utente.setSesso(Sesso.valueOf(sesso.toUpperCase())); // Assumendo M, F, A

            // Inserisci utente nel DB
            UtenteDao utenteDao = DaoFactory.getDaoFactory().getUtenteDao();
            utenteDao.insert(utente);

            // Reindirizza al login dopo registrazione
            request.getRequestDispatcher("jsp_pubbliche/login.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            System.out.println("Valore 'gender' non valido: " + sesso);
            response.sendRedirect(request.getContextPath() + "/jsp_pubbliche/registrazione.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/HomePageServlet");
        }
    }
}

