const accountTypeSelect = document.getElementById('acceso');
const empleadoBlock = document.getElementById('bloqueEmpleado');
const adminBlock = document.getElementById('bloqueAdministrador');
const gerenteBlock = document.getElementById('bloqueGerente');
const clienteBlock = document.getElementById('bloqueCliente');

accountTypeSelect.addEventListener('change', function() {

    switch (this.value) {
        case 'empty':
            empleadoBlock.classList.add('escondido');
            adminBlock.classList.add('escondido');
            gerenteBlock.classList.add('escondido');
            clienteBlock.classList.add('escondido');
            break;

        case 'empleado':
            empleadoBlock.classList.remove('escondido');
            adminBlock.classList.add('escondido');
            gerenteBlock.classList.add('escondido');
            clienteBlock.classList.add('escondido');
            break;

        case 'administrador':
            empleadoBlock.classList.add('escondido');
            adminBlock.classList.remove('escondido');
            gerenteBlock.classList.add('escondido');
            clienteBlock.classList.add('escondido');
            break;

        case 'gerente':
            empleadoBlock.classList.add('escondido');
            adminBlock.classList.add('escondido');
            gerenteBlock.classList.remove('escondido');
            clienteBlock.classList.add('escondido');
            break;

        case 'cliente':
            empleadoBlock.classList.add('escondido');
            adminBlock.classList.add('escondido');
            gerenteBlock.classList.add('escondido');
            clienteBlock.classList.remove('escondido');
            break;
    }
});

const inicioSelect = document.getElementById('opciones');
const crearBlock = document.getElementById('crear');
const buscarBlock = document.getElementById('buscar');
const verBlock = document.getElementById('ver');

inicioSelect.addEventListener('change', function() {

    switch (this.value) {
        case 'crear':
            crearBlock.classList.remove('escondido');
            buscarBlock.classList.add('escondido');
            verBlock.classList.add('escondido');
            break;

        case 'buscar':
            crearBlock.classList.add('escondido');
            buscarBlock.classList.remove('escondido');
            verBlock.classList.add('escondido');
            break;

        case 'ver':
            crearBlock.classList.add('escondido');
            buscarBlock.classList.add('escondido');
            verBlock.classList.remove('escondido');
            break;
    }
});

const blockTypeSelect = document.getElementById('listaBlock');
const personaBlock = document.getElementById('bloquePersona');
const tareaBlock = document.getElementById('bloqueTarea');
const registroBlock = document.getElementById('bloqueRegistro');

blockTypeSelect.addEventListener('change', function() {
    switch (this.value) {
        case 'personas':
            personaBlock.classList.remove('escondido');
            tareaBlock.classList.add('escondido');
            registroBlock.classList.add('escondido');
            break;

        case 'tareas':
            personaBlock.classList.add('escondido');
            tareaBlock.classList.remove('escondido');
            registroBlock.classList.add('escondido');
            break;

        case 'registros':
            personaBlock.classList.add('escondido');
            tareaBlock.classList.add('escondido');
            registroBlock.classList.remove('escondido');
            break;
    }
});

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

    /*
    try {
        if (idEmpleadoInput.value = "") {
            await fetch(EmpleadoapiUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(empleado),
            });

            empleadoMensaje.textContent = "Empleado agregado correctamente.";
        } else {
            await fetch(`${EmpleadoapiUrl}/${idEmpleadoInput.value}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(empleado)
            });

            empleadoMensaje.textContent = "Empleado modificado correctamente."
        }

        formEmpleado.reset();
        idEmpleadoInput.value = "";
        cargarEmpleado();
    }catch(error){
        empleadoMensaje.textContent = "Error al guardar / modificar empleado.";
    }
        */


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
//const tablaGerente = document.getElementById("tablaPersonas");
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
//const tablaAdmin = document.getElementById("tablaPersonas");
const adminMensaje = document.getElementById("adminMensaje");

