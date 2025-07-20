<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.elis.model.Portata" %>
<%@ page import="org.elis.model.Categoria" %>
<%@ page import="java.util.List" %>

<%
    Portata portata = (Portata) request.getAttribute("portata");
    List<Categoria> categorie = (List<Categoria>) request.getAttribute("categorie");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Portata</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/styleaggiungiportata.css">
</head>
<body>

<div class="main_container">
    <h1>Modifica Portata</h1>

    <form method="POST" action="<%=request.getContextPath()%>/ModificaPortataServlet" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= portata.getId() %>">

        <div class="form_group">
            <label for="nome">Nome portata:</label>
            <input type="text" id="nome" name="nome" value="<%= portata.getNome() %>" required>
        </div>

        <div class="form_group">
            <label for="prezzo">Prezzo (€):</label>
            <input type="number" step="0.01" id="prezzo" name="prezzo" value="<%= portata.getPrezzo() %>" required>
        </div>

        <div class="form_group">
            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" required><%= portata.getDescrizione() %></textarea>
        </div>

        <div class="form_group">
            <label>Allergeni:</label>
            <label>
                <input type="checkbox" name="gluten_free" <%= portata.isSenzaGlutine() ? "checked" : "" %> > Senza Glutine
            </label>
            <label>
                <input type="checkbox" name="lactose_free" <%= portata.isSenzaLattosio() ? "checked" : "" %> > Senza Lattosio
            </label>
        </div>

        <div class="form_group">
            <label for="categoria">Categoria:</label>
            <select id="categoria" name="categoria" required>
                <option value="">Seleziona una categoria</option>
                <% for (Categoria c : categorie) { %>
                    <option value="<%= c.getId() %>" <%= (portata.getCategoria() != null && portata.getCategoria().getId().equals(c.getId())) ? "selected" : "" %>>
                        <%= c.getNome() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="form_group">
            <label for="foto">Sostituisci immagine:</label>
            <input type="file" id="foto" name="foto" accept="image/*">
            <br><br>
            <img src="<%=request.getContextPath()%>/<%= portata.getFoto() %>" alt="Immagine attuale" style="max-width: 150px;">
        </div>

        <input type="submit" value="Salva Modifiche">
    </form>
</div>

</body>
</html>
