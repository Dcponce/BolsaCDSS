$(document).ready(function () {
    menuDatos();
    menuNav();
    footer();
    contar();
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

        '<ul class="nav ml-auto">' +
        '<li class="nav-item dropdown">' +
        '<a class="nav-link" href="#" id="notificationDropdown" data-toggle="dropdown"' +
        'aria-expanded="false">' +
        '<i class="mdi mdi-bell-outline mdi-1x"></i>' +
        '</a>' +
        '<div class="dropdown-menu navbar-dropdown dropdown-menu-right"' +
        'aria-labelledby="notificationDropdown">' +
        '<div class="dropdown-header">' +
        '<h6 class="dropdown-title">Denuncias</h6>' +
        '<p class="dropdown-title-text">You have 4 unread notification</p>' +
        '</div>' +
        '<div class="dropdown-body">' +
        ' <div class="dropdown-list">' +
        '<div class="icon-wrapper rounded-circle bg-inverse-primary text-primary">' +
        '<i class="mdi mdi-alert"></i>' +
        '</div>' +
        '<div class="content-wrapper">' +
        '<small class="name">Storage Full</small>' +
        '<small class="content-text">Server storage almost full</small>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="dropdown-footer">' +
        '<a href="#">View All</a>' +
        '</div>' +
        '</div>' +
        '</li>' +
        '<li class="nav-item dropdown">' +
        '<a class="nav-link" href="#" id="messageDropdown" data-toggle="dropdown" aria-expanded="false">' +
        ' <i class="mdi mdi-message-outline mdi-1x"></i>' +
        '<span class="notification-indicator-primary notification-indicator-ripple" id="punto"></span>' +
        ' </a>' +
        '<div class="dropdown-menu navbar-dropdown dropdown-menu-right" aria-labelledby="messageDropdown">' +
        ' <div class="dropdown-header">' +
        '<h6 class="dropdown-title">Correos</h6>' +
        ' </div>' +
        '<div class="dropdown-body">' +
        '<div class="dropdown-list">' +
        '<div class="image-wrapper">' +
        ' </div>' +
        '<div class="content-wrapper">' +
        ' <p class="dropdown-title-text"></p>' +
        ' </div>' +
        ' </div>' +
        ' </div>' +
        '<div class="dropdown-footer">' +
        '<a href="email.html">Ver todos</a>' +
        '</div>' +
        '</div>' +
        '</li>' +
        '</ul> ' +
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
        '<a href="admin.html">' +
        '<span class="link-title">Inicio</span>' +
        '<i class="mdi mdi-home link-icon"></i>' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="#sample-pages" data-toggle="collapse" aria-expanded="false">' +
        '<span class="link-title">Agregar</span>' +
        '<i class="mdi mdi-database-plus link-icon"></i>' +
        '</a>' +
        '<ul class="collapse navigation-submenu" id="sample-pages">' +
        '<li>' +
        '<a href="certificacion.html">Certificaciones</a>' +
        '</li>' +
        '<li>' +
        '<a href="credencial.html">Credencial</a>' +
        '</li>' +
        '<li>' +
        '<a href="habilidades.html">Habilidades</a>' +
        '</li>' +
        '<li>' +
        '<a href="usuario.html">Usuario</a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li>' +
        '<a href="#ui-elements" data-toggle="collapse" aria-expanded="false">' +
        '<span class="link-title">Ver</span>' +
        '<i class="mdi mdi-eye link-icon"></i>' +
        '</a>' +
        '<ul class="collapse navigation-submenu" id="ui-elements">' +
        '<li>' +
        '<a href="alumno.html">Alumnos</a>' +
        '</li>' +
        '<li>' +
        '<a href="empresa.html">Empresas</a>' +
        '</li>' +
        '<li>' +
        '<a href="email.html">Email</a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li>' +
        '<a href="#elements" data-toggle="collapse" aria-expanded="false">' +
        '<span class="link-title"> Cerrar Sesión</span>' +
        '<i class="mdi mdi-import"></i>' +
        '</a>' +
        '<ul class="collapse navigation-submenu" id="elements">' +
        '<li>' +
        '<button type="button" class="btn btn-danger" onclick="cerrarSesion()">Cerrar</button>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '</ul>')
}

function contar() {
    $.ajax({
        url: "http://localhost:8080/email",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                var count = 0;
                $.each(result, function (i, v) {
                    if (v.estado == "A") {
                        count++
                    }
                });
                if (count > 0) {
                    $('.dropdown-title-text').text('Tienes '+count+' correos sin leer')
                    $('#punto').addClass('notification-indicator')
                } else {
                    $('.dropdown-title-text').text('No tienes correos')
                }
                $('#count').text(count);
            }
        }
    });
}
function cerrarSesion(){
    localStorage.removeItem('Email');
    localStorage.removeItem('Bienvenida');
    localStorage.removeItem('Token');
    localStorage.removeItem('Id');
    window.location.replace("../../index.html");
}
