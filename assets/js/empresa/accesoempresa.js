$(document).ready(function () {

    var base_uri = "http://localhost:8080/empresa";

    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });

    editar(base_uri)

});

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

    if (nombre != null) {

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
    }
}

function editar(base_uri) {
    $.ajax({
        url: base_uri+ "/empre/" + JSON.parse(localStorage.getItem('Id')),
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
