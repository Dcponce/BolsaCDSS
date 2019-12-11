$(document).ready(function () {

    var uriH = "http://localhost:8080/habilidades";
    var uriH = "http://localhost:8080/detalleHa";

    getCert(uriC, 0)

    $('#datosH').on('click', function () {
        guardarH(uriE);
    });

});

function guardarH(uriH) {
    var idc = $('#idE').val();
    var pri = $('#universidad').val();
    var lvl = $('#carrera').val();
    var hab = $('#certificacion').val();
    var metodo = "POST";
    var accion = "Guardado";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (cer > 0) {

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
            url: uriH,
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