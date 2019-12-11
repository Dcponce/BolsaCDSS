$(document).ready(function () {
    $('#two-tab').prop("disabled", true);
    $('#three-tab').prop("disabled", true);
    $('#four-tab').prop("disabled", true);

    var uri = "http://localhost:8080/alumnos";
    var uriD = "http://localhost:8080/municipios";
    var uriH = "http://localhost:8080/habilidades";

    getDepto(uriD, 0);
    getHabi(uriH, 0)

    $('#datosP').on('click', function () {
        guardar(uri);
    });

    $('#departamento').on('change', function () {
        var id = $('#departamento').val();

        getMunicipioByDepto(uriD, id, 0);
    });

});

function guardar(uri) {
    var idc = $('#id').val();
    var nom = $('#nombre').val();
    var apellido = $('#apellido').val();
    var tel = $('#telefono').val();
    var cel = $('#celular').val();
    var dir = $('#direccion').val();
    var pro = $('#proyecto').val();
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

    if (muni > 0) {

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
                "id": idc
            }
        };

        $.ajax({
            url: uri,
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                $('#one').removeClass('show active');
                $('#one-tab').removeClass('show active');
                $('#one-tab').prop("disabled", true);

                $('#two').addClass('show active');
                $('#two-tab').addClass('show active');
                $('#two-tab').prop("disabled", false);

                $('#three').removeClass('show active');
                $('#four').removeClass('show active');
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