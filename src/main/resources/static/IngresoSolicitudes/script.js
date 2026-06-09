console.log("Archivo script.js cargado correctamente");

function enviarDatos() {
    console.log("Función enviarDatos ejecutada");

    const nombreInput = document.getElementById("nombre");
    const emailInput = document.getElementById("email");
    const telefonoInput = document.getElementById("telefono");
    const descripcionInput = document.getElementById("descripcion");

    // Verificamos que los elementos existan en el HTML
    if (!nombreInput || !emailInput || !telefonoInput || !descripcionInput) {
        console.error("No se encontró uno de los inputs. Revisa los IDs en el HTML.");
        return;
    }

    const datos = {

        nombre: nombreInput.value,
        email: emailInput.value,
        telefono: telefonoInput.value,
        descripcion: descripcionInput.value
    };

    fetch("http://localhost:8080/api/solicitudes", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })
    .then(response => {
        if (response.ok) {
            alert("¡Solicitud enviada!");
        } else {
            alert("Error al enviar: " + response.status);
        }
    })
    .catch(error => {
        console.error("Error de conexión:", error);
        alert("No se pudo conectar con el servidor.");
    });
}