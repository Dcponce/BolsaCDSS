$(document).ready(function () {
    menuDatos();
});

function menuDatos() {
    $('#menuA').append('<ul class="nav flex-column">' +
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