const popup = document.getElementById("popup");
       const popupText = document.getElementById("popup-text");
       const closeBtn = document.querySelector(".close");
       const userIcon = document.querySelector(".user-icon");

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
	   userIcon.addEventListener("click", async () => {
	          try {
	              // Prendi il contesto dinamicamente dall'URL corrente
	              const contextPath = window.location.pathname.split('/')[1];
	              const url = `/${contextPath}/UserInfoServlet`;

	              const response = await fetch(url, { credentials: 'include' });

	              if (!response.ok) throw new Error("Utente non autenticato");

	              const html = await response.text();

	              popupText.innerHTML = html;
	              popup.style.display = "flex";
	          } catch (error) {
	              popupText.innerHTML = `<p>${error.message}</p>`;
	              popup.style.display = "flex";
	          }
	   	   
	   	   closeBtn.addEventListener("click", () => {
	   	       popup.style.display = "none";
	   	   });
	      });

       window.addEventListener("click", (e) => {
           if (e.target === popup) {
               popup.style.display = "none";
           }
       });

       //MODIFICARE SCRIPT CON IMPLEMENTAZIONE DELLA SERVLET ATTRAVERSO DATI DATABASE
       // Dati statici esempio (hardcoded)


       document.addEventListener("DOMContentLoaded", () => {
           const track = document.getElementById("carouselTrack");
           const leftArrow = document.querySelector(".carousel_arrow.left");
           const rightArrow = document.querySelector(".carousel_arrow.right");
           const scrollAmount = 270;

           function updateCenter() {
               const boxes = track.querySelectorAll(".food_box");
               boxes.forEach(box => box.classList.remove("centered"));

               // Trova l'elemento centrale
               const centerIndex = Math.floor(boxes.length / 2);
               if (boxes[centerIndex]) {
                   boxes[centerIndex].classList.add("centered");
               }
           }

           function moveLeft() {
               const lastItem = track.lastElementChild;
               track.insertBefore(lastItem, track.firstElementChild);
               updateCenter();
           }

           function moveRight() {
               const firstItem = track.firstElementChild;
               track.appendChild(firstItem);
               updateCenter();
           }

           leftArrow.addEventListener("click", moveLeft);
           rightArrow.addEventListener("click", moveRight);

           // Effettua il primo aggiornamento al caricamento
           updateCenter();
       });

       // === CARRELLO ===

       const cartIcon = document.querySelector(".cart-icon");
       const cartSidebar = document.getElementById("cartSidebar");
       const overlay = document.getElementById("overlay");
       const closeCartBtn = document.querySelector(".close-cart");
       const cartItemsContainer = document.getElementById("cartItem");
       const checkoutBtn = document.getElementById("checkoutBtn");
       const mainContent = document.querySelector(".main-content"); // <-- PRIMA COSA: seleziona il contenitore principale

       // Dati statici iniziali
       let carrello = [
           { nome: "Pizza Margherita", quantita: 1 },
           { nome: "Hamburger Classico", quantita: 2 }
       ];

       function aggiornaCarrello() {
           cartItemsContainer.innerHTML = "";

           if (carrello.length === 0) {
               cartItemsContainer.innerHTML = "<p>Il tuo carrello è vuoto.</p>";
               checkoutBtn.style.display = "none";
               return;
           }

           carrello.forEach((item, index) => {
               const itemDiv = document.createElement("div");
               itemDiv.classList.add("cart-product");  // usa classe corretta per stile colonnare

               itemDiv.innerHTML = `
           <div class="cart-item-name">${item.nome}</div>
           <div class="quantity-controls">
               <button onclick="modificaQuantita(${index}, -1)">–</button>
               <span class="cart-item-quantity">${item.quantita}</span>
               <button onclick="modificaQuantita(${index}, 1)">+</button>
           </div>
       `;

               cartItemsContainer.appendChild(itemDiv);
           });

           checkoutBtn.style.display = "block";
       }

       function modificaQuantita(index, delta) {
           carrello[index].quantita += delta;

           if (carrello[index].quantita <= 0) {
               carrello.splice(index, 1);
           }

           aggiornaCarrello();
       }

       window.modificaQuantita = modificaQuantita;

       checkoutBtn.addEventListener("click", () => {
           alert("Ordine completato!");
           carrello = [];
           aggiornaCarrello();
           cartSidebar.classList.remove("active");
           overlay.classList.remove("active");
           mainContent.classList.remove("blur");  // <- RIMUOVI BLUR AL COMPLETARE ORDINE

       });

       cartIcon.addEventListener("click", () => {
           cartSidebar.classList.add("active");
           overlay.classList.add("active");
           aggiornaCarrello();
           mainContent.classList.add("blur");      // <- AGGIUNGI BLUR QUANDO APRI SIDEBAR

       });

       closeCartBtn.addEventListener("click", () => {
           cartSidebar.classList.remove("active");
           overlay.classList.remove("active");
           mainContent.classList.remove("blur");  // <- RIMUOVI BLUR QUANDO CHIUDI SIDEBAR

       });

       overlay.addEventListener("click", () => {
           cartSidebar.classList.remove("active");
           overlay.classList.remove("active");
           mainContent.classList.remove("blur");  // <- RIMUOVI BLUR QUANDO CHIUDI SIDEBAR CLICCANDO FUORI

       });
