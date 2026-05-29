console.log("Script de tabla de solicitudes cargado correctamente");

function cargarSolicitudes() {
    const tbody = document.querySelector('#tablaSolicitudes tbody');
    tbody.innerHTML='';

    fetch('http://localhost:8080/api/solicitudes', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener las solicitudes');
            }
            else
            {
                
            }
            return response.json();
        })
        .then(solicitudes => {
            if (solicitudes.length === 0) {
                tbody.innerHTML = `
                    <tr>
                        <td>
                            No hay solicitudes registradas aún.
                        </td>
                    </tr>`;
                return;
            }

            solicitudes.forEach(s => {
                const fila = document.createElement('tr');
                fila.innerHTML = `
                    <td>${s.id}</td>
                    <td>${s.nombre || '-'}</td>
                    <td>${s.email || '-'}</td>
                    <td>${s.telefono || '-'}</td>
                    <td>${s.descripcion || '-'}</td>
                `;
                tbody.appendChild(fila);
            });
        })
        .catch(error => {
            console.error(error);
            tbody.innerHTML = `
                <tr>
                    <td>
                        Error al conectar con el servidor.
                    </td>
                </tr>`;
        });
}