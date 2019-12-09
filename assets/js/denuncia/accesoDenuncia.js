$(document).ready(function () {

    var uri = "http://localhost:8080/denuncia";

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
                    "<td>" + v.emisor.id+ "</td>" +
                    "<td>" + v.reportado.id + "</td>" +
                    "<td>" + v.descripcion + "</td>" +
                    "<td> <a href='#' onclick='leer(" + v.id + ")'>Seleccionar</a></td>" +
                    "<td> <a href='#' onclick='eliminar(" + v.id + ")'>Eliminar</a></td>" +
                    "</tr>";

                $('#tabla>tbody').append(fila);
            });
        }
    });
}

function leer(id) {
    $.getJSON("http://localhost:8080/denuncia/denuncias/" + id, function (data) {
        if (data != null) {
            $('#emi').text(data.emisor.id);
            $('#repo').text(data.reportado.id);
            $('#des').text(data.descripcion);
        }
    });

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/denuncia/" + id,
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            listar("http://localhost:8080/denuncia");
        }
    });
}