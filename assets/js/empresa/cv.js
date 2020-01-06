$(document).ready(function () {
    personales();
});

function personales() {
    let params = new URLSearchParams(location.search);
    var id = params.get('id');
    $.ajax({
        url: "http://localhost:8080/alumnos/alumno/" + id,
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
                $('#email').text(data.id_usuario.email);
                $('#dir').text(data.direccion);
                $('#muni').text(data.id_municipio.nombre);
                $('#dep').text(data.id_municipio.departamento.nombre);

                var age = calculateAge(data.fecha);
                function calculateAge(birthday) {
                    var birthday_arr = birthday.split("-");
                    var birthday_date = new Date(birthday_arr[0], birthday_arr[2] - 1, birthday_arr[1]);
                    var ageDifMs = Date.now() - birthday_date.getTime();
                    var ageDate = new Date(ageDifMs);
                    return Math.abs(ageDate.getUTCFullYear() - 1970);
                }
                $('#edad').text(age);
            }
            educacion(data.id_usuario.id)
            proyectos(data.id_usuario.id)
            Dhabilidades(data.id_usuario.id)
            Documentos(data.id_usuario.id, data.id)
        }
    });
}

function educacion(idUs) {
    $.ajax({
        url: "http://localhost:8080/educacion/usuario/" + idUs,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                if (data.universidad != null) {
                    $('#dts').append('<li>' +
                        '<ul class="address-text">' +
                        ' <li><b>Universidad</b></li>' +
                        ' <li><span id="uni"></span></li>' +
                        ' </ul >' +
                        ' </li >' +
                        '<li>' +
                        '<ul class="address-text">' +
                        '<li><b>Carrera </b></li>' +
                        '<li><span id="carrera"></span></li>' +
                        '</ul>' +
                        '</li>')
                }
                $('#uni').text(data.universidad);
                $('#carrera').text(data.carrera);
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

function proyectos(idUs) {
    $.ajax({
        url: "http://localhost:8080/proyecto/usuario/" + idUs,
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

function Dhabilidades(idUs) {
    $.ajax({
        url: "http://localhost:8080/detalleHa/usuario/" + idUs,
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
                    }
                });
            }
        }
    });
}
function Documentos(idUs, id) {
    $('#img').append('<img src="../../../BolsaCDSS/img/img_' + idUs + '.png" alt="" class="rounded">');
    $('.nav1').append('<li><a href="../../../BolsaCDSS/cv/cv_' + idUs + '.pdf" class="resp-tab-item" style="color: #fff;" target="_blank"><i class="glyphicon glyphicon-download"></i> Curriculum</a></li>');
    $('.nav1').append(' <li><a href="envio.html?correo=' + id + '" class="resp-tab-item" style="color: #fff;"><i class="glyphicon glyphicon-send"></i> Enviar propuesta</a></li>');
}
