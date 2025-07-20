<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Portata" %>

<%
    List<Portata> portate = (List<Portata>) request.getAttribute("portate");
%>

<div class="lista-portate">
    <% if (portate != null && !portate.isEmpty()) { 
        for (Portata p : portate) { %>
            <div class="portata-box">
                <img src="<%=request.getContextPath()%>/<%= p.getFoto() %>" alt="Immagine portata">
                <h3><%= p.getNome() %></h3>
                <p><strong>Prezzo:</strong> €<%= String.format("%.2f", p.getPrezzo()) %></p>
                <p><strong>Categoria:</strong> <%= p.getCategoria().getNome() %></p>                
                <p><strong>Descrizione:</strong> <%= p.getDescrizione() %></p>
                <p><strong>Senza Glutine:</strong> <%= p.isSenzaGlutine() ? "Sì" : "No" %></p>
                <p><strong>Senza Lattosio:</strong> <%= p.isSenzaLattosio() ? "Sì" : "No" %></p>

                <div class="portata-azioni">
    <a href="<%=request.getContextPath()%>/ModificaPortataServlet?id=<%= p.getId() %>" 
       class="btn-modifica">
       Modifica
    </a>

    <a href="<%=request.getContextPath()%>/EliminaPortataServlet?id=<%= p.getId() %>" 
       class="btn-elimina" 
       onclick="return confirm('Sei sicuro di voler eliminare questa portata?');">
       Elimina
    </a>
</div>

            </div>
    <% } 
    } else { %>
        <p>Non ci sono portate da mostrare.</p>
    <% } %>
</div>


