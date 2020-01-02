$(document).ready(function () {

    var uri = "http://localhost:8080/alumnos";


    getDepto("http://localhost:8080/municipios", 0);

    $('#datosP').on('click', function () {
        guardar(uri);
    });

    $('#departamento').on('change', function () {
        var id = $('#departamento').val();

        getMunicipioByDepto("http://localhost:8080/municipios", id, 0);
    });

});

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

    if (muni > 0 && nom != "" && apellido != "" && cel != "" && dir != "" && nac != "") {

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
                alert("No se pudo completar la acción");
            }
        });

    }else{
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Operación fallida"
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
