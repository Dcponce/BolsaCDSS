$(document).ready(function () {

    var base_uri = "http://localhost:8080/empresa";

    getDeparta(base_uri, 0);

    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });

    $('#departamento').on('change', function () {
        var id = $('#departamento').val();

        getMunicipioByDepar(base_uri, id, 0);
    });

});

function nuevo(base_uri) {
    var idc = $('#id').val();
    var nombre = $('#nombre').val();
    var telefono = $('#telefono').val();
    var direccion = $('#direccion').val();
    var muni = $('#municipio').val();
    var metodo = "POST";
    var accion = "Guardado";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if (nombre != null) {

        var data = {
            "id": id,
            "nombre": nombre,
            "telefono": telefono,
            "direccion": direccion,
            "municipio": {
                "id": muni
            },
            "id_usuario": {
                "id": idc
            }
        };

        $.ajax({
            url: base_uri,
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Registro agregar Existosamente !!!");
            }
        });
    }
}

function getDeparta(base_uri, id) {

    $.getJSON(base_uri + "/departamento", function (data) {
        if (data != null) {

            $('#departamento').empty();
            $('#departamento').append("<option selected disabled>Seleccione un departamento</option>");

            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                }
                $('#departamento').append(fila);
            });
        }
    });
}

function getMunicipioByDepar(base_uri, idDepto, id) {
    $.getJSON(base_uri + "/muni/" + idDepto, function (data) {
        if (data != null) {
            $('#municipio').empty();
            $('#municipio').append("<option selected disabled>Seleccione un municipio</option>");
            var fila = "";
            $.each(data, function (i, v) {

                if (v.id == id) {
                    fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                } else {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                }

                $('#municipio').append(fila);
            });
        }
    });
}