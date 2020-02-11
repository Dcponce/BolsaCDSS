$(document).ready(function () {

    var uri = "http://localhost:8080/alumnos";

    getDepto("http://localhost:8080/municipios", 0);

    getCentros("http://localhost:8080/centros", 0);

    $('#datosP').on('click', function () {
        guardar(uri);
    });

    $('#departamento').on('change', function () {
        var id = $('#departamento').val();
        getMunicipioByDepto("http://localhost:8080/municipios", id, 0);
    });

    $('#telefono').mask('9999-9999');
    $('#celular').mask('9999-9999');

    $('#error_nombre').hide();

    $('#nombre').focusout(function () {
        check_nombre();
    });

    $('#apellido').focusout(function () {
        check_apellido();
    });

    $('#telefono').keyup(function () {
        check_telefono();
    });

    $('#celular').focusout(function () {
        check_celular();
    });

    $('#direccion').focusout(function () {
        check_direccion();
    });

    $('#proyecto').focusout(function () {
        check_proyecto();
    });

    $('#nacimiento').focusout(function () {
        check_fecha();
    });
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

function check_apellido() {
    var pattern = /^[a-zA-ZÀ-ÿ\u00f1\u00d1 ]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1 ]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1 ]+$/;
    var ape = $('#apellido').val();
    if (ape != "") {
        if (pattern.test(ape) && ape != '') {
            $('#apellido_error').hide();
            $('#apellido').css("border-bottom", "2px solid #89D200");
            return false;
        } else {
            $("#apellido_error").html("Sólo se aceptan letras.");
            $('#apellido_error').show();
            $('#apellido').css("border-bottom", "2px solid #FE0000");
            return true;
        }
    } else {
        $("#apellido_error").html("El campo es requerido.");
        $('#apellido_error').show();
        $('#apellido').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_celular() {
    var celu = $('#celular').val();

    if (celu != "") {
        if (celu.length == 9) {
            $('#celular_error').hide();
            $('#celular').css("border-bottom", "2px solid #89D200");
            return false;
        } else {
            $("#celular_error").html("Debe ingresar un número de contacto correcto.");
            $('#celular_error').show();
            $('#celular').css("border-bottom", "2px solid #FE0000");
            return true;
        }
    } else {
        $("#celular_error").html("Campo requerido.");
        $('#celular_error').show();
        $('#celular').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}
function check_telefono() {
    var tel = $('#telefono').val();

    if (tel.length == 9) {
        $('#telefono_error').hide();
        $('#telefono').css("border-bottom", "2px solid #89D200");
        return false;
    } else {
        $("#telefono_error").html("Ingrese correctamente el número.");
        $('#telefono_error').show();
        $('#telefono').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_direccion() {
    var dire = $('#direccion').val();
    if (dire != "") {
        $('#direccion_error').hide();
        $('#direccion').css("border-bottom", "2px solid #89D200");
        return false;
    } else {
        $("#direccion_error").html("Debe de ingresar un domicilio.");
        $('#direccion_error').show();
        $('#direccion').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_proyecto() {
    var proye = $('#proyecto option:selected').val();
    if (proye != "") {
        $('#proyecto_error').hide();
        $('#proyecto').css("border-bottom", "2px solid #89D200");
        return false;
    } else {
        $("#proyecto_error").html("Debe de seleccionar el proyecto al que pertenece.");
        $('#proyecto_error').show();
        $('#proyecto').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function check_fecha() {
    var fena = $('#nacimiento').val();

    var actual = new Date();
    var Factual = new Date(actual);
    var convertir = Factual.getFullYear() + '-' + (Factual.getMonth() + '' + 1) + '-' + Factual.getDate();

    if (fena != "") {
        if (fena < convertir) {
            $('#fecha_error').hide();
            $('#nacimiento').css("border-bottom", "2px solid #89D200");
            return false;
        } else {
            $("#fecha_error").html("La fecha ingresada no puede ser mayor a la actual.");
            $('#fecha_error').show();
            $('#nacimiento').css("border-bottom", "2px solid #FE0000");
            return true;
        }
    } else {
        $("#fecha_error").html("Ingrese su fecha de nacimiento.");
        $('#fecha_error').show();
        $('#nacimiento').css("border-bottom", "2px solid #FE0000");
        return true;
    }
}

function guardar(uri) {
    var id = $('#id').val();
    var nom = $('#nombre').val();
    var apellido = $('#apellido').val();
    var tel = $('#telefono').val();
    var cel = $('#celular').val();
    var dir = $('#direccion').val();
    var pro = $('#proyecto').val();
    var nac = $('#nacimiento').val();
    var muni = $('#municipio').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (muni > 0 && check_nombre() === false && check_apellido() === false && check_celular() === false &&
        check_direccion() === false && check_proyecto() === false && check_fecha() === false) {

        var data = {
            "id": id,
            "nombre": nom,
            "apellido": apellido,
            "telefono": tel,
            "celular": cel,
            "direccion": dir,
            "proyecto": {
                "id": pro
            },
            "fecha": nac,
            "id_municipio": {
                "id": muni
            },
            "id_usuario": {
                "id": JSON.parse(localStorage.getItem('Id')),
            }
        };

        $.ajax({
            url: uri,
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

                $('#two').addClass('show active');
                $('#two-tab').addClass('show active');
                $('#two-tab').prop("disabled", false);

                $('#three').removeClass('show active');
                $('#four').removeClass('show active');
            },
            error: function (error) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "No se pudo completar la acción"
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

function getDepto(uriD, id) {
    $.ajax({
        url: uriD,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#departamento').empty();
                $('#departamento').append("<option selected disabled>Seleccione un departamento</option>");

                var fila = "";
                $.each(result, function (i, v) {

                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }
                    $('#departamento').append(fila);
                });
            }
        }
    });
}

function getMunicipioByDepto(uriD, idDepto, id) {
    $.ajax({
        url: uriD + "/muni/" + idDepto,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                $('#municipio').empty();
                $('#municipio').append("<option selected disabled>Seleccione un municipio</option>");
                var fila = "";
                $.each(result, function (i, v) {

                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }

                    $('#municipio').append(fila);
                });
            }
        }
    });
}

function getCentros(uriD, id) {
    $.ajax({
        url: uriD,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#proyecto').empty();
                $('#proyecto').append("<option selected disabled>Seleccione el CDS al que pertenece</option>");

                var fila = "";
                $.each(result, function (i, v) {

                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }
                    $('#proyecto').append(fila);
                });
            }
        }
    });
}