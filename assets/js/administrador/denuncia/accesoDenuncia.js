$(document).ready(function () {

    var uri = "http://localhost:8080/denuncia";

    listar(uri);
    leer();

});

function listar(uri) {
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                $('#tabla>tbody').empty();
                var fila = "";
                $.each(result, function (i, v) {
                    fila = "<tr>" +
                        "<td>" + v.emisor.id + "</td>" +
                        "<td>" + v.reportado.id + "</td>" +
                        "<td>" + v.descripcion + "</td>" +
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
        url: "http://localhost:8080/denuncia/denuncias/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result){
            if (result != null) {
                $('#emi').text(result.emisor.id);
                $('#repo').text(result.reportado.id);
                $('#des').text(result.descripcion);
            }
        }
    });
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/denuncia/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            listar("http://localhost:8080/denuncia");
        }
    });
}