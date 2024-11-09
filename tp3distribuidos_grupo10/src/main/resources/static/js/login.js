function login(event) {
    event.preventDefault();

    const nombreUsuario = document.getElementById("nombreUsuario").value;
    const contrasena = document.getElementById("contrasena").value;

    fetch("/api/usuario/login", {  
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `nombreUsuario=${encodeURIComponent(nombreUsuario)}&contrasena=${encodeURIComponent(contrasena)}`
    })
    .then(response => response.json()) 
    .then(data => {
        if (data.nombreUsuario === null) {
            document.getElementById("error").innerText = "Usuario o contraseña incorrectos.";
        } else {
            window.location.href = "/home"; 
        }
    })
    .catch(error => {
        document.getElementById("error").innerText = "Ocurrió un error al iniciar sesión.";
        console.error("Error:", error);
    });
}


function logout() {
    fetch("/api/usuario/logout", { 
        method: "POST", 
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/login";  // Redirigir al login después de cerrar sesión
        } else {
            console.error("Error al cerrar sesión");
        }
    })
    .catch(error => {
        console.error("Ocurrió un error al cerrar sesión:", error);
    });
}