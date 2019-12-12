$(document).ready(function () {

    var uriH = "http://localhost:8080/habilidades";
    var uriDt = "http://localhost:8080/detalleHa";

    getHabi(uriH, 0)

    $('#datosH').on('click', function () {
        guardarH(uriDt);
    });

});

function guardarH(uriDt) {
    var idc = $('#idH').val();
    var pri = $('#prioridad').val();
    var lvl = $('#dominio').val();
    var hab = $('#select').val();
    var metodo = "POST";
    var accion = "Guardado";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (hab > 0) {

        var data = {
            "id": id,
            "prioridad": pri,
            "nivel": lvl,
            "habilidad": {
                "id": hab
            },
            "usuarios": {
                "id": idc
            }
        };

        $.ajax({
            url: uriDt,
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                $('#one').removeClass('show active');
                $('#one-tab').removeClass('show active');
                $('#one-tab').prop("disabled", true);

                $('#two').removeClass('show active');
                $('#two-tab').removeClass('show active');
                $('#two-tab').prop("disabled", true);

                $('#three-tab').removeClass('show active');
                $('#three').removeClass('show active');
                $('#three-tab').prop("disabled", true);

                $('#four').addClass('show active');
                $('#four-tab').addClass('show active');
                $('#four-tab').prop("disabled", false);
            }
        });
    }
}

function getHabi(uriH, id) {

    $.getJSON(uriH, function (data) {
        if (data != null) {

            $('#select').empty();
            $('#select').append("<option selected disabled>Seleccione sus habilidades</option>");
            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.descripcion + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.descripcion + '</option>';
                }
                $('#select').append(fila);
            });
        }
    });
}