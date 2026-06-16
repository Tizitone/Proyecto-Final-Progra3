const empleadoApiUrl = "http://localhost:8080/api/empleados";

const formEmpleado = document.getElementById("formEmpleado");
const idEmpleadoInput = document.getElementById("idEmpleado");

const empleadoEmailInput = document.getElementById("emailEmpleado");
const empleadoContraInput = document.getElementById("contraseniaEmpleado");
const empleadoNombreInput = document.getElementById("nombreEmpleado");
const empleadoTelefonoInput = document.getElementById("telefonoEmpleado");
const empleadoDniInput = document.getElementById("dniEmpleado");
const tablaPersona = document.querySelector("#tablaPersonas tbody");
const empleadoMensaje = document.getElementById("empleadoMensaje");

formEmpleado.addEventListener("submit", async function (e) {
    e.preventDefault();

    const empleado = {
        email: empleadoEmailInput.value,
        contrasenia: empleadoContraInput.value,
        nombre: empleadoNombreInput.value,
        telefono: empleadoTelefonoInput.value,
        acceso: "empleado",
        dni: empleadoDniInput.value 
    };

    try {
        await fetch(empleadoApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(empleado)
        });

        empleadoMensaje.textContent = "Empleado agregado correctamente.";

    } catch(error) {
        empleadoMensaje.textContent = "Error al guardar empleado.";
    }
});

function cancelarEdicionEmpleado() {
    formEmpleado.reset();
    idEmpleadoInput.value = "";
    empleadoMensaje.textContent = "Edición cancelada";
}

const gerenteApiUrl = "http://localhost:8080/api/gerentes";

const formGerente = document.getElementById("formGerente");
const idGerenteInput = document.getElementById("idGerente");
const gerenteEmailInput = document.getElementById("emailGerente");
const gerenteContraInput = document.getElementById("contraseniaGerente");
const gerenteNombreInput = document.getElementById("nombreGerente");
const gerenteTelefonoInput = document.getElementById("telefonoGerente");
const gerenteDniInput = document.getElementById("dniGerente")
const gerenteMensaje = document.getElementById("gerenteMensaje");

formGerente.addEventListener("submit", async function (e) {
    e.preventDefault();

    const gerente = {
        email: gerenteEmailInput.value,
        contrasenia: gerenteContraInput.value,
        nombre: gerenteNombreInput.value,
        telefono: gerenteTelefonoInput.value,
        acceso: "gerente",
        dni: gerenteDniInput.value 
    };

    try {
        await fetch(gerenteApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(gerente)
        });

        gerenteMensaje.textContent = "Gerente agregado correctamente.";

    } catch(error) {
        gerenteMensaje.textContent = "Error al guardar gerente.";
    }
});

function cancelarEdicionGerente() {
    formGerente.reset();
    idGerenteInput.value = "";
    gerenteMensaje.textContent = "Edición cancelada";
}

const adminApiUrl = "http://localhost:8080/api/administradores";

const formAdmin = document.getElementById("formAdmin");
const idAdminInput = document.getElementById("idAdmin");
const adminEmailInput = document.getElementById("emailAdmin");
const adminContraInput = document.getElementById("contraseniaAdmin");
const adminNombreInput = document.getElementById("nombreAdmin");
const adminTelefonoInput = document.getElementById("telefonoAdmin");
const adminDniInput = document.getElementById("dniAdmin")
const adminMensaje = document.getElementById("adminMensaje");

formAdmin.addEventListener("submit", async function (e) {
    e.preventDefault();

    const admin = {
        email: adminEmailInput.value,
        contrasenia: adminContraInput.value,
        nombre: adminNombreInput.value,
        telefono: adminTelefonoInput.value,
        acceso: "ADMIN",
        dni: adminDniInput.value 
    };

    try {
        await fetch(adminApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(admin)
        });

        adminMensaje.textContent = "Administrador agregado correctamente.";
        console.log("Administrador agregado correctamente.");

    } catch(error) {
        adminMensaje.textContent = "Error al guardar administrador.";
        console.log("Error al guardar administrador.");
    }
});

function cancelarEdicionAdmin() {
    formAdmin.reset();
    idAdminInput.value = "";
    adminMensaje.textContent = "Edición cancelada";
}

const clienteApiUrl = "http://localhost:8080/api/clientes";

const formCliente = document.getElementById("formCliente");
const idClienteInput = document.getElementById("idCliente");
const clienteEmailInput = document.getElementById("emailCliente");
const clienteContraInput = document.getElementById("contraseniaCliente");
const clienteNombreInput = document.getElementById("nombreCliente");
const clienteTelefonoInput = document.getElementById("telefonoCliente");
const clienteDniInput = document.getElementById("dniCliente")
const clienteMensaje = document.getElementById("clienteMensaje");

