<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.elis.model.Ordine, org.elis.model.ElementoOrdine, org.elis.model.StatoOrdine" %>
<%@ page import="java.util.List" %>
<%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/risorse/css/stylegestioneordini.css">
</head>
<body>

<h1>Gestione Ordini Ristorante</h1>

<% for (Ordine ordine : ordini) { %>
    <div class="ordine-box">
        <p><strong>ID Ordine:</strong> <%= ordine.getId() %></p>
        <p><strong>Utente:</strong> <%= ordine.getUtente().getNome() %> <%= ordine.getUtente().getCognome() %></p>
		<p><strong>Stato:</strong> 
   				 <%= ordine.getStato() != null ? ordine.getStato().getDescrizione() : "Stato non disponibile" %>
		</p>
        <p><strong>Data:</strong> <%= ordine.getDataOra() %></p>

        <table>
            <tr>
                <th>Portata</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Totale</th>
            </tr>
            <% for (ElementoOrdine elem : ordine.getElementi()) { %>
                <tr>
                    <td><%= elem.getNome() %></td>
                    <td><%= String.format("%.2f", elem.getPrezzo()) %> €</td>
                    <td><%= elem.getQuantita() %></td>
                    <td><%= String.format("%.2f", elem.getPrezzo() * elem.getQuantita()) %> €</td>
                </tr>
            <% } %>
        </table>

        <form action="<%= request.getContextPath() %>/AggiornaStatoOrdineServlet" method="post">
            <input type="hidden" name="idOrdine" value="<%= ordine.getId() %>" />
            <label for="nuovoStato">Cambia stato:</label>
            <select name="nuovoStato" id="nuovoStato">
                <% for (StatoOrdine stato : StatoOrdine.values()) { %>
                    <option value="<%= stato.name() %>" <%= stato == ordine.getStato() ? "selected" : "" %>>
                        <%= stato.getDescrizione() %>
                    </option>
                <% } %>
            </select>
            <button type="submit">Aggiorna</button>
        </form>
    </div>
<% } %>

        <p style="margin-top: 30px;">
            <a href="<%= request.getContextPath() %>/HomepageRistoratoreServlet" class="home">Torna alla home</a>
        </p>
</body>
</html>
