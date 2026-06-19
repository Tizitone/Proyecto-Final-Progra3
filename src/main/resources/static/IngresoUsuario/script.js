const formLogin = document.getElementById('loginForm');
const emailError = document.getElementById('emailError');
const passwordError = document.getElementById('passwordError');
const loginStatus = document.getElementById('loginStatus');
const btnLogin = document.getElementById('btnLogin');

formLogin.addEventListener('submit', async function(evento) {
    evento.preventDefault();

    const email = document.getElementById('email').value.trim();
    const contrasenia = document.getElementById('password').value.trim();

    // Reset errors
    emailError.textContent = '';
    passwordError.textContent = '';
    loginStatus.textContent = '';

    let isValid = true;

    if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        emailError.textContent = "Ingrese un email válido.";
        isValid = false;
    }

    if (!contrasenia.match(/^[a-zA-Z\d]{6,}$/)) {
        passwordError.textContent = 'La contraseña debe tener al menos 6 caracteres (solo letras y números).';
        isValid = false;
    }

    if (!isValid) return;

    btnLogin.disabled = true;
    btnLogin.textContent = "Iniciando sesión...";

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email: email,
                contrasenia: contrasenia
            })
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('token', data.token);
            localStorage.setItem('rol', data.rol);

            loginStatus.textContent = "¡Login exitoso! Redirigiendo...";
            loginStatus.style.color = "green";

            setTimeout(() => {
                window.location.href = "/CRUD%20metalurgica/HTML%20metalurgica.html";
            }, 1500);

        } else {
            loginStatus.textContent = "Email o contraseña incorrectos";
            loginStatus.style.color = "red";
        }
    } catch (error) {
        loginStatus.textContent = "No se pudo conectar con el servidor.";
        loginStatus.style.color = "red";
    } finally {
        btnLogin.disabled = false;
        btnLogin.textContent = "Iniciar Sesión";
    }
});