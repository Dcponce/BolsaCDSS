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
        success: function (result) {
            if (result != null) {
                $.each(result, function (i, v) {
                    if (v.estado == "A") {
                        $('#bandeja').append('<div class= "activity-log" style="background-color: #212F3C;">' + '<a href="#" onclick="leer(' + v.id + ')">' +
                            '<p class="log-name">' + v.asunto + '</p>' +
                            '<div class="log-details">' + v.emisor.nombre + '</div>' +
                            '<div class="log-details">' + v.receptor.nombre + " " + v.receptor.apellido + '</div>' +
                            '</a>' +
                            '<a href="#" style="color:#DB504A;" onclick=borrar(' + v.id + ') data-toggle="modal" data-target="#borrar" title="Eliminar"><i class="material-icons">delete_sweep</i></a>' +
                            '</div>')
                    } else {
                        $('#bandeja').append(
                            '<div class= "activity-log">' +
                            '<a href="#" onclick="leer(' + v.id + ')">' +
                            '<p class="log-name">' + v.asunto + '</p>' +
                            '<div class="log-details">' + v.emisor.nombre + '</div>' +
                            '<div class="log-details">' + v.receptor.nombre + " " + v.receptor.apellido + '</div>' +
                            '</a>' +
                            '<a href="#" style="color:#DB504A;" onclick=borrar(' + v.id + ') data-toggle="modal" data-target="#borrar" title="Eliminar"><i class="material-icons">delete_sweep</i></a>' +
                            '</div>');
                    }
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
        success: function (result) {
            if (result != null) {
                var test = result.contenido;
                var pri = test.split(",");
                $('#emi').text(result.emisor.nombre);
                $('#rece').text(result.receptor.nombre + " " + result.receptor.apellido);
                $('#asu').text(result.asunto);
                $('#pue').text(pri[0]);
                $('#descr').text(pri[1]);
                $('#sa').text(pri[2]);
                $('#contac').text(pri[3]);
                $('#email').text(pri[4]);
                $('#direct').text(pri[5]);
                $('#como').text(pri[6]);
                $('#info').text(pri[7]);

                modificar(result.id, result.emisor.id, result.receptor.id, result.asunto, result.contenido);
            }
        }
    })
}

function modificar(id, emi, rec, asu, cont) {
    var id = id;
    var em = emi;
    var re = rec;
    var asu = asu;
    var cont = cont;

    if (id > 0) {

        var data = {
            "id": id,
            "asunto": asu,
            "contenido": cont,
            "estado": "I",
            "receptor": {
                "id": re
            },
            "emisor": {
                "id": em
            }
        };

        $.ajax({
            url: "http://localhost:8080/email",
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                console.log('modifado')
            }
        });
    }
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/email/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            listar("http://localhost:8080/email");
            location.reload();
        }
    });
}

function borrar(id) {
    $('#idDelete').val(id);
}