formCliente.addEventListener("submit", async function (e) {
    e.preventDefault();

    const cliente = {
        email: clienteEmailInput.value,
        contrasenia: clienteContraInput.value,
        nombre: clienteNombreInput.value,
        telefono: clienteTelefonoInput.value,
        acceso: "cliente",
        dni: clienteDniInput.value 
    };

    try {
        await fetch(clienteApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cliente)
        });

        clienteMensaje.textContent = "Cliente agregado correctamente.";
        console.log("Cliente creado correctamente");

    } catch(error) {
        clienteMensaje.textContent = "Error al guardar cliente.";
        console.log("Error creando un cliente.");
    }
});

function cancelarEdicionCliente() {
    formCliente.reset();
    idClienteInput.value = "";
    clienteMensaje.textContent = "Edición cancelada";
}

const tareaApiUrl = "http://localhost:8080/api/tareas";

const formTarea = document.getElementById("formTarea");
const idTareaInput = document.getElementById("idTarea");
const fechaEntregaInput = document.getElementById("FechaEntrega");
const fechaRegistroInput = document.getElementById("FechaRegistro");
const descGeneralInput = document.getElementById("descripcionGeneral");
const descMateialInput = document.getElementById("descripcionMaterial");
const categoriaInput = document.getElementById("categoria")
const tareaMensaje = document.getElementById("tareaMensaje");

formTarea.addEventListener("submit", async function (e) {
    e.preventDefault();

    const tarea = {
        fecha_de_entrega: fechaEntregaInput.value,
        fecha_de_registro: fechaRegistroInput.value,
        descripcion_material: descMateialInput.value,
        descripcion_general: descGeneralInput.value,
        categorias: categoriaInput.value,
    };

    try {
        await fetch(tareaApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(tarea)
        });

        tareaMensaje.textContent = "Tarea agregada correctamente.";
        console.log("Tarea creada correctamente");
        formTarea.reset();

    } catch(error) {
        tareaMensaje.textContent = "Error al guardar tarea.";
        console.log("Error creando un tarea.");
    }
});

function cancelarEdicionTarea() {
    formTarea.reset();
    idTareaInput.value = "";
    tareaMensaje.textContent = "Edición cancelada";
}

const registroApiUrl = "http://localhost:8080/api/registros";

const formRegistro = document.getElementById("formRegistro");
const tituloRegistroInput = document.getElementById("tituloRegistro");
const idRegistroInput = document.getElementById("idRegistro");
const idTareaRegistroInput = document.getElementById("idTarea");
const idClienteRegistroInput = document.getElementById("idCliente");
const procesoInput = document.getElementById("proceso");
const publicadoInput = document.getElementById("publicado");
const participantesInput = document.getElementById("participantes");
const registroMensaje = document.getElementById("registroMensaje");

formRegistro.addEventListener("submit", async function (e) {
    e.preventDefault();

    const registro = {
        titulo: tituloRegistroInput.value,
        tarea_id: idTareaRegistroInput.value,
        cliente_id: idClienteRegistroInput.value,
        e_proceso: procesoInput.value,
        participantes_id: participantesInput.value,
        publicado: publicadoInput.value,
    };

    try {
        await fetch(registroApiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(registro)
        });

        registroMensaje.textContent = "Registro agregado correctamente.";
        console.log("Registro creado correctamente");
        formRegistro.reset();

    } catch(error) {
        registroMensaje.textContent = "Error al guardar registro.";
        console.log("Error creando el registro.");
    }
});

function cancelarEdicionRegistro() {
    formRegistro.reset();
    idRegistroInput.value = "";
    registroMensaje.textContent = "Edición cancelada";
}

async function buscarEmpleado(idEmpleado){
    const tablaBusquedaEmpleado = document.querySelector('#tablaBusquedaEmpleado tbody');
    const mensajeBusqueda = document.getElementById("busquedaMensaje");
    
    await fetch(`http://localhost:8080/api/empleados/${idEmpleado.value}`)
        
        .then(respuesta => {
            if(respuesta.status === 404){
                console.log("No se encontro un registro relacionado al id.");
                mensajeBusqueda.textContent = "No hay una empleado con ese identificador.";
                return respuesta.json();
            }

            if(!respuesta.ok){
                throw Error("Ocurrio un error obteniendo la empleado.");
            }
            
        })

        .then(empleado => {
            tablaBusquedaEmpleado.innerHTML = `
                <td>${empleado.legajo}</td>
                <th>${empleado.email}</th>
                <th>${empleado.contrasenia}</th>
                <th>${empleado.nombre}</th>
                <th>${empleado.telefono}</th>
                <th>${empleado.etiquetaDeAcceso}</th>
                <th>${empleado.dni}</th>
            `;
        })

        .catch(error => {
            console.error(error);
            mensajeBusqueda.textContent = "Ocurrio un error obteniendo el empleado.";
        }); 
}

