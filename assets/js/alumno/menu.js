$(document).ready(function () {
    menuDatos();
    menuNav();
});

function menuDatos() {
    $('#menuA').append('<ul class="list-unstyled">' +
        '<li class="nav-item">' +
        '<a class="nav-link" data-toggle="modal" data-target="#img">Imagen de perfil</a>' +
        '</li>' +
        ' <li class="nav-item">' +
        ' <a class="nav-link" href="datos.html">Datos Personales</a>' +
        ' </li>' +
        '<li class="nav-item">' +
        '<a class="nav-link">Cargar Curriculum</a>' +
        ' </li>' +
        ' <li class="nav-item">' +
        '<a class="nav-link" href="curriculum.html">Vista de Perfil</a>' +
        '</li>' +
        '</ul>')
}
function menuNav() {
    $('#ftco-navbar').append('<div class="container">' +
        '<a class="navbar-brand" href = "index.html"> Job Applicant Pool</a >' +
        '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"' +
        ' aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">' +
        '</div >')
}