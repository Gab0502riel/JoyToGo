<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Partner Registration</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/stylepartnerregister.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">
</head>

<body>

    <div class="navbar">

        <a href="<%=request.getContextPath()%>/jsp_pubbliche/homepage.jsp">
            <img src="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Full.png" alt="logoimg" class="logo_full">
        </a>

          <div class="navbar_buttons">
            <a href="<%=request.getContextPath()%>/jsp_pubbliche/LoginRistoratore.jsp">
                <label for="register" class="register"></label>
                <input type="submit" value="Accedi come Partner" class="login_button">
            </a>
        </div>
    </div>

    <div class="main">

        <h1 class="main_title">Diventa Ristoratore</h1>
        <form action="partner_registration" method="POST">
            <div class="form_group">
                <label for="name">Nome:</label>
                <input type="text" id="name" name="name" required placeholder="Inserisci il tuo nome">
            </div>
            <div class="form_group">
                <label for="surname">Cognome:</label>
                <input type="text" id="surname" name="surname" required placeholder="Inserisci il tuo cognome">
            </div>
            
            <div class="form_group">
                <label for="gender">Sesso:</label>
                <select id="gender" name="gender" required>
                    <option value="" disabled selected>Seleziona il tuo sesso</option>
                    <option value="Maschio">Maschio</option>
                    <option value="Femmina">Femmina</option>
                    <option value="Altro">Altro</option>
                </select>
            </div>
            
            <div class="form_group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="Inserisci la tua email">
            </div>
            <div class="form_group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Inserisci la tua password">
            </div>
            <div class="form_group">
                <label for="phone">Telefono:</label>
                <input type="tel" id="phone" name="phone" required placeholder="Inserisci il tuo numero di telefono">
            </div>
            <div class="form_group">
                <label for="business">Nome Attività:</label>
                <input type="text" id="business" name="business" required
                    placeholder="Inserisci il nome della tua attività">
            </div>
            <div class="form_group">
                <label for="address">Indirizzo:</label>
                <input type="text" id="address" name="address" required placeholder="Inserisci il tuo indirizzo">
            </div>
            <div class="form_group">
                <label for="city">Città:</label>
                <input type="text" id="city" name="city" required placeholder="Inserisci la tua città">
            </div>
            
                <input type="submitBtn" value="Registrati come Partner">
            
             <div>
                <p class="login_prompt">Hai già un account partner? 
                <a href="<%=request.getContextPath()%>/jsp_pubbliche/LoginRistoratore.jsp">Accedi</a></p>
            </div>
        </form>
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
                        popupText.innerHTML = "<h3>Termini e condizioni</h3><p>Ordinare è semplice: scegli, clicca, ritira... e non litigare col panino. Leggi queste regole prima di lamentarti perché il kebab non fa i salti mortali. Non siamo responsabili se ti innamori del nostro servizio (o del sushi). Ci riserviamo il diritto di aggiornare tutto... tranne la tua fame. Accetti tutto? Brav*. Adesso scegli il ristorante, il cibo non si ordina mica da solo!<p>";
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