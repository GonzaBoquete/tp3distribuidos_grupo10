document.addEventListener('DOMContentLoaded', () => {
    // Llamada automática a la función para cargar las órdenes de compra al cargar la página
    fetchOrdenes();
});

async function fetchOrdenes() {
    try {
        const response = await fetch('http://localhost:8080/api/ordenDeCompra/getAll');
        const data = await response.json();

        if (data && Array.isArray(data)) {
            const tableBody = document.querySelector('#ordenesDeCompraTable tbody');
            tableBody.innerHTML = ''; // Limpiar la tabla antes de agregar nuevas filas

            data.forEach(orden => {
                const row = document.createElement('tr');

                // Mostrar ID, fecha, tienda (ID de la tienda), cantidad total y estado
                const totalCantidad = orden.itemsOrdenCompra.reduce((total, item) => total + item.cantidad, 0);

                row.innerHTML = `
                    <td>${orden.id}</td>
                    <td>${orden.fecha}</td>
                    <td>${orden.tienda.codigo}</td>
                    <td>${totalCantidad}</td>
                    <td>${orden.estado}</td>
                    <td>
                        <a href="/ordenCompra/${orden.id}" class="btn btn-info">Ver detalle</a>
                    </td>
                `;

                tableBody.appendChild(row);
            });
        } else {
            alert('No se encontraron órdenes de compra.');
        }
    } catch (error) {
        console.error('Error al cargar las órdenes de compra:', error);
        alert('Hubo un error al obtener las órdenes de compra.');
    }
}
