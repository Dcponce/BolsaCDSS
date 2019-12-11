$(document).ready(function () {

    var uri = "http://localhost:8080/email";

    listar(uri);
    leer();

});

function listar(uri) {
    $.getJSON(uri, function (data) {
        if (data != null) {

            $('#tabla>tbody').empty();
            var fila = "";
            $.each(data, function (i, v) {
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
    });
}

function leer(id) {
    $.getJSON("http://localhost:8080/email/correos/" + id, function (data) {
        if (data != null) {
            $('#emi').text(data.emisor.nombre);
            $('#rece').text(data.receptor.nombre);
            $('#asu').text(data.asunto);
            $('#cont').text(data.contenido);
        }
    });

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/email/"+id,
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            listar("http://localhost:8080/email");
        }
    });
}