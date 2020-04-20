$(document).ready(function () {
    personales();
    educacion();
    proyectos();
    Dhabilidades();
    Documentos();
});

function personales() {
    $.ajax({
        url: "http://localhost:8080/alumnos/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#nom').text(data.nombre);
                $('#ape').text(data.apellido);
                $('#tel').text(data.telefono);
                $('#cel').text(data.celular);
                $('#email').text(JSON.parse(localStorage.getItem('Email')));
                $('#dir').text(data.direccion);
                $('#muni').text(data.id_municipio.nombre);
                $('#dep').text(data.id_municipio.departamento.nombre);

                var fe = new Date(data.fecha);
                var mes = fe.getMonth() + 1;           
                
                var fecha = fe.getFullYear() + "-" + mes + "-" + fe.getDate();

               calculateAge(fecha);
                function calculateAge(birthday) {
                    var hoy = new Date();
                    var cumpleanos = new Date(birthday);
                    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
                    var m = hoy.getMonth() - cumpleanos.getMonth();
                
                    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
                        edad--;
                    }
                
                    $('#edad').text(edad);
                }
                
            }
        }
    });
}

function educacion() {
    $.ajax({
        url: "http://localhost:8080/educacion/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                if (data.universidad != null & data.universidad!="") {
                    $('#dts').append('<li>' +
                        '<ul class="address-text">' +
                        ' <li><b>Universidad</b></li>' +
                        ' <li><span id="uni"></span></li>' +
                        ' </ul >' +
                        ' </li >' +
                        '<li>' +
                        '<ul class="address-text">' +
                        '<li><b>Carrera </b></li>' +
                        '<li><span id="carrera"></span> - <b id="stado"></b></li>' +
                        '</ul>' +
                        '</li>')
                }
                $('#uni').text(data.universidad);
                $('#carrera').text(data.carrera);
                $('#stado').text(data.estado);
                $('#certificacion').text(data.id_certificacion.nombre);
                $('#nivel').text(data.nivel);
                if (data.nivel == 3) {
                    $('#completo').text("Graduado");
                    $('#premio').addClass("ti-star");
                }

            }
        }
    });
}

function proyectos() {
    $.ajax({
        url: "http://localhost:8080/proyecto/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $.each(data, function (i, v) {
                    $('#exp').append('<div class="col-md-4 wthree_about_right_grid">' +
                        '<div class= "col-xs-4 wthree_about_right_grid_left">' +
                        '<div class="hvr-rectangle-in">' +
                        '<i class="glyphicon glyphicon-cog"></i>' +
                        ' </div>' +
                        ' </div >' +
                        '<div class="col-xs-8 wthree_about_right_grid_right">' +
                        ' <h4>' + v.nombre + '</h4>' +
                        '<a href="' + v.url + '" class="navigation" target="_blank">Link</a>' +
                        '</div>' +
                        '<div class="clearfix"> </div>' +
                        '</div> ');
                });
            }
        }
    });
}

function Dhabilidades() {
    $.ajax({
        url: "http://localhost:8080/detalleHa/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $.each(data, function (i, v) {
                    if (v.habilidad.tipo == "O") {
                        $('#Dha').show("slow");
                        $('#ocultar').show("slow");
                    }
                    switch (v.habilidad.tipo) {
                        case "T":
                            $('#tec').append('<p>' + v.habilidad.descripcion + '</p>');
                            break
                        case "P":
                            $('#bla').append('<p>' + v.habilidad.descripcion + '</p>');
                            break
                        case "O":
                            $('#otra').append('<p>' + v.habilidad.descripcion + '</p>');
                            break
                    };
                });
            }
        }
    });
}
function Documentos() {
    $('#img').append('<img src="../../../BolsaCDSS/img/img_' + JSON.parse(localStorage.getItem('Id')) + '.png" alt="" class="rounded"  onerror="imgError(this);">');
}

function imgError(image) {
    image.onerror = "";
    image.src = "../../img/porDefecto/usuario.png"
    return true;
}
