$(document).ready(function () {
    menuDatos();
    menuNav();
    footer();
    contar();

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
        '<a href="admin.html">' +
        '<img class="logo" src="../../assets/img/logos jap-04.png" alt="" width="100%" height="100%">' +
        '<img class="logo-mini" src="../../assets/img/Siglas.png" alt="" width="100%" height="100%">' +
        '</a>' +
        '</div>' +
        '<div class="t-header-content-wrapper">' +
        '<div class="t-header-content">' +
        '<button class="t-header-toggler t-header-mobile-toggler d-block d-lg-none text-gray" id="mdes">' +
        '<i class="mdi mdi-menu"></i>' +
        '</button>' +
        '<ul class="nav ml-auto">' +
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
        '<li>' +
        '<a href="glosario.html">Glosario</a>' +
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
                    $('.dropdown-title-text').text('Tienes ' + count + ' correos sin leer')
                    $('#punto').addClass('notification-indicator')
                } else {
                    $('.dropdown-title-text').text('No tienes correos')
                }
                $('#count').text(count);
            }
        }
    });
}
function cerrarSesion() {
    localStorage.removeItem('Email');
    localStorage.removeItem('Bienvenida');
    localStorage.removeItem('Token');
    localStorage.removeItem('Id');
    window.location.replace("../../index.html");
}

function mmovil() {
    if ($('.page-body').hasClass('sidebar-collpased')) {
        $('.page-body').removeClass('sidebar-collpased');
    } else {
        $('.page-body').addClass('sidebar-collpased');
    }

}