<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Portata</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/styleaggiungiportata.css">
</head>
<body>

<div class="main_container">
    <h1>Aggiungi una nuova portata</h1>

    <form method="POST" action="<%=request.getContextPath()%>/AggiungiPortataServlet" enctype="multipart/form-data">

        <div class="form_group">
            <label for="nome">Nome portata:</label>
            <input type="text" id="nome" name="nome" required>
        </div>

        <div class="form_group">
            <label for="prezzo">Prezzo (€):</label>
            <input type="number" step="0.01" id="prezzo" name="prezzo" required>
        </div>

        <div class="form_group">
            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" required></textarea>
        </div>

        <div class="form_group">
            <label for="foto">Foto portata:</label>
            <input type="file" id="foto" name="foto" accept="image/*" required>
        </div>

        <div class="form_group">
            <label>Allergeni:</label>
            <label><input type="checkbox" name="allergeni" value="gluten_free"> Senza Glutine</label>
            <label><input type="checkbox" name="allergeni" value="lactose_free"> Senza Lattosio</label>
        </div>

        <div class="form_group">
            <label for="categoria">Categoria:</label>
            <select id="categoria" name="categoria" required>
                <option value="">Seleziona una categoria</option>
                <%
                    List<org.elis.model.Categoria> categorie = (List<org.elis.model.Categoria>) request.getAttribute("categorie");
                    if (categorie != null && !categorie.isEmpty()) {
                        for (org.elis.model.Categoria cat : categorie) {
                %>
                    <option value="<%= cat.getId() %>"><%= cat.getNome() %></option>
                <%
                        }
                    } else {
                %>
                    <option disabled>Nessuna categoria disponibile</option>
                <%
                    }
                %>
            </select>
        </div>

        <input type="submit" value="Aggiungi Portata">
    </form>
</div>

</body>
</html>