function buscarAdmin(idAdmin){
    const tablaBusquedaAdmin = document.querySelector('#tablaBusquedaAdmin tbody');
    const mensajeBusqueda = document.getElementById("busquedaMensaje");
    
    await fetch(`http://localhost:8080/api/administradores/${idAdmin.value}`)
        
        .then(respuesta => {
            if(respuesta.status === 404){
                console.log("No se encontro un registro relacionado al id.");
                mensajeBusqueda.textContent = "No hay una administrador con ese identificador.";
                return respuesta.json();
            }

            if(!respuesta.ok){
                throw Error("Ocurrio un error obteniendo la administrador.");
            }
            
        })

        .then(admin => {
            tablaBusquedaAdmin.innerHTML = `
                <td>${admin.id_administrador}</td>
                <th>${admin.email}</th>
                <th>${admin.contrasenia}</th>
                <th>${admin.nombre}</th>
                <th>${admin.telefono}</th>
                <th>${admin.etiquetaDeAcceso}</th>
                <th>${admin.dni}</th>
            `;
        })

        .catch(error => {
            console.error(error);
            mensajeBusqueda.textContent = "Ocurrio un error obteniendo el administrador.";
        }); 
}

function buscarCliente(idCliente){
    const tablaBusquedaCliente = document.querySelector('#tablaBusquedaCliente tbody');
    const mensajeBusqueda = document.getElementById("busquedaMensaje");
    
    await fetch(`http://localhost:8080/api/cliente/${idCliente.value}`)
        
        .then(respuesta => {
            if(respuesta.status === 404){
                console.log("No se encontro un registro relacionado al id.");
                mensajeBusqueda.textContent = "No hay un cliente con ese identificador.";
                return respuesta.json();
            }

            if(!respuesta.ok){
                throw Error("Ocurrio un error obteniendo el cliente.");
            }
            
        })

        .then(cliente => {
            tablaBusquedaCliente.innerHTML = `
                <td>${cliente.idCliente}</td>
                <th>${cliente.email}</th>
                <th>${cliente.contrasenia}</th>
                <th>${cliente.nombre}</th>
                <th>${cliente.telefono}</th>
                <th>${cliente.etiquetaDeAcceso}</th>
                <th>${cliente.dni}</th>
            `;
        })

        .catch(error => {
            console.error(error);
            mensajeBusqueda.textContent = "Ocurrio un error obteniendo el cliente.";
        }); 
}

function buscarTarea(idTarea){
    const tablaBusquedaTarea = document.querySelector('#tablaBusquedaTarea tbody');
    const mensajeBusqueda = document.getElementById("busquedaMensaje");
    
    await fetch(`http://localhost:8080/api/tareas/${idTarea.value}`)
        
        .then(respuesta => {
            if(respuesta.status === 404){
                console.log("No se encontro un registro relacionado al id.");
                mensajeBusqueda.textContent = "No hay una tarea con ese identificador.";
                return respuesta.json();
            }

            if(!respuesta.ok){
                throw Error("Ocurrio un error obteniendo la tarea.");
            }
            
        })

        .then(tarea => {
            tablaBusquedaTarea.innerHTML = `
                <td>${tarea.id}</td>
                <th>${tarea.email}</th>
                <th>${tarea.fechaDeRegistro}</th>
                <th>${tarea.fecha_de_entrega}</th>
                <th>${tarea.descripcion_general}</th>
                <th>${tarea.descripcion_material}</th>
            `;
        })

        .catch(error => {
            console.error(error);
            mensajeBusqueda.textContent = "Ocurrio un error obteniendo la tarea.";
        }); 
}

