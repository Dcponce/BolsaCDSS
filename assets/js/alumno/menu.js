$(document).ready(function () {
    menuDatos();
    menuNav();
    imgPerfil();
    footer();
});

function menuDatos() {
    $('.sidebar').append('<div class="user-profile">' +
        '<div class= "display-avatar animated-avatar" id="img">' +
        '</div>' +
        ' <div class="info-wrapper">' +
        '<p class="user-name"></p>' +
        ' <h6 class="display-income"></h6>' +
        ' </div>' +
        '</div >' +
        '<ul class="navigation-menu">' +
        ' <li class="nav-category-divider">MENÚ</li>' +
        ' <li>' +
        '<a href="inicio.html">' +
        '<span class="link-title">Inicio</span>' +
        '<i class="mdi mdi-home link-icon"></i>' +
        '</a>' +
        ' </li>' +
        '<li>' +
        ' <a href="datos.html">' +
        '<span class="link-title">Datos personales</span>' +
        ' <i class="mdi mdi-clipboard-outline link-icon"></i>' +
        ' </a>' +
        '</li>' +
        ' <li>' +
        ' <a href="#sample-pages" data-toggle="collapse" aria-expanded="false">' +
        ' <span class="link-title">Subir documentos</span>' +
        '<i class="mdi mdi-basket-unfill link-icon"></i>' +
        '</a>' +
        '<ul class="collapse navigation-submenu" id="sample-pages">' +
        '<li>' +
        '<a href="#" target="_blank">Imagen de perfil</a>' +
        ' </li>' +
        ' <li>' +
        '<a href="#" target="_blank">Curriculum</a>' +
        ' </li>' +
        ' </ul>' +
        '</li>' +
        '<li>' +
        ' <a href="curriculum.html" target="_blank">' +
        '  <span class="link-title">Vista de perfil</span>' +
        '  <i class="mdi mdi-chart-donut link-icon"></i>' +
        '  </a>' +
        ' </li>' +
        '</ul>')
}
function menuNav() {
    $('.header-fixed').append('<nav class="t-header">' +
        '<div class= "t-header-brand-wrapper">' +
        '<a href="inicio.html">' +
        '<img class="logo" src="../../assets/css/perfil/images/logo.svg" alt="">' +
        ' <img class="logo-mini" src="../../assets/css/perfil/images/logo_mini.svg" alt="">' +
        ' </a>' +
        ' </div>' +
        '<div class="t-header-content-wrapper">' +
        '<div class="t-header-content">' +
        ' <button class="t-header-toggler t-header-mobile-toggler d-block d-lg-none text-gray">' +
        '<i class="mdi mdi-menu"></i>' +
        '</button>' +
        ' </div>' +
        ' </div>' +
        '</nav>')
}
function footer() {
    $('.footer').append('<div class="row">' +
        '<div class= "col-sm-6 text-center text-sm-right order-sm-1">' +
        '<ul class="text-gray">' +
        '<li><a href="#">Terms of use</a></li>' +
        '<li><a href="#">Privacy Policy</a></li>' +
        ' </ul>' +
        '</div>' +
        '<div class="col-sm-6 text-center text-sm-left mt-3 mt-sm-0">' +
        '<small class="text-muted d-block">Copyright © 2019 <a href="http://www.uxcandy.co"' +
        'target="_blank">UXCANDY</a>. All rights reserved</small>' +
        '<small class="text-gray mt-2">Handcrafted With <i class="mdi mdi-heart text-danger"></i></small>' +
        '</div>' +
        '</div>')
}

function imgPerfil() {
    $.ajax({
        url: "http://localhost:8080/documento/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#img').append('<img class="profile-img img-lg rounded-circle" src="../../../BolsaCDSS/img/' + data.ruta + '"alt = "profile image" >');
            }
        }
    });
}