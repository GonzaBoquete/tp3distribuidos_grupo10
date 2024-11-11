function cargarTiendas() {
    fetch('/api/tienda/getAll')  
        .then(response => response.json())
        .then(tiendas => {
            const tiendaSelect = document.getElementById('tienda');
            tiendaSelect.innerHTML = '<option value="">Seleccione una Tienda</option>';
            tiendas.filter(tienda => tienda.habilitada)
                .forEach(tienda => {
                    const option = document.createElement('option');
                    option.value = tienda.codigo;  
                    option.textContent = tienda.codigo;  
                    tiendaSelect.appendChild(option);
                });
        })
        .catch(error => console.error('Error al cargar tiendas:', error));
}

function cargarEstados() {
    const estadoSelect = document.getElementById('estado');
    estadoSelect.innerHTML = '<option value="">Seleccione un Estado</option>';
    
    const estados = ['SOLICITADA', 'RECHAZADA', 'ACEPTADA'];
    
    estados.forEach(estado => {
        const option = document.createElement('option');
        option.value = estado;
        option.textContent = estado;
        estadoSelect.appendChild(option);
    });
}

async function guardarFiltro() {
    const nombreFiltro = document.getElementById('nombreFiltro').value;

    // Captura de los valores de los filtros
    const codigoTienda = document.getElementById('tienda').value;
    const codigoProducto = document.getElementById('codigoProducto').value;
    const fechaDesde = document.getElementById('fechaDesde').value;
    const fechaHasta = document.getElementById('fechaHasta').value;
    const estado = document.getElementById('estado').value;

    // Verificar que `idUsuario` esté definido
    if (!idUsuario) {
        console.error('Error: idUsuario no está definido.');
        alert('No se pudo obtener el ID del usuario.');
        return;
    }

    if (!nombreFiltro) {
        alert("Por favor ingrese un nombre para el filtro.");
        return;
    }

    // Construir los parámetros de la URL
    const params = new URLSearchParams({
        nombreFiltro: nombreFiltro,
        idUsuario: idUsuario,
        codigoTienda: codigoTienda,
        codigoProducto: codigoProducto,
        fechaDesde: fechaDesde,
        fechaHasta: fechaHasta,
        estado: estado
    });

    // Construir la URL completa
    const url = `/api/filtro/add?${params.toString()}`;

    // Enviar los datos al endpoint con método GET
    try {
        const response = await fetch(url, {
            method: 'POST',  
            headers: {
                'Content-Type': 'application/json'
            }
        });

        const result = await response.json();
        if (result.status == 200 ) {
            alert('Filtro guardado con éxito');
            const modal = bootstrap.Modal.getInstance(document.getElementById('guardarFiltroModal'));
            modal.hide();
        } else {
            alert('Error al guardar el filtro');
        }
    } catch (error) {
        console.error('Error al guardar el filtro:', error);
        alert('Hubo un error al guardar el filtro.');
    }
}
function cargarFiltrosUsuario() {
    fetch('/api/filtro/getAll')  // Traemos todos los filtros
        .then(response => response.json())
        .then(filtros => {
            console.log(filtros);  // Verifica qué datos estás recibiendo
            if (!filtros || filtros.length === 0) {
                console.log('No se recibieron filtros.');
                return;
            }

            // Filtramos los filtros por el idUsuario
            const filtrosFiltrados = filtros.filter(filtro => filtro.usuario.id === idUsuario);

            // Verificamos si se han filtrado resultados
            if (filtrosFiltrados.length === 0) {
                console.log('No se encontraron filtros para el idUsuario:', idUsuario);
            }

            // Referencia al select de filtros
            const filtroSelect = document.getElementById('filtro');
            filtroSelect.innerHTML = '<option value="">Seleccione un Filtro</option>';  // Reseteamos el select

            // Agregamos los filtros correspondientes al select
            filtrosFiltrados.forEach(filtro => {
                const option = document.createElement('option');
                option.value = filtro.id;
                option.textContent = filtro.nombre;  // Solo mostramos el atributo "nombre"
                filtroSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al cargar filtros:', error));
}


document.addEventListener('DOMContentLoaded', function() {
    cargarTiendas();  
    cargarEstados();
    cargarFiltrosUsuario();
});
