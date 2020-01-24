$(document).ready(function () {

    var uriE = "http://localhost:8080/educacion";

    getCert(id, 0)

    $('#datosE').on('click', function () {
        guardarE(uriE);
    });

    $('#certificacion').focusout(function () {
        check_certificacion();
    });

    $('#nivel').focusout(function () {
        check_nivel();
    });
});

function check_certificacion() {
    var cer = $('#certificacion option:selected').val();
    if (cer > 0) {
        $('#certificacion_error').hide();
        $('#certificacion').css("border-bottom", "2px solid #89D200");
        return false;
    } else {
        $("#certificacion_error").html("Seleccionar una certificación.");
        $('#certificacion_error').show();
        $('#certificacion').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_nivel() {
    var niv = $('#nivel option:selected').val();
    if (niv != "Seleccione su nivel") {
        $('#nivel_error').hide();
        $('#nivel').css("border-bottom", "2px solid #89D200");
        return false;
    } else {
        $("#nivel_error").html("Seleccionar el nivel de certificación alcanzado.");
        $('#nivel_error').show();
        $('#nivel').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function guardarE(uriE) {
    var id = $('#id').val();
    var uni = $('#universidad').val();
    var carre = $('#carrera').val();
    var estado = $('#estado').val();
    var cer = $('#certificacion').val();
    var lvl = $('#nivel').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (check_certificacion() === false && check_nivel() === false) {

        var data = {
            "id": id,
            "universidad": uni,
            "carrera": carre,
            "estado": estado,
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