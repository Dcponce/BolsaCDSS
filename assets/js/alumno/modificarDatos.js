$(document).ready(function () {

    modificar();
    modificarE();
    modificarPro();
});

function modificar() {
    $.ajax({
        url: "http://localhost:8080/alumnos/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#id').val(data.id);
                $('#nombre').val(data.nombre);
                $('#apellido').val(data.apellido);
                $('#no').text(data.nombre);
                $('#ap').text(data.apellido);
                $('#telefono').val(data.telefono);
                $('#celular').val(data.celular);
                $('#direccion').val(data.direccion);
                $('#proyecto').val(data.proyecto);
                $('#nacimiento').val(data.fecha);
                var uri = "http://localhost:8080/municipios";
                var idDepto = data.id_municipio.departamento.id;
                var idMuni = data.id_municipio.id;

                getDepto(uri, idDepto);
                getMunicipioByDepto(uri, idDepto, idMuni);
            }
        }
    });
}
function modificarE() {
    $.ajax({
        url: "http://localhost:8080/educacion/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#idE').val(data.id);
                $('#universidad').val(data.universidad);
                $('#carrera').val(data.carrera);
                $('#certificacion').val(data.id_certificacion.id);
                $('#nivel').val(data.nivel);
            }
        }
    });
}
function modificarPro() {
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
                    $('#idPr'+i).val(v.id);
                    $('#nom' + i).val(v.nombre);
                    $('#link' + i).val(v.url);
                });
            }
        }
    });
}

