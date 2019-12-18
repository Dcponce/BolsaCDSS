$(document).ready(function () {
    var uriBuscar = "http://localhost:8080/alumnos";


    modificar(uriBuscar);
    modificarE("http://localhost:8080/educacion");
    modificarPro("http://localhost:8080/proyecto");
    modificarDtH("http://localhost:8080/detalleHa");
});

function modificar(uriBuscar) {
    $.ajax({
        url: uriBuscar + "/usuario/" + JSON.parse(localStorage.getItem('Id')),
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
function modificarE(UriE) {
    $.ajax({
        url: UriE + "/usuario/" + JSON.parse(localStorage.getItem('Id')),
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
function modificarPro(UriP) {
    $.ajax({
        url: UriP + "/usuario/" + JSON.parse(localStorage.getItem('Id')),
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
function modificarDtH(UriDtH) {
    $.ajax({
        url: UriDtH + "/usuario/" + JSON.parse(localStorage.getItem('Id')),
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

