const formLogin = document.getElementById('loginForm')

formLogin.addEventListener('submit', async function(evento) {
    evento.preventDefault();

    const emailInput = document.getElementById('email').value;
    const contraseniaInput = document.getElementById('password').value;

    let isValid = true;

    if (!emailInput.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        document.getElementById('emailError').textContent = "El email debe contener al menos 5 carácteres alfanumericos."
        isValid = false;
    } else {
        document.getElementById('emailError').textContent = '';
    }

    if (!contraseniaInput.match(/^[a-zA-Z\d]{6,}$/)) {
        document.getElementById('passwordError').textContent = 'La contraseña debe contener al menos 6 carácteres, solo letras y numeros estan permitidos.'
        isValid = false;
    } else {
        document.getElementById('passwordError').textContent = '';
    }

    if (isValid) {

        try{
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "x-www-form-urlencoded",
                },
                body: JSON.stringify({ emailInput, contraseniaInput}),
                credentials: "include",
            });

            if (!response.ok) {
                throw new Error('http error! status: ${response.status}');
            }

            const data = await response.json();
            console.log("login logrado: ", data);
            alert("login logrado! Redirigiendo...");
            
            setTimeout(function() {
                window.location.href = "http://localhost:8080/CRUD%20metalurgica/HTML%20metalurgica.html";
            }, 3000);

        } catch {

        }
        simularRequestAServer(emailInput, contraseniaInput);
    }
});

function simularRequestAServer(emailInput, contraseniaInput) {
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