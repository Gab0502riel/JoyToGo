<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Homepage JoyToGo</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/stylehomepage.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png" />
</head>

<body>
    <div class="navbar">
        <img src="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Full.png" alt="logoimg" class="logo_full" />
        
        <div class="nav-buttons">
         <a href="<%=request.getContextPath()%>/PartnerRegisterServlet" target="_blank">
       		 <input type="submit" value="Diventa Partner" class="partner" />
        </a>
        

        <a href="<%=request.getContextPath()%>/LoginPageServlet" target="_blank">
            <input type="submit" value="Login" class="login_button" />
        </a>

        <a href="<%=request.getContextPath()%>/RegisterPageServlet" target="_blank">
            <input type="submit" value="Registrati" class="signup_button" />
        </a>
    </div>
    </div>

    <div class="main">
        <video autoplay muted loop playsinline class="main_video_bg">
            <source
                src="<%=request.getContextPath()%>/risorse/res/vecteezy_hot-pizza-just-from-oven-in-the-pizzeria-restaurant-of_5355517.mov"
                type="video/mp4">
        </video>

        <div class="main_left">
            <div class="glass-card">
                <h1 class="main_title">Cosa vuoi mangiare oggi?</h1>

                <div class="search_box">
                    <input type="text" placeholder="Cerca ristoranti o piatti..." class="search_input" />
                    <button type="submit" class="search_button" id="search-btn">
                        <i class="fa fa-search"></i>
                    </button>
                </div>

                <div class="social_media">
                    <a href="https://www.facebook.com/" target="_blank" class="fa fa-facebook"></a>
                    <a href="https://www.instagram.com/joy_to.go?igsh=MXdwN211NXlhcWVrcA==" target="_blank"
                        class="fa fa-instagram"></a>
                    <a href="https://www.twitter.com/" target="_blank" class="fa fa-twitter"></a>
                </div>
            </div>
        </div>
    </div>


    <div class="space">

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

    <div class="space">

    </div>


    <div class="istructions_container">
        <div class="istruzioni">
            <h3 class="rule_1">Come Funziona JoyToGo?</h3>
            <h2 class="rule_2">È semplicissimo!</h2>
            <div class="brandbag_section">
                <img src="<%=request.getContextPath()%>/risorse/res/brandbag.png" alt="brandbag" class="brandbag_image">
                <p class="brandbag_text">Bag e Bowl JoyToGo ecosostenibili, riutilizzabili!</p>
            </div>
        </div>



        <div class="burger_info">
            <img src="<%=request.getContextPath()%>/risorse/res/icons8-location-96.png" alt="Location Icon" class="location_icon">
            <p>Scopri i migliori ristoranti e ordina comodamente da casa tua.</p>
            <img src="<%=request.getContextPath()%>/risorse/res/icons8-hamburger-96.png" alt="Hamburger Icon" class="burger_icon">
            <p>Ordina i tuoi piatti preferiti in pochi clic.</p>
            <img src="<%=request.getContextPath()%>/risorse/res/icons8-take-away-food-96.png" alt="Take Away Icon" class="bag_icon">
            <p>Ad ordine effettuato, cibo caldo d'asporto assicurato!.</p>
        </div>

    </div>

    <div class="space">

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

    <div class="resturants_container">
        <h2 class="text_card">Scopri cosa puoi ordinare comodamente da casa, ed andare a ritirare nel più breve tempo
            possibile!</h2>

        <div class="resturant_info">

            <div class="resturant_card">
                <div class="slider">
                    <img src="<%=request.getContextPath()%>/risorse/res/mcmaking.webp" alt="McMaking" class="slide_main">
                    <img src="<%=request.getContextPath()%>/risorse/res/mcdrive.webp" alt="McDrive" class="slide">
                    <img src="<%=request.getContextPath()%>/risorse/res/mcdonald.jpg" alt="McDonald" class="slide">
                </div>
                <h4 class="resturant_name">McDonald's</h4>

                <button class="prev">←</button>
                <button class="next">→</button>
            </div>

            <div class="resturant_card">
                <div class="slider">
                    <img src="<%=request.getContextPath()%>/risorse/res/Pizza.jpg" alt="Pizza" class="slide_main">
                    <img src="<%=request.getContextPath()%>/risorse/res/pizzaacasa.avif" alt="Pizza a casa" class="slide">
                    <img src="<%=request.getContextPath()%>/risorse/res/pizza2.avif" alt="Pizza 2" class="slide">
                </div>
                <button class="prev">←</button>
                <button class="next">→</button>
            </div>
            
            <div class="resturant_card">
                <div class="slider">
                    <img src="<%=request.getContextPath()%>/risorse/res/fritto1.jpg" alt="Fritti" class="slide_main">
                    <img src="<%=request.getContextPath()%>/risorse/res/Fritto2.jpg" alt="Fritti a casa" class="slide">
                    <img src="<%=request.getContextPath()%>/risorse/res/fritto3.jpg" alt="Fritti" class="slide">
                </div>
                <button class="prev">←</button>
                <button class="next">→</button>
            </div>

            <div class="resturant_card">
                <div class="slider">
                    <img src="<%=request.getContextPath()%>/risorse/res/Sushi.jpg" alt="Sushi" class="slide_main">
                    <img src="<%=request.getContextPath()%>/risorse/res/sushiacasa.avif" alt="Sushi a casa" class="slide">
                    <img src="<%=request.getContextPath()%>/risorse/res/sushi4.jpg" alt="Poke" class="slide">
                </div>
                <button class="prev">←</button>
                <button class="next">→</button>
            </div>
            
            <div class="resturant_card">
                <div class="slider">
                    <img src="<%=request.getContextPath()%>/risorse/res/pasta1.jpg" alt="Pasta" class="slide_main">
                    <img src="<%=request.getContextPath()%>/risorse/res/pasta2.jpg" alt="Pasta a casa" class="slide">
                    <img src="<%=request.getContextPath()%>/risorse/res/pasta3.jpg" alt="Pasta" class="slide">
                </div>
                <button class="prev">←</button>
                <button class="next">→</button>
            </div>
            
            

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

    <script>
    document.getElementById("search-btn").addEventListener("click", function(e) {
        e.preventDefault(); // Previene l'invio del form
        popupText.innerHTML = "<h3>Attenzione</h3><p>Per cercare / filtrare i risultati, devi essere registrato!</p>";
        popup.style.display = "flex";
    });
    
    
        document.querySelectorAll('.resturant_card').forEach(card => {
            const images = card.querySelectorAll('.slider img');
            let index = 0;

            images[index].classList.add('active');

            const showSlide = (i) => {
                images.forEach((img, idx) => {
                    img.classList.remove('active');
                });
                images[i].classList.add('active');
            };

            card.querySelector('.prev').addEventListener('click', () => {
                index = (index - 1 + images.length) % images.length;
                showSlide(index);
            });

            card.querySelector('.next').addEventListener('click', () => {
                index = (index + 1) % images.length;
                showSlide(index);
            });
        });

        const popup = document.getElementById("popup");
        const popupText = document.getElementById("popup-text");
        const closeBtn = document.querySelector(".close");

        document.querySelectorAll("footer a").forEach(link => {
            link.addEventListener("click", e => {
                if (link.classList.contains("fa")) return;

                e.preventDefault();
                const text = link.textContent.trim();

                switch (text) {
                    case "Chi siamo":
                        popupText.innerHTML = "<h3>Chi siamo?</h3><p>Ci presentiamo: Gabriel D'Antoni, Veronica Gioia, Alessio Biagioni, Raffaele Recupero.<br> Siamo un team di aspiranti sviluppatori che hanno creato con passione questa pagina web in ogni minimo dettaglio. E' un piacere vedervi navigare nella nostra pagina!</p>";
                        break;
                    case "Contatti":
                        popupText.innerHTML = "<h3>Contatti</h3><p>Email: info.joytogo@gmail.com<br>Telefono: +39 123 456 7890</p>";
                        break;
                    case "FAQ":
                        popupText.innerHTML = "<h3>FAQ</h3><p>Domande frequenti? Dai che hai voglia anche tu di un bel panino!</p>";
                        break;
                    case "Termini e condizioni":
                        popupText.innerHTML = "<h3>Termini e condizioni</h3><p>Ordinare è semplice: scegli, clicca, ritira... e non litigare col panino. Leggi queste regole prima di lamentarti perché il kebab non fa i salti mortali. Non siamo responsabili se ti innamori del nostro servizio (o del sushi). Ci riserviamo il diritto di aggiornare tutto... tranne la tua fame. Accetti tutto? Brav*. Adesso scegli il ristorante, il cibo non si ordina mica da solo!</p>";
                        break;
                    case "Privacy policy":
                        popupText.innerHTML = "<h3>Privacy policy</h3><p>Promesso: non spiattelleremo a nessuno che ordini 3 burger alle 23.59. I tuoi dati sono conservati meglio del tiramisù nel frigo di una nonna. Niente pubblicità invadenti, niente vendite strane. Solo cibo e tranquillità. Puoi gestire i tuoi dati quando vuoi, ma purtroppo non possiamo cancellare le calorie. E no, non usiamo i tuoi dati per suggerire l' insalata: sappiamo che volevi la pizza.</p>";
                        break;
                    default:
                        popupText.innerHTML = "<p>Informazioni non disponibili.</p>";
                }

                popup.style.display = "flex";
            });
        });
        closeBtn.addEventListener("click", () => {
            popup.style.display = "none";
        });

        window.addEventListener("click", (e) => {
            if (e.target === popup) {
                popup.style.display = "none";
            }
        });

    </script>
</body>

</html>