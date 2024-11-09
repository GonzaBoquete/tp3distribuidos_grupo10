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
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error("Error de autenticación");
        }
    })
    .then(data => {
        if (data.nombreUsuario === null || data.nombre === null) {
            throw new Error("Usuario o contraseña incorrectos.");
        }

        window.location.href = "/home";
    })
    .catch(error => {
        document.getElementById("error").innerText = error.message;
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
            window.location.href = "/login";
        } else {
            throw new Error("Error al cerrar sesión");
        }
    })
    .catch(error => {
        console.error("Ocurrió un error al cerrar sesión:", error);
    });
}


