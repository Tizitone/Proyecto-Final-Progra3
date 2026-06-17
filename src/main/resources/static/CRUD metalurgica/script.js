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
            cargarEmpleado();
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

const EmpleadosGeneralApiUrl = "http://localhost:8080/api/empleadosGeneral";
const EmpleadoApiUrl = "http://localhost:8080/api/empleados"

const formEmpleado = document.getElementById("formEmpleado");
const idEmpleadoInput = document.getElementById("idEmpleado");

const empleadoEmailInput = document.getElementById("emailEmpleado");
const empleadoContraInput = document.getElementById("contraseniaEmpleado");
const empleadoNombreInput = document.getElementById("nombreEmpleado");
const empleadoTelefonoInput = document.getElementById("telefonoEmpleado");
const empleadoDniInput = document.getElementById("dniEmpleado");
const tablaPersona = document.getElementById("tablaPersonas");
const empleadoMensaje = document.getElementById("mensaje");

    
async function cargarEmpleado() {

        tablaPersona.innerHTML='';

        try {
        const respuesta = await fetch(EmpleadosGeneralApiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!respuesta.ok) {
            throw new Error('Error al obtener los empleados');
        }

        const solicitudes = await respuesta.json();
        console.log("Datos recibidos del servidor:", solicitudes); 

         if (solicitudes.length === 0) {
            tablaPersona.innerHTML = `
            <tr>
                <td colspan="8" style="text-align: center;">
                    No hay empleados registrados.
                </td>
            </tr>`;
            return;
        }

                solicitudes.forEach(empleado => {
                    const filaEmpleado = `
            <tr>
                <td>${empleado.legajo}</td>
                <td>${empleado.email}</td>
                <td>${empleado.nombre}</td>
                <td>${empleado.telefono}</td>
                <td>${empleado.dni}</td>
                <td>
                    <div class="acciones">
                        <button onclick='editarEmpleado(${JSON.stringify(empleado)})'>Editar Empleado</button>
                        <button onclick='eliminarEmpleado(${empleado.idEmpleadoInput})'>Eliminar Emplado</button>
                    </div>
                </td>
            </tr>
            `;
                    tablaPersona.innerHTML += filaEmpleado;
                });
            }
                    catch(error)
                    {
                        console.error(error);
                        tablaPersona.innerHTML = `
                <tr>
                    <td>
                        Error al conectar con el servidor.
                    </td>
                </tr>`;
                    };

}

formEmpleado.addEventListener("submit", async function (e) {
    e.preventDefault();

    const empleado = {
        email: empleadoEmailInput.value,
        contrasenia: empleadoContraInput.value,
        nombre: empleadoNombreInput.value,
        telefono: empleadoTelefonoInput.value,
        acceso: "EMPLEADO",
        dni: empleadoDniInput.value 
    };

    try {
        if (idEmpleadoInput.value == "") {
            await fetch(new URL(EmpleadoApiUrl).href, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(empleado)
            });

            empleadoMensaje.textContent = "Empleado agregado correctamente.";
        } else {
            await fetch(`${EmpleadoApiUrl}/${idEmpleadoInput.value}`, {
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
//const tablaGerente = document.getElementById("tablaPersonas");
const gerenteMensaje = document.getElementById("gerenteMensaje");


formGerente.addEventListener("submit", async function (e) {
    e.preventDefault();

    const gerente = {
        email: gerenteEmailInput.value,
        contrasenia: gerenteContraInput.value,
        nombre: gerenteNombreInput.value,
        telefono: gerenteTelefonoInput.value,
        acceso: "GERENTE",
        dni: gerenteDniInput.value 
    };

    try {
        if (idGerenteInput.value == "") {
            await fetch(new URL(gerenteApiUrl).href, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(gerente)
            });

            gerenteMensaje.textContent = "Gerente agregado correctamente.";
        } else {
            await fetch(`${gerenteApiUrl}/${idGerenteInput.value}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(gerente)
            });

            gerenteMensaje.textContent = "Gerente modificado correctamente."
        }

        formGerente.reset();
        idGerenteInput.value = "";
        cargarEmpleado();
    }catch(error){
        gerenteMensaje.textContent = "Error al guardar / modificar gerente.";
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
                <td>${admin.nombre}</td>
                <td>${admin.telefono}</td>
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
                <td>${cliente.nombre}</td>
                <td>${cliente.telefono}</td>
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