$(document).ready(function () {

    var uriDt = "http://localhost:8080/detalleHa";

    createOptions(id,0);

    $('#datosH').on('click', function () {
        guardarH(uriDt);
    });

});

function guardarH(uriDt) {
    var id = $('#idH').val();
    var pri = $('#prioridad').val();
    var lvl = $('#dominio').val();
    var hab = [$('#select').val()];

    var metodo = "POST";
    if (id > 0) {
        metodo = "PUT";
    } else {
        id = null;
    }

    $.each(hab, function (i, v) {
        for (var i = 0; i < v.length; i++) {
            var data = {
                "id": id,
                "prioridad": pri,
                "nivel": lvl,
                "habilidad": {
                    "id": v[i]
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
    });

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
                    option = (v.id == id) ? '<option value="' + v.id + '" selected>' + v.descripcion + '</option>' : '<option value="' + v.id + '">' + v.descripcion + '</option>';
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