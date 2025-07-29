<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Ordine, org.elis.model.ElementoOrdine" %>

<%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordiniUtente");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>I miei ordini</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/styleordiniutente.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">

</head>
<body>
    <h1>I tuoi ordini</h1>

    <% if (ordini == null || ordini.isEmpty()) { %>
        <p>Non hai ancora effettuato ordini.</p>
    <% } else { 
        for (Ordine ordine : ordini) { 
            double totaleOrdine = 0;
    %>
        <div class="ordine-box">
            <p><strong>ID:</strong> <%= ordine.getId() %></p>
            <p><strong>Data:</strong> <%= ordine.getDataOra() %></p>
            <p><strong>Stato:</strong> <%= ordine.getStato().getDescrizione() %></p>

            <table>
                <tr>
                    <th>Portata</th>
                    <th>Prezzo</th>
                    <th>Quantità</th>
                    <th>Totale</th>
                </tr>
                <% for (ElementoOrdine e : ordine.getElementi()) {
                    double subTotale = e.getPrezzo() * e.getQuantita();
                    totaleOrdine += subTotale;
                %>
                <tr>
                    <td><%= e.getNome() %></td>
                    <td><%= String.format("%.2f", e.getPrezzo()) %> €</td>
                    <td><%= e.getQuantita() %></td>
                    <td><%= String.format("%.2f", subTotale) %> €</td>
                </tr>
                <% } %>
            </table>

            <p><strong>Totale ordine:</strong> <%= String.format("%.2f", totaleOrdine) %> €</p>
        </div>
    <% } } %>
    
            <p style="margin-top: 30px;">
            <a href="<%= request.getContextPath() %>/HomePageUtenteServlet" class="home">Torna alla home</a>
        </p>

</body>
</html>
