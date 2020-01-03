$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/usuarios/autAlumno",
        headers: {
            Authorization: JSON.parse(localStorage.getItem("Token"))
        },
        method: "GET",
        contentType: "json",
        success: function (res) {

        },
        error: function (error) {
            localStorage.removeItem('Email');
            localStorage.removeItem('Bienvenida');
            localStorage.removeItem('Token');
            localStorage.removeItem('Id');
            window.location.replace("../../index.html");
        }
    });
});

