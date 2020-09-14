function ansValidation(ev) {
    ev.preventDefault
    const password = document.getElementById("password").value
    const confirmedPassword = document.getElementById("passwordConfirm").value
    if (password !== confirmedPassword) {
        window.alert("Passwords do not match!")
    }
}
