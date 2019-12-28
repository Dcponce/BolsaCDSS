$(document).ready(function () {
    menuDatos();
    menuNav();
    footer();
    imgPerfil()
});

function footer() {
    $('.footer').append('<div class="row">' +
        '<div class= "col-sm-6 text-center text-sm-right order-sm-1">' +
        '<ul class="text-gray">' +
        '<li><a href="#">Terms of use</a></li>' +
        '<li><a href="#">Privacy Policy</a></li>' +
        ' </ul>' +
        '</div>' +
        '<div class="col-sm-6 text-center text-sm-left mt-3 mt-sm-0">' +
        '<small class="text-muted d-block">Derechos reservados © 2019 <a href="https://www.fundaciongloriakriete.org/"' +
        'target="_blank">FGK</a></small>' +
        '<small class="text-gray mt-2">Handcrafted With <i class="mdi mdi-heart text-danger"></i></small>' +
        '</div>' +
        '</div>')
}

function menuNav() {
    $('.header-fixed').append('<nav class="t-header">' +
        '<div class= "t-header-brand-wrapper">' +
        '<a href="admin.html">' +
        '<img class="logo" src="../../assets/css/perfil/images/logo.svg" alt="">' +
        '<img class="logo-mini" src="../../assets/css/perfil/images/logo_mini.svg" alt="">' +
        '</a>' +
        '</div>' +
        '<div class="t-header-content-wrapper">' +
        '<div class="t-header-content">' +
        '<button class="t-header-toggler t-header-mobile-toggler d-block d-lg-none text-gray">' +
        '<i class="mdi mdi-menu"></i>' +
        '</button>' +
        '</div> ' +
        '</div> ' +
        '</nav>')
}

function menuDatos() {
    $('.sidebar').append('<div class="user-profile">' +
        '<div class= "display-avatar animated-avatar" id="img">' +
        '</div>' +
        '<div class="info-wrapper">' +
        '<p class="user-name"></p>' +
        '<h6 class="display-income"></h6>' +
        '</div>' +
        '</div >' +
        '<ul class="navigation-menu">' +
        '<li class="nav-category-divider">MENÚ</li>' +
        '<li>' +
        '<a href="inicio.html">' +
        '<span class="link-title">Inicio</span>' +
        '<i class="mdi mdi-home link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="datos.html">' +
        '<span class="link-title">Datos empresariales</span>' +
        '<i class="mdi mdi-briefcase link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        ' <a href="editar.html">' +
        '<span class="link-title">Editar Usuario</span>' +
        ' <i class="mdi mdi-clipboard-outline link-icon"></i>' +
        ' </a>' +
        '</li>' +
        '<li>' +
        '<a href="#" data-toggle="modal" data-target="#subir">' +
        '<span class="link-title">Subir imagen de perfil</span>' +
        '<i class="mdi mdi-basket-unfill link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="filtros.html">' +
        '<span class="link-title">Buscar talento</span>' +
        '<i class="mdi mdi-magnify link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="#elements" data-toggle="collapse" aria-expanded="false">' +
        '<span class="link-title"> Cerrar Sesión</span>' +
        '<i class="mdi mdi-import link-icon"></i>' +
        '</a>' +
        '<ul class="collapse navigation-submenu" id="elements">' +
        '<li>' +
        '<button type="button" class="btn btn-danger" onclick="cerrarSesion()">Cerrar</button>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '</ul>')
}


function cerrarSesion() {
    localStorage.removeItem('Email');
    localStorage.removeItem('Bienvenida');
    localStorage.removeItem('Token');
    localStorage.removeItem('Id');
    window.location.replace("../../index.html");
}


function imgPerfil() {


    $('#img').append('<img class="profile-img img-lg rounded-circle" src="../../../BolsaCDSS/img/img_' + JSON.parse(localStorage.getItem('Id')) + '.png' + '"alt = "profile image">');

}



