<%@page import="java.util.List"%>
<%@page import="org.elis.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/stylehomepageutente.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">
    <title>Homepage Utente</title>
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
        <div class="carousel_wrapper">
            <button class="carousel_arrow left"><i class="fas fa-chevron-left"></i></button>

            <div class="food_container">
                <div class="food_pic" id="carouselTrack">
                    <div class="food_box">
                        <img src="<%=request.getContextPath()%>/risorse/res/Pizza_3.jpg" alt="pizza" class="food_image" />
                        <div class="food_label">Pizza</div>
                    </div>
                    <div class="food_box">
                        <img src="<%=request.getContextPath()%>/risorse/res/panino_1.webp" alt="hamburger" class="food_image" />
                        <div class="food_label">Hamburger</div>
                    </div>
                    <div class="food_box">
                        <img src="<%=request.getContextPath()%>/risorse/res/sushi_2.jpg" alt="sushi" class="food_image" />
                        <div class="food_label">Sushi</div>
                    </div>
                    <div class="food_box">
                        <img src="<%=request.getContextPath()%>/risorse/res/Fritti_1.jpg" alt="fritti" class="food_image" />
                        <div class="food_label">Fritti</div>
                    </div>
                    <div class="food_box">
                        <img src="<%=request.getContextPath()%>/risorse/res/pasta_1.jpg" alt="italiano" class="food_image" />
                        <div class="food_label">Italiano</div>
                    </div>
                </div>
            </div>

            <button class="carousel_arrow right"><i class="fas fa-chevron-right"></i></button>
        </div>

        <section class="search-filters">
            <form id="searchForm" method="GET" action="SearchServlet">
                <input type="text" name="query" placeholder="Cerca ristorante o piatto..." class="search-input" />

                <select name="category" class="filter-select">
                    <option value="">Tutte le categorie</option>
                    <option value="hamburger">Hamburger</option>
                    <option value="pizza">Pizza</option>
                    <option value="sushi">Sushi</option>
                    <option value="fritti">Fritti</option>
                    <option value="italiano">Italiano</option>
                    <!-- altre categorie -->
                </select>

                <select name="stars" class="filter-select">
                    <option value="">Valutazione</option>
                    <option value="5">★★★★★</option>
                    <option value="4">★★★★☆</option>
                    <option value="3">★★★☆☆</option>
                    <option value="2">★★☆☆☆</option>
                    <option value="1">★☆☆☆☆</option>
                </select>

                <select name="priceRange" class="filter-select">
                    <option value="">Prezzo</option>
                    <option value="low">€</option>
                    <option value="medium">€€</option>
                    <option value="high">€€€</option>
                </select>

                <button type="submit" class="btn-search">Cerca</button>
            </form>
        </section>
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



    <script src="<%=request.getContextPath()%>/script/homepageutente.js">
    </script>




</body>

</html>