function buscarRegistro(idRegistro){
    const tablaBusquedaRegistro = document.querySelector('#tablaBusquedaRegistro tbody');
    const mensajeBusqueda = document.getElementById("busquedaMensaje");
    
    await fetch(`http://localhost:8080/api/registros/${idRegistro.value}`)
        
        .then(respuesta => {
            if(respuesta.status === 404){
                console.log("No se encontro un registro relacionado al id.");
                mensajeBusqueda.textContent = "No hay un registro con ese identificador.";
                return respuesta.json();
            }

            if(!respuesta.ok){
                throw Error("Ocurrio un error obteniendo el registro.");
            }
            
        })

        .then(registro => {
            tablaBusquedaRegistro.innerHTML = `
                <td>${registro.id}</td>
                <th>${registro.titulo}</th>
                <th>${registro.proceso}</th>
                <th>${registro.tareaId}</th>
                <th>${registro.clienteId}</th>
                <th>${registro.publicado}</th>
                <th>${registro.participantesId}</th>
            `;
        })

        .catch(error => {
            console.error(error);
            mensajeBusqueda.textContent = "Ocurrio un error obteniendo la registro.";
        }); 
}

const formBuscar = document.getElementById("formBusqueda");
const tipoBusquedaInput = document.getElementById("tipoBusqueda");
const idABuscar = document.getElementById("idBuscado");

formBuscar.addEventListener("submit", async function (e) {
    e.preventDefault();

    switch(tipoBusquedaInput.value){

        case 'empleado': 
            buscarEmpleado(idABuscar);
            break;

        case 'admin':
            buscarAdmin(idABuscar);
            break;

        case 'cliente':
            buscarCliente(idABuscar);
            break;

        case 'tarea':
            buscarTarea(idABuscar);
            break;

        case 'registro':
            buscarRegistro(idABuscar);
            break;
    }
})

async function cargarEmpleados() { 
    const tbodyPersonas = document.querySelector('#tablaPersonas tbody');

    await fetch(empleadoApiUrl)
    .then(Response => {
        if(!Response.ok) {
            throw new Error('Error al obtener empleados.');
        }
        return Response.json();
    })
    .then(personas => {
        if(personas.length === 0) {
            tbodyPersonas.innerHTML = `
                <tr>
                    <td>
                        no existen empleados aún.
                    </td>
                </tr>`;
            return;
        }

        personas.forEach(persona => {
            const filaPersonas = document.createElement('tr');
            filaPersonas.innerHTML = `
                    <td>${persona.legajo}</td>
                    <td>${persona.email}</td>
                    <td>${persona.contrasenia}</td>
                    <td>${persona.nombre}</td>
                    <td>${persona.telefono}</td>
                    <td>${persona.etiquetaDeAcceso}</td>
                    <td>${persona.dni}</td>
                `;

        tbodyPersonas.appendChild(filaPersonas)
        });
    })
    .catch(error => {
        console.error(error);
        tbodyPersonas.innerHTML = `
            <tr>
                <td>
                    Error al conectar con el servidor.
                </td>
            </tr>`;
    });

    await fetch(gerenteApiUrl)
    .then(Response => {
        if(!Response.ok) {
            throw new Error('Error al obtener empleados.');
        }
        return Response.json();
    })
    .then(personas => {
        if(personas.length === 0) {
            tbodyPersonas.innerHTML = `
                <tr>
                    <td>
                        no existen empleados aún.
                    </td>
                </tr>`;
            return;
        }

        personas.forEach(persona => {
            const filaPersonas = document.createElement('tr');
            filaPersonas.innerHTML = `
                    <td>${persona.legajo}</td>
                    <td>${persona.email}</td>
                    <td>${persona.contrasenia}</td>
                    <td>${persona.nombre}</td>
                    <td>${persona.telefono}</td>
                    <td>${persona.etiquetaDeAcceso}</td>
                    <td>${persona.dni}</td>
                `;

        tbodyPersonas.appendChild(filaPersonas)
        });
    })
    .catch(error => {
        console.error(error);
        tbodyPersonas.innerHTML = `
            <tr>
                <td>
                    Error al conectar con el servidor.
                </td>
            </tr>`;
    });
    console.log("tabla personas cargada");
}

async function cargarAdmins() { 
    const tbodyAdmins = document.querySelector('#tablaAdmins tbody');

    await fetch(adminApiUrl)
    .then(Response => {
        if(!Response.ok) {
            throw new Error('Error al obtener administradores.');
        }
        return Response.json();
    })
    .then(admins => {
        if(admins.length === 0) {
            tbodyAdmins.innerHTML = `
                <tr>
                    <td>
                        no existen administradores aún.
                    </td>
                </tr>`;
            return;
        }

        admins.forEach(admin => {
            const filaAdmin = document.createElement('tr');
            filaAdmin.innerHTML = `
                    <td>${admin.id_administrador}</td>
                    <td>${admin.email}</td>
                    <td>${admin.contrasenia}</td>
                    <td>${admin.nombre}</td>
                    <td>${admin.telefono}</td>
                    <td>${admin.etiquetaDeAcceso}</td>
                    <td>${admin.dni}</td>
                `;

        tbodyAdmins.appendChild(filaAdmin)
        });
    })
    .catch(error => {
        console.error(error);
        tbodyAdmins.innerHTML = `
            <tr>
                <td>
                    Error al conectar con el servidor.
                </td>
            </tr>`;
    });

    console.log("tabla administradores cargada");
}

