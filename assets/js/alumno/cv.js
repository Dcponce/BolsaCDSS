$(document).ready(function () {
    personales();
    educacion();
    proyectos();
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
                        '<a href="' + v.url +'" class="navigation" target="_blank">Link</a>' +
                        '</div>' +
                        '<div class="clearfix"> </div>' +
                        '</div> ');
                });
            }
        }
    });
}

