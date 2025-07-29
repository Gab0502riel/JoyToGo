<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.elis.model.Utente, org.elis.model.Ristorante, org.elis.model.Ordine, org.elis.model.ElementoOrdine, java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    Utente utente = (Utente) session.getAttribute("UtenteLog");
    Ristorante ristorante = (Ristorante) session.getAttribute("ristoranteScelto");
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    List<ElementoOrdine> elementi = ordine.getElementi();
    double totale = 0;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordine Effettuato</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/risorse/css/styleordine.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png" />
</head>
<body>


    
    <div class="container">
    
        <video autoplay muted loop playsinline class="main_video_bg">
          <source
                src="<%=request.getContextPath()%>/risorse/res/Pasta.mov"
                type="video/mp4">
        </video>
        
        <h1>Grazie per il tuo ordine!</h1>
        <p>Ciao <strong><%= utente.getNome() %></strong>,</p>
        <p>il tuo ordine presso <strong><%= ristorante.getNome() %></strong> è stato ricevuto con successo.</p>
        <p>Riceverai presto una notifica quando sarà pronto per il ritiro.</p>
        <p>Orario ordine: <strong><%= ordine.getDataOra().format(formatter) %></strong></p>

        <h2>Dettagli ordine:</h2>
        <table>
            <tr>
                <th>Prodotto</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Subtotale</th>
            </tr>
            <%
                for (ElementoOrdine e : elementi) {
                    double subTotale = e.getPrezzo() * e.getQuantita();
                    totale += subTotale;
            %>
            <tr>
                <td><%= e.getNome() %></td>
                <td><%= String.format("%.2f", e.getPrezzo()) %> €</td>
                <td><%= e.getQuantita() %></td>
                <td><%= String.format("%.2f", subTotale) %> €</td>
            </tr>
            <% } %>
            <tr style="font-weight: bold;">
                <td colspan="3" style="text-align:right;">Totale:</td>
                <td><%= String.format("%.2f", totale) %> €</td>
            </tr>
        </table>

        <p style="margin-top: 30px;">
            <a  href="<%= request.getContextPath() %>/HomePageUtenteServlet" class="home">Torna alla home</a>
        </p>
    </div>
    
        <footer class="footer">
        <div class="footer_container">
            <div class="footer_section">
                <h4>JoyToGo</h4>
                <ul>
                    <li><a href="#">Chi siamo</a></li>
                    <li><a href="#">Contatti</a></li>
                    <li><a href="#">FAQ</a></li>
                </ul>
            </div>

            <div class="footer_section">
                <h4>Legal</h4>
                <ul>
                    <li><a href="#">Termini e condizioni</a></li>
                    <li><a href="#">Privacy policy</a></li>
                </ul>
            </div>

            <div class="footer_section">
                <h4>Contatti</h4>
                <p>Email: info.joytogo@gmail.com</p>
                <p>Telefono: +39 123 456 7890</p>
                <div class="footer_social">
                    <a href="#" class="fa fa-facebook"></a>
                    <a href="https://www.instagram.com/joy_to.go?igsh=MXdwN211NXlhcWVrcA==" target="_blank" class="fa fa-instagram"></a>
                    <a href="#" class="fa fa-twitter"></a>
                </div>
            </div>
        </div>
        <div class="footer_bottom">
            <p>&copy; 2025 JoyToGo s.r.l. - Tutti i diritti riservati - Made by Gabriel, Veronica, Alessio, Raffaele</p>
        </div>
    </footer>
    
            <div id="popup" class="popup">
            <div class="popup-content">
                <span class="close">&times;</span>
                <div id="popup-text"></div>
            </div>
        </div>
    
   <script src="<%=request.getContextPath()%>/script/ordine.js"> </script>
    
</body>
</html>

