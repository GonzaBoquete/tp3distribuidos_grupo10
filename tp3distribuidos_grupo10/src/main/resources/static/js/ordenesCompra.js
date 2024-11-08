async function fetchOrdenes() {
    try {
        const response = await fetch('http://localhost:8080/api/ordenDeCompra/getAll');
        const data = await response.json();
        
        if (data && Array.isArray(data)) {
            const tableBody = document.querySelector('#ordenesDeCompraTable tbody');
            tableBody.innerHTML = ''; 
            data.forEach(orden => {
                const row = document.createElement('tr');
                
                row.innerHTML = `
                    <td>${orden.id}</td>
                    <td>${orden.fecha}</td>
                    <td>${orden.proveedor}</td>
                    <td>${orden.cantidad}</td>
                    <td>${orden.estado}</td>
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
