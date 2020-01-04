$(document).ready(function () {

    var uriDt = "http://localhost:8080/detalleHa";

    createOptions();

    $('#datosH').on('click', function () {
        guardarH(uriDt);
    });

});

function guardarH(uriDt) {

    var hab = [$('#select').val()];
    var idUsuario = JSON.parse(localStorage.getItem('Id'));

    if (hab.length > 0) {

        //Eliminar todos los registros de habilidades del usuario en sesión
        $.ajax({
            url: uriDt + "/deleteByUser/" + idUsuario,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: "DELETE",
            contentType: "application/json",
            success: function () {
            }
        });


        $.each(hab, function (i, v) {

            for (var i = 0; i < v.length; i++) {

                var data = {
                    "id": null,
                    "habilidad": {
                        "id": v[i]
                    },
                    "usuarios": {
                        "id": idUsuario
                    }
                };

                $.ajax({
                    url: uriDt,
                    headers: {
                        'Authorization': JSON.parse(localStorage.getItem('Token'))
                    },
                    method: "POST",
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
        });
    }
}


function getHabByUser() {
    var ids = [];

    $.ajax({
        url: "http://localhost:8080/detalleHa/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $.each(data, function (i, v) {
                    ids.push(v.habilidad.id);
                });
            }
        }
    });
    
    return ids;
}


function createOptions() {

    var ids = getHabByUser();
    console.log(ids);
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
                    if (ids.length == 0) {
                        option = '<option value="' + v.id + '">' + v.descripcion + '</option>';
                    } else {
                        option = (ids.includes(v.id)) ? '<option value="' + v.id + '" selected>' + v.descripcion + '</option>' : '<option value="' + v.id + '">' + v.descripcion + '</option>';
                    }
                    options.push(option);
                });

                _options = options.join('');
                $('#select')[0].innerHTML = _options;
            }
        }
    }).done(function () {
        var multipleCancelButton = new Choices('#select', {
            removeItemButton: true,
            maxItemCount: 10,
            searchResultLimit: 5
            //renderChoiceLimit:5
        });
    });
}