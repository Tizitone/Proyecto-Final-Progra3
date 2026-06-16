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