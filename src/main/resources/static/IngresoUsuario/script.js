const formLogin = document.getElementById('loginForm')

formLogin.addEventListener('submit', async function(evento) {
    evento.preventDefault();

    const email = document.getElementById('email').value;
    const contrasenia = document.getElementById('password').value;

    let isValid = true;

    if (!email.match(/[a-zA-Z0-9]{5,}/)) {
        document.getElementById('emailError').textContent = "El email debe contener al menos 5 carácteres alfanumericos."
        isValid = false;
    } else {
        document.getElementById('emailError').textContent = '';
    }

    if (!contrasenia.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/)) {
        document.getElementById('passwordError').textContent = 'La contraseña debe contener al menos 8 carácteres, una letra mayuscula, una letra minuscula y un numero.'
        isValid = false;
    } else {
        document.getElementById('passwordError').textContent = '';
    }

    if (isValid) {

        try{
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, contrasenia})
            });

            if (!response.ok) {
                throw new Error('http error! status: ${response.status}');
            }

            const data = await response.json();
            console.log("login logrado: ", data);
            alert("login logrado! Redirigiendo...");
            
            setTimeout(function() {
                window.location.href = "IdeaProjects\Proyecto-Final-Progra3\src\main\resources\static\CRUD metalurgica\HTML metalurgica.html";
            }, 3000);

        } catch {

        }
        simularRequestAServer(email, contrasenia);
    }
});

function simularRequestAServer(email, contrasenia) {
    const statusElement = document.getElementById('loginStatus');

    setTimeout(() => {
        statusElement.textContent = 'Login Logrado! redirigiendo...'
        statusElement.style.color = 'red';
    }, 5000);
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