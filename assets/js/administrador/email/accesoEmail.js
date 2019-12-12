$(document).ready(function () {

    var uri = "http://localhost:8080/email";

    listar(uri);

});

function listar(uri) {
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result){
            if (result != null) {

                $('#tabla>tbody').empty();
                var fila = "";
                $.each(result, function (i, v) {
                    fila = "<tr>" +
                        "<td>" + v.asunto + "</td>" +
                        "<td>" + v.emisor.nombre + "</td>" +
                        "<td>" + v.receptor.nombre + "</td>" +
                        "<td>" + v.contenido + "</td>" +
                        "<td> <a href='#' onclick='leer(" + v.id + ")'>Seleccionar</a></td>" +
                        "<td> <a href='#' onclick='eliminar(" + v.id + ")'>Eliminar</a></td>" +
                        "</tr>";
    
                    $('#tabla>tbody').append(fila);
                });
            }
        }
    });
}

function leer(id) {
    $.ajax({
        url: "http://localhost:8080/email/correos/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result){
            if (result != null) {
                $('#emi').text(result.emisor.nombre);
                $('#rece').text(result.receptor.nombre);
                $('#asu').text(result.asunto);
                $('#cont').text(result.contenido);
            }
        }
    })
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/email/"+id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            listar("http://localhost:8080/email");
        }
    });
}