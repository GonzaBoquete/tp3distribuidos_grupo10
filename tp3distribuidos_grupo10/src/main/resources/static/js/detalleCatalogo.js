document.addEventListener("DOMContentLoaded", () => {
  const pathSegments = window.location.pathname.split('/');
  const idCatalogo = pathSegments[pathSegments.length - 1];

  if (idCatalogo) {
    fetchCatalogoDetail(idCatalogo);
    loadProducts(); // Cargar los productos en el select
  } else {
    console.error("Error: ID de catálogo no encontrado en la URL");
  }

  // Manejar el envío del formulario
  document.getElementById("add-product-form").addEventListener("submit", (event) => {
    event.preventDefault();
    const selectedProduct = document.getElementById("product-select").value;
    if (selectedProduct) {
      addProductToCatalog(idCatalogo, selectedProduct);
    } else {
      alert("Por favor, seleccione un producto.");
    }
  });
});

function fetchCatalogoDetail(idCatalogo) {
	  fetch(`http://localhost:8080/api/catalogo/getById?idCatalogo=${idCatalogo}`)
	    .then(response => response.json())
	    .then(catalogo => {
	      const detailDiv = document.getElementById("catalogo-detail");
	      let catalogoHTML = `
	        <div class="card mb-3">
	          <div class="card-body">
	            <h5 class="card-title">ID Catálogo: ${catalogo.id}</h5>
	            <p class="card-text"><strong>ID Tienda:</strong> ${catalogo.tienda.codigo}</p>
	            <h6 class="mt-3">Productos en el catálogo:</h6>
	            <ul class="list-group">
	      `;
	      if (catalogo.producto && catalogo.producto.length > 0) {
	        catalogo.producto.forEach(producto => {
	          catalogoHTML += `
	            <li class="list-group-item position-relative">
	              <p><strong>Código:</strong> ${producto.codigo}</p>
	              <p><strong>Nombre:</strong> ${producto.nombre}</p>
	              <p><strong>Talle:</strong> ${producto.talle}</p>
	              <p><strong>Color:</strong> ${producto.color}</p>
	              ${producto.foto ? `<img src="${producto.foto}" style="max-width: 100px;">` : "<p>Sin foto disponible</p>"}
	              <button class="btn btn-danger position-absolute top-0 end-0 m-2" onclick="removeProductFromCatalog(${idCatalogo}, ${producto.codigo})">Eliminar</button>
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

function loadProducts() {
  fetch("http://localhost:8080/api/producto/getAll")
    .then(response => response.json())
    .then(productos => {
      const productSelect = document.getElementById("product-select");
      productos.forEach(producto => {
        const option = document.createElement("option");
        option.value = producto.codigo;
        option.textContent = `${producto.codigo} - ${producto.nombre}`;
        productSelect.appendChild(option);
      });
    })
    .catch(error => console.error("Error al cargar productos:", error));
}

function addProductToCatalog(codigoCatalogo, codigoProducto) {
	  fetch(`http://localhost:8080/api/catalogo/agregarProducto?codigoCatalogo=${codigoCatalogo}&codigoProducto=${codigoProducto}`, {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json"
	    }
	  })
	  .then(response => {
	    if (response.ok) {
	      fetchCatalogoDetail(codigoCatalogo); 
	    } else {
	      throw new Error("Error al agregar producto");
	    }
	  })
	  .catch(error => console.error("Error al agregar producto al catálogo:", error));
	}


function removeProductFromCatalog(codigoCatalogo, codigoProducto) {
  fetch(`http://localhost:8080/api/catalogo/eliminarProducto?codigoCatalogo=${codigoCatalogo}&codigoProducto=${codigoProducto}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    }
  })
  .then(response => {
    if (response.ok) {
      fetchCatalogoDetail(codigoCatalogo); 
    } else {
      throw new Error("Error al eliminar producto");
    }
  })
  .catch(error => console.error("Error al eliminar producto del catálogo:", error));
}