console.log("Script de tabla de solicitudes cargado correctamente");

//listener para cargar las filas cuando se carga la pagina
document.getElementById('formBuscar').addEventListener('submit', function(event) {
    event.preventDefault();
    const criterio = document.getElementById('buscarSolicitud').value.trim(); 
    BuscarSolicitudes(criterio);
});
//listener para checkear el boton buscar
document.getElementById('formBuscar').addEventListener('reset', function(event) {
    event.preventDefault();
    document.getElementById('buscarSolicitud').value = "";
    cargarSolicitudes();
});
//listener para checkear el boton cancelar
document.addEventListener('DOMContentLoaded', function() {
    cargarSolicitudes();
});

document.querySelector('#tablaSolicitudes tbody').addEventListener('click', function(event) {
    const botonBorrar = event.target.closest('.btn-borrar');
    
    if (botonBorrar) {
        event.preventDefault();
        
        const idSolicitud = botonBorrar.getAttribute('data-id');

        if (confirm(`¿Estás seguro de que deseas eliminar la solicitud #${idSolicitud}?`)) {
            EliminarSolicitud(idSolicitud);
        }
    }
});
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
                    <td style="text-align: center;">
            <a href="#" class="btn-borrar" data-id="${s.id}" title="Borrar">
                <img src="./source/img/basuraIcon.png" style="width: 20px; height: 20px; cursor: pointer;">
            </a>
        </td>
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
function BuscarSolicitudes(criterio) {
    const tbody = document.querySelector('#tablaSolicitudes tbody');
    tbody.innerHTML = `<tr><td colspan="5" style="text-align: center;">Buscando...</td></tr>`;

    const url = new URL('http://localhost:8080/api/solicitudes/buscar');
    if (criterio) {
        url.searchParams.append('desc', criterio);
    }

    fetch(url.href, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener las solicitudes');
            }
            return response.json();
        })
        .then(solicitudes => {
        tbody.innerHTML = '';
            if (solicitudes.length === 0) {
               tbody.innerHTML = `
                <tr>
                    <td colspan="5" style="text-align: center;">
                        No se encontraron solicitudes según el criterio de búsqueda.
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
            <td colspan="5" style="text-align: center;"> <!-- <--- Aniadido colspan y centrado -->
                Error al conectar con el servidor.
            </td>
        </tr>`;
    });
}
function EliminarSolicitud(id) {
    const url = `http://localhost:8080/api/solicitudes/borrar/${id}`;

    fetch(url, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            // Buscamos la fila HTML por su ID asignado anteriormente
            const filaAsociada = document.getElementById(`fila-solicitud-${id}`);
            if (filaAsociada) {
                filaAsociada.remove(); // Remueve la fila de la tabla inmediatamente
            }
            alert('Solicitud eliminada con éxito.');
        } else {
            throw new Error('No se pudo eliminar el registro del servidor.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al intentar eliminar la solicitud.');
    });
}