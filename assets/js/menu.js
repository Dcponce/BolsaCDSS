$(document).ready(function () {
    menuregistros();
});

function menuregistros() {
    $('#body').append('<nav class="navbar navbar-expand-lg navbar-light bg-light">' +
        '<a class="navbar-brand" href="../../index.html">Job Applicant Pool</a>' +
        '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">' +
        '<span class="navbar-toggler-icon"></span>' +
        '</button>' +
        '<div class="collapse navbar-collapse justify-content-center" id="navbarNav">' +
        '<ul class="navbar-nav">' +
        '<li class="nav-item">' +
        '<a class="nav-link" href="../../index.html">Inicio <span class="sr-only">(current)</span></a>' +
        '</li>' +
        '<li class="nav-item">' +
        '<a class="nav-link" href="../alumno/registro.html">Participante</a>' +
        '</li>' +
        '<li class="nav-item">' +
        '<a class="nav-link" href="../empresa/registro.html">Empresa</a>' +
        '</li>' +
        '</ul>' +
        '</div>' +
        '</nav>');
}