document.addEventListener("DOMContentLoaded", () => {
  fetchAllCatalogos();  // Llamar a la función que obtiene todos los catálogos
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