async function cargarClientes() { 
    const tbodyClientes = document.querySelector('#tablaClientes tbody');

    await fetch(clienteApiUrl)
    .then(Response => {
        if(!Response.ok) {
            throw new Error('Error al obtener clientes.');
        }
        return Response.json();
    })
    .then(clientes => {
        if(clientes.length === 0) {
            tbodyClientes.innerHTML = `
                <tr>
                    <td>
                        no existen clientes aún.
                    </td>
                </tr>`;
            return;
        }

        clientes.forEach(cliente => {
            const filaClientes = document.createElement('tr');
            filaClientes.innerHTML = `
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.contrasenia}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.etiquetaDeAcceso}</td>
                    <td>${cliente.dni}</td>
                `;

        tbodyClientes.appendChild(filaClientes)
        });
    })
    .catch(error => {
        console.error(error);
        tbodyClientes.innerHTML = `
            <tr>
                <td>
                    Error al conectar con el servidor.
                </td>
            </tr>`;
    });

    console.log("tabla clientes cargada");
}

async function cargarTareas() { 
    const tbodyTarea = document.querySelector('#tablaTareas tbody');

    await fetch(tareaApiUrl)
    .then(Response => {
        if(!Response.ok) {
            throw new Error('Error al obtener tareas.');
        }
        return Response.json();
    })
    .then(tarea => {
        if(tarea.length === 0) {
            tbodyTarea.innerHTML = `
                <tr>
                    <td>
                        no existen tareas aún.
                    </td>
                </tr>`;
            return;
        }

        tarea.forEach(tarea => {
            const filaTarea = document.createElement('tr');

            const options = {
                year: 'numeric',
                month: 'long', 
                day: '2-digit', 
                hour: '2-digit', 
                minute: '2-digit', 
                hour12: false 
            }

            const formatter = Intl.DateTimeFormat(navigator.languages, options);
            const fechaAMostrar = formatter.format(new Date(tarea.fechaDeRegistro));

            filaTarea.innerHTML = `
                    <td>${tarea.id}</td>
                    <td>${tarea.categorias}</td>
                    <td>${fechaAMostrar}</td>
                    <td>${tarea.fecha_de_entrega}</td>
                    <td>${tarea.descripcion_general}</td>
                    <td>${tarea.descripcion_material}</td>
                `;

        tbodyTarea.appendChild(filaTarea)
        });
    })
    .catch(error => {
        console.error(error);
        tbodyTarea.innerHTML = `
            <tr>
                <td>
                    Ocurrio un error inesperado.
                </td>
            </tr>`;
    });

    console.log("tabla tareas cargada");
}

function resetTablas() {
    const tbodyPersonas = document.querySelector('#tablaPersonas tbody');
    while (tbodyPersonas.firstChild) {
        tbodyPersonas.firstChild.remove();
    }

    const tbodyClientes = document.querySelector('#tablaClientes tbody');
    while (tbodyClientes.firstChild) {
        tbodyClientes.firstChild.remove();
    }

    const tbodyAdmins = document.querySelector('#tablaAdmins tbody');
    while (tbodyAdmins.firstChild) {
        tbodyAdmins.firstChild.remove();
    }

    const tbodyTarea = document.querySelector('#tablaTareas tbody');
    while (tbodyTarea.firstChild) {
        tbodyTarea.firstChild.remove();
    }

    console.log("tablas reseteadas");
}

function orderTabla(order = 'asc') {
    const tbodyPersonas = document.querySelector('#tablaPersonas tbody');
    const filas = Array.from(tbodyPersonas.querySelectorAll('tr'));

    filas.sort((a, b) => {
        const idA = parseInt(a.cells[0].textContent);
        const idB = parseInt(b.cells[0].textContent);

        return order === 'asc' ? idA - idB : idB - idA;
    });

    filas.forEach(fila => tbodyPersonas.appendChild(fila));
    console.log("tablas ordenadas");
}

const refreshButton = document.getElementById('refrescarTablas');

async function eventosTablas() {
    resetTablas();
    await cargarEmpleados();
    await cargarClientes();
    await cargarAdmins();
    await cargarTareas();
    orderTabla();
}