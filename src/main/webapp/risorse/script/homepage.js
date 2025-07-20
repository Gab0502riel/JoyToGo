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
                       popupText.innerHTML = "<h3>Contatti</h3><p>Email: support@joytogo.it<br>Telefono: +39 123 456 7890</p>";
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


document.addEventListener("DOMContentLoaded", function() {
     const btn = document.getElementById("theme-toggle");
     const html = document.documentElement;

     // Funzione per impostare il tema
     function setTheme(theme) {
       html.setAttribute("data-theme", theme);
       localStorage.setItem("theme", theme);
       
       // Aggiorna l'icona
	   const icon = btn.querySelector("i");
	   if (!icon) return;  // blocca se non trova l'icona

	   if (theme === "dark") {
	     icon.classList.remove("fa-moon");
	     icon.classList.add("fa-sun");
	   } else {
	     icon.classList.remove("fa-sun");
	     icon.classList.add("fa-moon");
	   }

       
	   // Invia la richiesta al server per aggiornare il cookie
	          updateThemeCookie(theme);

     }

     // Funzione per aggiornare il cookie lato server
	 function updateThemeCookie(theme) {
	   fetch(`${contextPath}/ThemeModeServlet`, {
	     method: 'POST',
	     headers: {
	       'Content-Type': 'application/x-www-form-urlencoded',
	     },
	     body: `theme=${theme}`
	   });
	 }

	 



     // Controlla il tema preferito
	 function initTheme() {
	   const storedTheme = localStorage.getItem("theme");
	   const cookieTheme = getCookie("theme");
	   const systemPrefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
	   
	   const theme = cookieTheme || storedTheme || (systemPrefersDark ? "dark" : "light");
	   
	   // Sincronizza localStorage con cookie se diversi
	   if (cookieTheme && cookieTheme !== storedTheme) {
	     localStorage.setItem("theme", cookieTheme);
	   }
	   
	   setTheme(theme);
	 }


     // Gestore per il toggle
     btn.addEventListener("click", function() {
       const currentTheme = html.getAttribute("data-theme");
       const newTheme = currentTheme === "dark" ? "light" : "dark";
       setTheme(newTheme);
     });

     // Funzione helper per i cookie
     function getCookie(name) {
       const value = `; ${document.cookie}`;
       const parts = value.split(`; ${name}=`);
       if (parts.length === 2) return parts.pop().split(';').shift();
     }

     // Inizializza il tema
     initTheme();

     // Ascolta i cambiamenti delle preferenze del sistema
     window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', e => {
       if (!localStorage.getItem("theme") && !getCookie("theme")) {
         setTheme(e.matches ? "dark" : "light");
       }
     });
   });