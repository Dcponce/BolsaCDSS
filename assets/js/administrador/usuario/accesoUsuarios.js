$(document).ready(function () {
    var uri = "http://localhost:8080/usuarios";
    getData(uri + "/lista");
    getRoles(uri + "/api/listaTipo");
    $('#nuevo').on('click', function () {
        nuevo(uri);
    });

});

function getData(uri) {
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result) {
                $("#tabla>tbody").empty();
                var fila = "";

                $.each(result, function (i, v) {
                    fila =
                        "<tr>" +
                        "<td>" + v.email + "</td>" +
                        "<td>" + v.id_credencial.codigo + "</td>" +
                        "<td>" + v.id_tipo.descripcion + "</td>" +
                        "<td>" + v.estado + "</td>" +
                        "<td>" + v.activo + "</td>" +
                        "<td>" +
                        "<button type='button' onclick='eliminar(" + v.id + ")'>Eliminar</button>" +
                        "</td>" +
                        "<td>" +
                        "<button type='button' onclick='eliminar(" + v.id + ")'>Editar</button>" +
                        "</td>" +
                        "</tr>";

                    $('#tabla>tbody').append(fila);
                });
            }
        },
        error: function (error) {
            // ¡¡Esta ruta da error si no la cambias la tu ruta local!!
            location.href = "file:///C:/Users/nehe.sandovalfgkss/Documents/BolsaCDSvistas/BolsaCDSS/index.html";
        }
    });
}

function getRoles(uri) {
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#tipo').empty();
                var fila = "";
                $.each(result, function (i, v) {
                    fila = ' <option value=" ' + v.id + ' "> ' + v.descripcion + '</option>';

                    $('#tipo').append(fila);

                });

            }
        },
        error: function (error) {
            // ¡¡Esta ruta da error si no la cambias la tu ruta local!!
            location.href = "file:///C:/Users/nehe.sandovalfgkss/Documents/BolsaCDSvistas/BolsaCDSS/index.html";
        }
    });
}

function nuevo(uri) {
    var id = $('#id').val();
    var email = $('#email').val();
    var credencial = $('#credencial').val();
    var clave = $('#clave').val();
    var tipo = $('#tipo').val();
    var metodo = "POST";
    var accion = "Guardado"

    if (id > 0) {
        metodo = "PUT"
        accion = "Actualizado"

    } else {
        id = null;
    }

    if (credencial != null) {
        var midata = new FormData();

        midata.append("id", id);
        midata.append("email", email);
        midata.append("clave", clave);
        midata.append("credencial", credencial);
        midata.append("tipo", tipo);

        // var data = {
        //     "id": id,
        //     "email": email,
        //     "clave": clave,
        //     "credencial": credencial,
        //     "tipo": tipo
        // }

        $.ajax({
            url: uri,
            method: metodo,
            contentType: "application/json",
            data: midata,
            processData: false,
            cache: false,
            success: function () {
                mensaje = "Registro " + accion + " exitosamente";
                alert(mensaje);
                getData(uri);
            }

        })

    } else {
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
}