async function cargarAdmin() {
    try {
        const respuesta = await fetch(adminApiUrl);
        const admins = await respuesta.json();

        tablaPersona.innerHTML = "";

        admins.array.forEach(admin => {
            const filaAdmin = `
            <tr>
                <td>${admin.id}</td>
                <td>${admin.email}</td>
                <td>${admin.contrasenia}</td>
                <td>${admin.nombre}</td>
                <td>${admin.telefono}</td>
                <td>${admin.acceso}</td>
                <td>${admin.dni}</td>
                <td>
                    <div class="acciones">
                        <button onclick='editarAdmin(${JSON.stringify(admin)})'>Editar Admin</button>
                        <button onclick='eliminarAdmin(${admin.id})'>Eliminar Admin</button>
                    </div>
                </td>
            </tr>
            `;
            tablaPersona.innerHTML += filaAdmin;
        });
    } catch(error) {
        adminMensaje.textContent = "Error al cargar administrador.";
    }
}

formAdmin.addEventListener("submit", async function (e) {
    e.preventDefault();

    const admin = {
        email: adminEmailInput.value,
        contrasenia: adminContraInput.value,
        nombre: adminNombreInput.value,
        telefono: adminTelefonoInput.value,
        acceso: "administrador",
        dni: adminDniInput.value 
    };

    try {
        if (idAdminInput.value = "") {
            await fetch(adminApiUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(admin)
            });

            adminMensaje.textContent = "Administrador agregado correctamente.";
        } else {
            await fetch(`${adminApiUrl}/${idAdminInput.value}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(admin)
            });

            adminMensaje.textContent = "Administrador modificado correctamente."
        }

        formAdmin.reset();
        idAdminInput.value = "";
        cargarAdmin();
    }catch(error){
        adminMensaje.textContent = "Error al guardar / modificar administrador.";
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
//const tablaCliente = document.getElementById("tablaPersonas");
const clienteMensaje = document.getElementById("clienteMensaje");

async function cargarCliente() {
    try {
        const respuesta = await fetch(clienteApiUrl);
        const clientes = await respuesta.json();

        tablaPersona.innerHTML = "";

        clientes.array.forEach(cliente => {
            const filaCliente = `
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.email}</td>
                <td>${cliente.contrasenia}</td>
                <td>${cliente.nombre}</td>
                <td>${cliente.telefono}</td>
                <td>${cliente.acceso}</td>
                <td>${cliente.dni}</td>
                <td>
                    <div class="acciones">
                        <button onclick='editarCliente(${JSON.stringify(cliente)})'>Editar Cliente</button>
                        <button onclick='eliminarCliente(${cliente.id})'>Eliminar Cliente</button>
                    </div>
                </td>
            </tr>
            `;
            tablaPersona.innerHTML += filaCliente;
        });
    } catch(error) {
        clienteMensaje.textContent = "Error al cargar cliente";
    }
}

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
        if (idClienteInput.value = "") {
            await fetch(clienteApiUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cliente)
            });

            clienteMensaje.textContent = "Cliente agregado correctamente.";
        } else {
            await fetch(`${clienteApiUrl}/${idClienteInput.value}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cliente)
            });

            clienteMensaje.textContent = "Cliente modificado correctamente."
        }

        formCliente.reset();
        idClienteInput.value = "";
        cargarCliente();
    }catch(error){
        clienteMensaje.textContent = "Error al guardar / modificar cliente.";
    }
});

function cancelarEdicionCliente() {
    formCliente.reset();
    idClienteInput.value = "";
    clienteMensaje.textContent = "Edición cancelada";
}

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
}

function resetTablas() {
    const tbodyPersonas = document.querySelector('#tablaPersonas tbody');
    while (tbodyPersonas.firstChild) {
        tbodyPersonas.firstChild.remove();
    }
}

const refreshButton = document.getElementById('refrescarTablas');

refreshButton.addEventListener("click", resetTablas);
refreshButton.addEventListener("click", cargarEmpleados);
