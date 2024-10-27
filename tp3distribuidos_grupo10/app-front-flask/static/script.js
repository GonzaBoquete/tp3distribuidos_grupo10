// scripts.js

// Ocultar mensaje de éxito/error después de unos segundos
document.addEventListener('DOMContentLoaded', function () {
    const flashMessage = document.querySelector('.flash-message');
    if (flashMessage) {
        setTimeout(() => {
            flashMessage.style.display = 'none';
        }, 3000);
    }
});

// Confirmación para acciones de eliminación
function confirmAction(message) {
    return confirm(message || "¿Estás seguro de que deseas realizar esta acción?");
}

// Evento para enviar formulario con confirmación
document.querySelectorAll('form.confirm').forEach(form => {
    form.addEventListener('submit', (event) => {
        const confirmed = confirmAction("¿Estás seguro de que deseas continuar?");
        if (!confirmed) {
            event.preventDefault();
        }
    });
});

// Función para mostrar u ocultar elementos según filtros seleccionados
function toggleElementVisibility(filterElement, targetElements) {
    filterElement.addEventListener('change', function () {
        const selectedValue = filterElement.value;
        targetElements.forEach(element => {
            element.style.display = element.dataset.value === selectedValue ? 'block' : 'none';
        });
    });
}
