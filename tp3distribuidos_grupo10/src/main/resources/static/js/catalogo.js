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
        <p><strong>ID Catálogo:</strong> ${catalogo.idCatalogo}</p>
        <p><strong>ID Tienda:</strong> ${catalogo.idTienda}</p>
        <h3>Productos en el catálogo:</h3>
      `;

      // Generar la lista de productos
      if (catalogo.productos && catalogo.productos.length > 0) {
        catalogoHTML += "<ul>";
        catalogo.productos.forEach(producto => {
          catalogoHTML += `
            <li>
              <strong>Código:</strong> ${producto.codigo}, 
              <strong>Nombre:</strong> ${producto.nombre}, 
              <strong>Precio:</strong> ${producto.precio}
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
