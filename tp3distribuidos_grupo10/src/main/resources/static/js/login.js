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
