document.addEventListener("DOMContentLoaded", () => {
  const pathSegments = window.location.pathname.split('/');
  const idCatalogo = pathSegments[pathSegments.length - 1];  // Obtiene el último segmento de la URL

  if (idCatalogo) {
    fetchCatalogoDetail(idCatalogo);
  } else {
    console.error("Error: ID de catálogo no encontrado en la URL");
  }
});

function fetchCatalogoDetail(idCatalogo) {
  fetch(`http://localhost:8080/api/catalogo/getById?idCatalogo=${idCatalogo}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then(catalogo => {
      const detailDiv = document.getElementById("catalogo-detail");

      // Construir HTML para mostrar la información del catálogo y sus productos
      let catalogoHTML = `
        <div class="card mb-3">
          <div class="card-body">
            <h5 class="card-title">ID Catálogo: ${catalogo.id}</h5>
            <p class="card-text"><strong>ID Tienda:</strong> ${catalogo.tienda.codigo}</p>
            <h6 class="mt-3">Productos en el catálogo:</h6>
            <ul class="list-group">
      `;

      // Ajuste para la lista de productos
      if (catalogo.producto && catalogo.producto.length > 0) {  // Cambiado a "producto" en singular
        catalogo.producto.forEach(producto => {
          catalogoHTML += `
            <li class="list-group-item">
              <div style="display: flex; align-items: center;">
                <div style="flex: 1;">
                  <p><strong>Código:</strong> ${producto.codigo}</p>
                  <p><strong>Nombre:</strong> ${producto.nombre}</p>
                  <p><strong>Talle:</strong> ${producto.talle}</p>
                  <p><strong>Color:</strong> ${producto.color}</p>
                </div>
                <div style="flex: 1; display: flex; justify-content: center;">
                  ${producto.foto ? `<img src="${producto.foto}" alt="Foto de ${producto.nombre}" style="max-width: 100px; border-radius: 8px;">` : "<p>Sin foto disponible</p>"}
                </div>
              </div>
            </li>
          `;
        });
      } else {
        catalogoHTML += "<p>No hay productos en este catálogo.</p>";
      }

      catalogoHTML += "</ul></div></div>";
      detailDiv.innerHTML = catalogoHTML;
    })
    .catch(error => console.error("Error al obtener detalles del catálogo:", error));
}
