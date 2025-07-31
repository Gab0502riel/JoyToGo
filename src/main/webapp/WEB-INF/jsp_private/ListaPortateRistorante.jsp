<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="org.elis.model.Utente"%>
    <%@page import="org.elis.model.Portata"%>
    <%@page import="org.elis.model.Categoria"%>
    <%@page import="java.util.List"%>
<%@page import="org.elis.model.Ristorante"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/stylelistaportate.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">
    <title>Lista Portate Ristorante</title>
</head>
<%Ristorante r = (Ristorante)request.getAttribute("ristoranteScelto");
List<Categoria> categorie = (List<Categoria>)request.getAttribute("categorie");
List<Portata> portate = (List<Portata>)request.getAttribute("portate");
%>
<body>
	    <div class="main-content">
	
    <div class="navbar">
        <img src="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Full.png" alt="logoimg" class="logo_full" />
        <div class="nav-buttons">
            <img src="<%=request.getContextPath()%>/risorse/res/Black cart_48.png" alt="carrello" class="cart-icon">
            <img src="<%=request.getContextPath()%>/risorse/res/Default.png" alt="usericcon" class="user-icon" id="user-info-icon">
				
			<a href="<%=request.getContextPath()%>/LogoutUtenteServlet" title="Logout" class="logout-icon">
				<img src="<%=request.getContextPath()%>/risorse/res/icons8-logout-arrotondato-60.png" class="logout-icon">
			</a>
        </div>
    </div>

    <section class="ristorante-header">
        <img src="<%=request.getContextPath()%>/risorse/res/Patternjoytogo.png" alt="Img" class="cover-img">
        <div class="ristorante-info">
            <h1><%=r.getNome() %></h1>
        </div>
    </section>




    <div class="contenuto-portate">
    <h1 class="Portate">Scopri tutti i piatti disponibili</h1>
    </div>
    	
                <div class="sezione-portate">
                <%for(Categoria c :categorie){%>
                <h1><%=c.getNome().toUpperCase()%></h1>
                	<%for(Portata p : portate){
                	if(p.getCategoria().getId()==c.getId()){%>    
            <div class="card-portate">
                <img src="<%=p.getFoto() %>" alt="Ristorante 1" class="restaurant-image">
                
                <div class="restaurant-info">
                <h3><%=p.getNome() %></h3>
                <p class="info">Descrizione:<%=p.getDescrizione() %></p>
                <%if(p.isSenzaGlutine() && p.isSenzaLattosio()){ %>
                <p class="info">Allergeni:Assenti</p>
                <%}else if(p.isSenzaGlutine()){ %>
                <p class="info">Allergeni:Lattosio</p>
                <%}else if(p.isSenzaLattosio()){ %>
                <p class="info">Allergeni:Glutine</p>
                <%}else{ %>
                <p class="info">Allergeni:Glutine - Lattosio</p>
                <%} %>
                <p class="info">Prezzo:€ <%=p.getPrezzo() %></p>
                </div>
                <div class="quantity-controls">
                	<button class="quantity-btn minus">−</button>
               		 <span class="quantity-number">0</span>
                	<button class="quantity-btn plus">+</button>
            </div>
            </div> 
            <%} } }%>
        </div>
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
                    <a href="https://www.facebook.com/share/1dqqsUosaG/" target="_blank" class="fa fa-facebook"></a>
                    <a href="https://www.instagram.com/joy_to.go?igsh=MXdwN211NXlhcWVrcA==" target="_blank"
                        class="fa fa-instagram"></a>
                    <a href="https://x.com/JoyToG0" class="fa fa-twitter"></a>
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
    
      <!-- Sidebar Carrello -->
    <div id="cartSidebar" class="cart-sidebar">
        <span class="close-cart">&times;</span>
        <h2 class="cart-title">Il tuo carrello</h2>
        <div class="cart-content">
            <div class="cart-item" id="cartItem">
                <!-- I prodotti del carrello verranno generati dinamicamente via JS -->
            </div>

            <button id="checkoutBtn" class="checkout-button" style="display: none;">
                Completa Ordine
            </button>
        </div>
    </div>

    <!-- Sfondo sfocato quando il carrello è aperto -->
    <div id="overlay" class="overlay"></div>

    <script src="<%=request.getContextPath()%>/script/listaportate.js"></script>
    
       
    
</body>

</html>