<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.elis.model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/stylehomepageristoratore.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">
    <title>Homepage Ristoratore</title>
</head>

<body>
    <div class="main-content">

        <div class="navbar">
            <img src="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Full.png" alt="logoimg" class="logo_full" />
            <div class="nav-buttons">
                <img src="<%=request.getContextPath()%>/risorse/res/Black cart_48.png" alt="carrello" class="cart-icon">
                <img src="<%=request.getContextPath()%>/risorse/res/Default.png" alt="usericcon" class="user-icon" id="user-info-icon">
            	
					<a href="<%=request.getContextPath()%>/jsp_public/Homepage.jsp" title="Logout" class="logout-icon">
						<img src="<%=request.getContextPath()%>/risorse/res/icons8-logout-arrotondato-60.png" class="logout-icon">
					</a>
            
            </div>
        </div>
        
        <div class="scrolling-bar">
            <div class="scrolling-text">
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
                <span>JoyToGo • JoyToGo • JoyToGo • JoyToGo • JoyToGo • </span>
            </div>
        </div>

        <div class="hero-section">
            <h1>Benvenuto nella tua area ristoratore!</h1>
            <p>Gestisci il tuo ristorante e i tuoi ordini con facilità.</p>
        </div>

        <div class="informazioni-ristoranti">
            <h2 class="ristorante-1-text"> Nome Ristorante da mettere con DB</h2>
            <div class="ristorante-1-info">
                <img src="<%=request.getContextPath()%>/risorse/res/ristorante1.jpg" alt="ristorante-img" class="ristorante1-img">
                <div class="dettagli-ristorante">
                    <p>Categoria: da db</p>
                    <p>Telefono: +39 123 456 7890</p>
                    <p>Indirizzo: da db</p>
                    <p>Città: da db</p>
                </div>
            </div>
        </div>
        
        <div class="gestione-portate">
    <h2>Le tue portate</h2>
    
    <a href="<%=request.getContextPath()%>/jsp_ristoratore/AggiungiPortata.jsp" class="aggiungi-portata-btn">
        + Aggiungi Portata
    </a>
    
    <div class="lista-portate">
        <jsp:include page="listaPortateRistoratore.jsp" />
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
                        <a href="#" class="fa fa-facebook"></a>
                        <a href="https://www.instagram.com/joy_to.go?igsh=MXdwN211NXlhcWVrcA==" target="_blank"
                            class="fa fa-instagram"></a>
                        <a href="#" class="fa fa-twitter"></a>
                    </div>
                </div>
            </div>
            <div class="footer_bottom">
                <p>&copy; 2025 JoyToGo s.r.l. - Tutti i diritti riservati - Made by Gabriel, Veronica, Alessio, Raffaele
                </p>
            </div>
        </footer>

        <div id="popup" class="popup">
            <div class="popup-content">
                <span class="close">&times;</span>
                <div id="popup-text"></div>
            </div>
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

    
   <script src="<%=request.getContextPath()%>/script/homepageristoratore.js"> </script>
</body>

</html>




















