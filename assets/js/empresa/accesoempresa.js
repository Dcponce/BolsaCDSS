$(document).ready(function () {

    var base_uri = "http://localhost:8080/empresa";

    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });

    editar(base_uri)
    getCert();
    getDepto();
    createOptions();
    getCentros();

    $('#telefonoE').mask('9999-9999');

    $('#nombre').change(function () {
        check_nombre();
    });

    $('#telefono').focusout(function () {
        check_telefono();
    });

    $('#telefono').mask('9999-9999');
});

function check_nombre() {
    var pattern = /^[a-zA-ZÀ-ÿ\u00f1\u00d1 ]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1 ]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1 ]+$/;
    var nomb = $('#nombre').val();
    if (nomb != "") {
        if (pattern.test(nomb)) {
            $('#nombre_error').hide();
            $('#nombre').css("border-bottom", "2px solid #89D200");
            return false;
        } else {
            $("#nombre_error").html("Sólo se aceptan letras.");
            $('#nombre_error').show();
            $('#nombre').css("border-bottom", "2px solid #FE0000");
            return true;
        }
    } else {
        $("#nombre_error").html("El campo es requerido.");
        $('#nombre_error').show();
        $('#nombre').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_telefono() {
    var tel = $('#telefono').val();
    if (tel != "") {
        if (tel.length == 9) {
            $('#telefonoE_error').hide();
            $('#telefono').css("border-bottom", "2px solid #89D200");
            return false;
        } else {
            $("#telefonoE_error").html("Debe ingresar un número de contacto correcto.");
            $('#telefonoE_error').show();
            $('#telefono').css("border-bottom", "2px solid #FE0000");
            return true;
        }
    } else {
        $("#telefonoE_error").html("Campo requerido.");
        $('#telefonoE_error').show();
        $('#telefono').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function nuevo(base_uri) {
    var id = $('#id').val();
    var nombre = $('#nombre').val();
    var telefono = $('#telefono').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
    } else {
        id = null;
    }

    if (check_nombre() === false && check_telefono() === false) {

        var data = {
            "id": id,
            "nombre": nombre,
            "telefono": telefono,
            "id_usuario": {
                "id": JSON.parse(localStorage.getItem('Id')),
            }
        };

        $.ajax({
            url: base_uri,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Datos almacenados correctamente'
                });
            }
        });

    } else {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Todos los campos son requeridos"

        });
    }
}

function editar(base_uri) {
    $.ajax({
        url: base_uri + "/empre/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#id').val(data.id);
                $('#nombre').val(data.nombre);
                $('#nom').text(data.nombre);
                $('#telefono').val(data.telefono);
            }
        }
    });
}

function getCert() {
    $.ajax({
        url: "http://localhost:8080/certificacion",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#certi').empty();
                $('#certi').append("<option selected disabled>Seleccione una certificación</option>");
                var fila = "";
                $.each(result, function (i, v) {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    $('#certi').append(fila);
                });
            }
        }
    });
}

function getDepto() {
    $.ajax({
        url: "http://localhost:8080/municipios",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#depa').empty();
                $('#depa').append("<option selected disabled>Seleccione un departamento</option>");

                var fila = "";
                $.each(result, function (i, v) {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    $('#depa').append(fila);
                });
            }
        }
    });
}

function createOptions() {
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
                    option = '<option value="' + v.id + '">' + v.descripcion + '</option>';
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

function getCentros() {
    $.ajax({
        url: "http://localhost:8080/centros",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#proyecto').empty();
                $('#proyecto').append("<option selected disabled>Seleccione centro de formación</option>");

                var fila = "";
                $.each(result, function (i, v) {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    $('#proyecto').append(fila);
                });
            }
        }
    });
}