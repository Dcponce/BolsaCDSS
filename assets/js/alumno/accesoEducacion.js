$(document).ready(function () {

    var uriE = "http://localhost:8080/educacion";

    getCert(id, 0)

    $('#datosE').on('click', function () {
        guardarE(uriE);
    });

});

function guardarE(uriE) {
    var id = $('#id').val();
    var uni = $('#universidad').val();
    var carre = $('#carrera').val();
    var cer = $('#certificacion').val();
    var lvl = $('#nivel').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (cer > 0) {

        var data = {
            "id": id,
            "universidad": uni,
            "carrera": carre,
            "id_certificacion": {
                "id": cer
            },
            "nivel": lvl,
            "id_usuario": {
                "id": JSON.parse(localStorage.getItem('Id'))
            }
        };

        $.ajax({
            url: uriE,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
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

                $('#three-tab').addClass('show active');
                $('#three').addClass('show active');
                $('#three-tab').prop("disabled", false);

                $('#four').removeClass('show active');
            }
        });
    }
}

function getCert(id) {
    $.ajax({
        url: "http://localhost:8080/certificacion",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#certificacion').empty();
                $('#certificacion').append("<option selected disabled>Seleccione su certificación</option>");
                var fila = "";
                $.each(result, function (i, v) {

                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }
                    $('#certificacion').append(fila);
                });
            }
        }
    });
}