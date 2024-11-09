document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const idCatalogo = params.get("idCatalogo");
  fetchCatalogoDetail(idCatalogo);
});

function fetchCatalogoDetail(idCatalogo) {
  fetch(`http://localhost:8080/api/catalogo/getById?idCatalogo=${idCatalogo}`)
    .then(response => response.json())
    .then(catalogo => {
      const detailDiv = document.getElementById("catalogo-detail");

      // Mostrar la información del catálogo
      let catalogoHTML = `
        <div class="card mb-3">
        <div class="card-body">
          <h5 class="card-title">ID Catálogo: ${catalogo.id}</h5>
          <p class="card-text"><strong>ID Tienda:</strong> ${catalogo.tienda.codigo}</p>
          <h6 class="mt-3">Productos en el catálogo:</h6>
          <ul class="list-group">
      `;

      // Generar la lista de productos
      if (catalogo.productos && catalogo.productos.length > 0) {
        catalogoHTML += "<ul>";
        catalogo.productos.forEach(producto => {
          catalogoHTML += `
             <li class="list-group-item">
            <div class="d-flex justify-content-between">
              <div>
                <p><strong>Código:</strong> ${producto.codigo}</p>
                <p><strong>Nombre:</strong> ${producto.nombre}</p>
                <p><strong>Talle:</strong> ${producto.talle}</p>
                <p><strong>Color:</strong> ${producto.color}</p>
              </div>
              <div>
                ${producto.foto ? 
                  `<img src="${producto.foto}" alt="Foto de ${producto.nombre}" style="max-width: 100px; border-radius: 8px;">` 
                  : "<p>Sin foto disponible</p>"
                }
              </div>
            </div>
          </li>
          `;
        });
        catalogoHTML += "</ul>";
      } else {
        catalogoHTML += "<p>No hay productos en este catálogo.</p>";
      }

      detailDiv.innerHTML = catalogoHTML;
    })
    .catch(error => console.error("Error al obtener detalles del catálogo:", error));
}
