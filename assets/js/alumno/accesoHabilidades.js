$(document).ready(function () {

    var uriDt = "http://localhost:8080/detalleHa";

    createOptions(0);
    $('#select').selectpicker();

    $('#datosH').on('click', function () {
        guardarH(uriDt);
    });

});

function guardarH(uriDt) {
    var id = $('#id').val();
    var pri = $('#prioridad').val();
    var lvl = $('#dominio').val();
    var hab = $('#select').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
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
                "id": JSON.parse(localStorage.getItem('Id'))
            }
        };

        $.ajax({
            url: uriDt,
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

function createOptions(id) {
    $.ajax({
        url: "http://localhost:8080/habilidades",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#select').empty();

                var option = "";
                var options = [], _options;

                $.each(result, function (i, v) {
                    option = "";

                    if (v.id == id) {
                        option = '<option value="' + v.id + '" selected>' + v.descripcion + '</option>';
                    } else {
                        option = '<option value="' + v.id + '">' + v.descripcion + '</option>';
                    }

                    options.push(option);
                });
                _options = options.join('');

                $('#select')[0].innerHTML = _options;
            }
        }

    });
}