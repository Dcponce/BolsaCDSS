$(document).ready(function () {

    var uri = "http://localhost:8080/alumnos";
    var uriD = "http://localhost:8080/municipios";
    var uriC = "http://localhost:8080/certificacion";
    var uriH = "http://localhost:8080/habilidades";

    getDepto(uriD, 0);
    getCert(uriC, 0)
    getHabi(uriH, 0)

    $('#datosP').on('click', function () {
        nuevo(base_uri);
    });

    $('#departamento').on('change', function () {
        var id = $('#departamento').val();

        getMunicipioByDepto(uriD, id, 0);
    });

});

function guardar(uri) {
    var id = $('#id').val();
    var nom = $('#nombre').val();
    var apellido = $('#apellido').val();
    var tel = $('#telefono').val();
    var cel = $('#celular').val();
    var dir = $('#direccion').val();
    var pro = $('#proyeto').val();
    var nac = $('#nacimiento').val();
    var muni = $('#municipio').val();
    var metodo = "POST";
    var accion = "Guardado";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (sede != "" && muni > 0) {

        var data = {
            "id": id,
            "nombre": nom,
            "apellido": apellido,
            "telefono": tel,
            "celular": cel,
            "direccion": dir,
            "proyecto": pro,
            "fecha": nac,
            "id_municipio": {
                "id": muni
            },
            "id_usuario": {
                "id": id
            }
        };

        $.ajax({
            url: uri,
            method: metodo,
            contentype: "application/json",
            data: JSON.stringify(data),
            success: function () {
                $('#educacion').on('click', function () {
                    nuevo(uri);
                });
            }
        });
    }
}

function getDepto(uriD, id) {

    $.getJSON(uriD, function (data) {
        if (data != null) {

            $('#departamento').empty();
            $('#departamento').append("<option selected disabled>Seleccione un departamento</option>");

            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                }
                $('#departamento').append(fila);
            });
        }
    });
}

function getMunicipioByDepto(uriD, idDepto, id) {
    $.getJSON(uriD + "/muni/" + idDepto, function (data) {
        if (data != null) {
            $('#municipio').empty();
            $('#municipio').append("<option selected disabled>Seleccione un municipio</option>");
            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                }

                $('#municipio').append(fila);
            });
        }
    });
}

function getCert(uriC, id) {

    $.getJSON(uriC, function (data) {
        if (data != null) {

            $('#certificacion').empty();
            $('#certificacion').append("<option selected disabled>Seleccione su certificación</option>");
            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                }
                $('#certificacion').append(fila);
            });
        }
    });
}

function getHabi(uriH, id) {

    $.getJSON(uriH, function (data) {
        if (data != null) {

            $('#habilidad').empty();
            $('#habilidad').append("<option selected disabled>Seleccione sus habilidades</option>");
            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.descripcion + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.descripcion + '</option>';
                }
                $('#habilidad').append(fila);
            });
        }
    });
}