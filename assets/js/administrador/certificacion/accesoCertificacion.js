$(document).ready(function () {
    var uri = "http://localhost:8080/certificacion";
    getData(uri);
    $('#nuevo').on('click', function () {
        nuevo(uri);
    });
    $('#delete').on('click', function () {
        var id = $('#idDelete').val();
        eliminar(id);
    });
})

function getData(uri) {
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                $("#tabla>tbody").empty();
                var fila = "";

                $.each(result, function (i, v) {
                    fila =
                        "<tr>" +
                        "<td>" + v.nombre + "</td>" +
                        "<td>" +
                        "<a href='#' style='color:  #2980b9 ' onclick='editar(" + v.id + ")' data-toggle='modal' data-target='#nuevoU'><i class='material-icons'>edit</i></a>" +
                        "</td>" +
                        "<td>" +
                    "<a href='#' style='color:  #c0392b' onclick='borrar(" + v.id + ")' data-toggle='modal' data-target='#borrar'><i class='material-icons'>delete_forever</i></a>" +
                        "</td>" +
                        "</tr>";
                    $('#tabla>tbody').append(fila);
                });
            }
        }
    });
}
function nuevo(uri) {
    var id = $('#id').val();
    var nombre = $('#nombre').val();
    var metodo = "POST";
    var accion = "Guardado";
    if (id > 0) {
        metodo = "PUT"
        accion = "Actualizado"

    } else {
        id = null;
    }

    if (nombre != null) {

        var data = {
            "id": id,
            "nombre": nombre
        }

        $.ajax({
            url: uri,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                $('#oko').text('Excelente')
                $('#msg').text('datos almacenados correctamente.')
                $('.alert').addClass('alert-success');
                $('.alert').removeClass('alert-warning');
                getData(uri);
                clear();
            },
            error: function (error) {
                console.log(error);
            }
        })

    } else {
        $('#oko').text('Lo sentimos')
        $('#msg').text('no se logro completar la acción.')
        $('.alert').addClass('alert-warning');
        $('.alert').removeClass('alert-success');
    }

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/certificacion/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token')),
        },
        method: "DELETE",
        contentType: "application/json",
        success: function (res) {
            $('#oko').text('Excelente')
            $('#msg').text('datos eliminados correctamente.')
            $('.alert').addClass('alert-success');
            $('.alert').removeClass('alert-warning');
            getData("http://localhost:8080/certificacion");
        }
    }).fail(function (error) {
        $('#oko').text('Lo sentimos')
        $('#msg').text('no se logro completar la acción.')
        $('.alert').addClass('alert-warning');
        $('.alert').removeClass('alert-success');
    });
}

function editar(id) {
    $('#exampleModalLabel').text("Modificar Certificación")
    $.ajax({
        url: "http://localhost:8080/certificacion/byId/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                $('#id').val(result.id);
                $('#nombre').val(result.nombre);
            }
        }
    });
}

function clear() {
    $('#id').val("");
    $('#nombre').val("");

}

function titulo() {
    $('#exampleModalLabel').text("Nueva certificación")
}
function borrar(id) {
    $('#idDelete').val(id);
}