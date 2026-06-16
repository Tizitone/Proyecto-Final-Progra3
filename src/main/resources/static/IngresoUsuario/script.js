document.getElementById('loginForm').addEventListener('submit', function(evento) {
    evento.preventDefault();

    const usuario = document.getElementById('username').value;
    const contrasenia = document.getElementById('password').value;

    let isValid = true;

    if (!usuario.match(/[a-zA-Z0-9]{5,}/)) {
        document.getElementById('usernameError').textContent = "El nombre de usuario debe contener al menos 5 carácteres alfanumericos."
        isValid = false;
    } else {
        document.getElementById('usernameError').textContent = '';
    }

    if (!contrasenia.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/)) {
        document.getElementById('passwordError').textContent = 'La contraseña debe contener al menos 8 carácteres, una letra mayuscula, una letra minuscula y un numero.'
        isValid = false;
    } else {
        document.getElementById('passwordError').textContent = '';
    }

    if (isValid) {
        simularRequestAServer(usuario, contrasenia);
    }
});

function simularRequestAServer(usuario, contrasenia) {
    const statusElement = document.getElementById('loginStatus');

    setTimeout(() => {
        statusElement.textContent = 'Login Logrado! redirigiendo...'
        statusElement.style.color = 'green';
    }, 1000);
}


let loginAttempts = 0;
const maxAttempts = 5;

const lockoutTime = 15 * 60 * 1000;

function handleLogin() {
    if (loginAttempts >= maxAttempts) {
        const remainingTime = calculateRemainingLockoutTime();
        showError('Realizo demasiados intentos equivocados. Intente de nuevo en ${Math.ceil(remainingTime / 60000)} minutos.');
        return false;
    }
}