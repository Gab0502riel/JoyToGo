<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.elis.model.Utente, org.elis.model.Ristorante" %>
<%
    Utente utente = (Utente) session.getAttribute("UtenteLog");
    Ristorante ristorante = (Ristorante) session.getAttribute("ristoranteScelto");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordine Effettuato</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/risorse/css/styleordine.css">
</head>
<body>
    <div class="container">
        <h1>Grazie per il tuo ordine!</h1>
        <p>Ciao <strong><%= utente.getNome() %></strong>,</p>
        <p>il tuo ordine presso <strong><%= ristorante.getNome() %></strong> è stato ricevuto con successo.</p>
        <p>Riceverai presto una notifica quando sarà pronto per il ritiro.</p>
        <a href="<%= request.getContextPath() %>/HomePageUtenteServlet">Torna alla home</a>
    </div>
</body>
</html>
