document.addEventListener("DOMContentLoaded", () => {
  const pathSegments = window.location.pathname.split('/');
  const idOrdenDeCompra = pathSegments[pathSegments.length - 1];

  if (idOrdenDeCompra) {
    fetchOrdenCompraDetail(idOrdenDeCompra);
    loadProducts();  // Cargar los productos al cargar la página
  } else {
    console.error("Error: ID de orden de compra no encontrado en la URL");
  }

  // Evento para el formulario de agregar producto
  const addProductForm = document.getElementById("add-product-form");
  addProductForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const productSelect = document.getElementById("product-select");
    const cantidadInput = document.getElementById("cantidad");
    
    const codigoProducto = productSelect.value;
    const cantidad = cantidadInput.value;
    
    if (codigoProducto && cantidad > 0) {
      agregarProductoAOrden(idOrdenDeCompra, codigoProducto, cantidad);
    } else {
      alert("Por favor, selecciona un producto y una cantidad válida.");
    }
  });
});

function fetchOrdenCompraDetail(idOrdenDeCompra) {
  fetch(`http://localhost:8080/api/ordenDeCompra/getById?idOrdenDeCompra=${idOrdenDeCompra}`)
    .then(response => response.json())
    .then(ordenCompra => {
      const detailDiv = document.getElementById("orden-compra-detail");
      let ordenCompraHTML = `
        <div class="card mb-3">
          <div class="card-body">
            <h5 class="card-title">ID Orden de Compra: ${ordenCompra.id}</h5>
            <p class="card-text"><strong>Fecha:</strong> ${ordenCompra.fecha}</p>
            <p class="card-text"><strong>Estado:</strong> ${ordenCompra.estado}</p>
            <p class="card-text"><strong>Tienda:</strong> ${ordenCompra.tienda.codigo}</p>
            <h6 class="mt-3">Items en la orden de compra:</h6>
            <ul class="list-group">
      `;

      if (ordenCompra.itemsOrdenCompra && ordenCompra.itemsOrdenCompra.length > 0) {
        ordenCompra.itemsOrdenCompra.forEach(item => {
          ordenCompraHTML += `
            <li class="list-group-item">
              <p><strong>Cantidad:</strong> ${item.cantidad}</p>
              <p><strong>Producto Código:</strong> ${item.producto.codigo}</p>
              <p><strong>Nombre:</strong> ${item.producto.nombre}</p>
              <p><strong>Talle:</strong> ${item.producto.talle}</p>
              <p><strong>Color:</strong> ${item.producto.color}</p>
              ${item.producto.foto ? `<img src="${item.producto.foto}" style="max-width: 100px;">` : "<p>Sin foto disponible</p>"}
              <button class="btn btn-danger position-absolute top-0 end-0 m-2" onclick="eliminarItemOrden(${ordenCompra.id}, ${item.id})">Eliminar</button>
            </li>
          `;
        });
      } else {
        ordenCompraHTML += "<p>No hay items en esta orden de compra.</p>";
      }

      ordenCompraHTML += "</ul></div></div>";
      detailDiv.innerHTML = ordenCompraHTML;
    })
    .catch(error => console.error("Error al obtener detalles de la orden de compra:", error));
}

function eliminarItemOrden(idOrdenDeCompra, idItemOrdenDeCompra) {
  fetch(`http://localhost:8080/api/ordenDeCompra/eliminarItem?idOrdenDeCompra=${idOrdenDeCompra}&idItemOrdenDeCompra=${idItemOrdenDeCompra}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    }
  })
  .then(response => {
    if (response.ok) {
      fetchOrdenCompraDetail(idOrdenDeCompra);
    } else {
      throw new Error("Error al eliminar producto");
    }
  })
  .catch(error => console.error("Error al eliminar producto de la Orden de Compra:", error));
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

function agregarProductoAOrden(idOrdenDeCompra, codigoProducto, cantidad) {
	  // Construir la URL con los parámetros
	  const url = new URL("http://localhost:8080/api/ordenDeCompra/agregarItem");
	  const params = {
	    codigoProducto: codigoProducto,
	    cantidad: cantidad,
	    idOrdenDeCompra: idOrdenDeCompra
	  };

	  Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));

	  fetch(url, {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json"
	    }
	  })
	  .then(response => {
	    if (response.ok) {
	      fetchOrdenCompraDetail(idOrdenDeCompra);  // Refrescar los detalles de la orden
	      
	    } else {
	      throw new Error("Error al agregar producto");
	    }
	  })
	  .catch(error => console.error("Error al agregar producto a la Orden de Compra:", error));
	}
