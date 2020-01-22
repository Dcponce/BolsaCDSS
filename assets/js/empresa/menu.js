$(document).ready(function () {
    menuDatos();
    menuNav();
    footer();
    imgPerfil()

    $('#mdes').on('click', function () {
        mmovil();
    });

});

function footer() {
    $('.footer').append('<div class="row">' +
        '<div class= "col-sm-6 text-center text-sm-right order-sm-1">' +
        '</div>' +
        '<div class="col-sm-6 text-center text-sm-left mt-3 mt-sm-0">' +
        '<small class="text-muted d-block">Copyright © 2020 <a href="https://www.fundaciongloriakriete.org/"' +
        'target="_blank">FGK</a>. Todos los Derechos Reservados</small>' +
        '</div>' +
        '</div>');

    $('.header-fixed').removeClass('purchase-banner-active')
}

function menuNav() {
    $('.header-fixed').append('<nav class="t-header">' +
        '<div class= "t-header-brand-wrapper">' +
        '<a href="inicio.html">' +
        '<img class="logo" src="../../assets/img/logos jap-04.png" alt="">' +
        ' <img class="logo-mini" src="../../assets/img/Siglas.png" alt="">' +
        '</a>' +
        '</div>' +
        '<div class="t-header-content-wrapper">' +
        '<div class="t-header-content">' +
        '<button class="t-header-toggler t-header-mobile-toggler d-block d-lg-none text-gray" id="mdes">' +
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
        ' <i class="mdi mdi-account link-icon link-icon"></i>' +
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
        '<i class="mdi mdi-account-search link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="../../glosario.html">' +
        '<span class="link-title">Glosario</span>' +
        '<i class="mdi mdi-book-open-variant link-icon"></i>' +
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
function mmovil() {
    if ($('.page-body').hasClass('sidebar-collpased')) {
        $('.page-body').removeClass('sidebar-collpased');
    } else {
        $('.page-body').addClass('sidebar-collpased');
    }

}


