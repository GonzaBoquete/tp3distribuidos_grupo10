document.addEventListener("DOMContentLoaded", () => {
	fetchAllCatalogos();  // Llamar a la función que obtiene todos los catálogos

	// Añadir evento al botón de creación de catálogo
	const createButton = document.getElementById("create-catalogo");
	createButton.addEventListener("click", crearCatalogo);
});

function fetchAllCatalogos() {
	fetch('http://localhost:8080/api/catalogo/getAll')  // Endpoint para obtener todos los catálogos
		.then(response => response.json())
		.then(catalogos => {
			const catalogosDiv = document.getElementById("catalogos-list");

			// Si no hay catálogos, mostrar un mensaje
			if (!catalogos || catalogos.length === 0) {
				catalogosDiv.innerHTML = "<p>No se encontraron catálogos.</p>";
				return;
			}

			// Generar la lista de catálogos
			let catalogosHTML = "<ul>";
			catalogos.forEach(catalogo => {
				catalogosHTML += `
          <li class="list-group-item">
          <strong>ID Catálogo:</strong> ${catalogo.id}, 
          <strong>ID Tienda:</strong> ${catalogo.tienda.codigo}
          <br>
          <a href="/detalleCatalogo/${catalogo.id}" class="btn btn-link">Ver detalles</a>
        </li>
        `;
			});
			catalogosHTML += "</ul>";

			catalogosDiv.innerHTML = catalogosHTML;
		})
		.catch(error => console.error("Error al obtener los catálogos:", error));
}

function toggleForm() {
	const form = document.getElementById("catalogForm");
	form.style.display = form.style.display === "none" ? "block" : "none";
}

function submitForm(event) {
	event.preventDefault(); // Evita la recarga de la página
	const codigoTienda = document.getElementById("idTienda").value;

	fetch("/api/catalogo/add", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: new URLSearchParams({ idTienda: codigoTienda })
	})
		.then(response => {
			if (response.ok) {
				document.getElementById("message").textContent = "Catálogo creado correctamente";
				document.getElementById("message").classList.remove("d-none");
				document.getElementById("catalogForm").style.display = "none";
			} else {
				throw new Error("Error al crear el catálogo");
			}
		})
		.catch(error => {
			document.getElementById("message").textContent = error.message;
			document.getElementById("message").classList.remove("d-none");
			document.getElementById("message").classList.replace("alert-success", "alert-danger");
		});
}
