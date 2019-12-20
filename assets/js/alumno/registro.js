$(document).ready(function () {
    var uri = "http://localhost:8080/usuarios/alumno";
    $("#nuevo").on("click", function () {
        getCred(uri);
    });
})

function nuevo(uri, idC) {
    var email = $("#email").val();
    var clave = $("#clave").val();
    var accion = "Guardado";
    var credencial = $("#credencial").val();

    if (credencial != "") {
        var data = {
            "id": null,
            "email": email,
            "id_credencial": {
                "id": idC
            },
            "clave": clave,
            "id_tipo": {
                "id": 3
            },
            "estado": true,
            "activo": false
        };

        $.ajax({
            url: uri,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            //processData: false,
            //cache: false,
            success: function () {
                mensaje = "Registro " + accion + " exitosamente";
                alert(mensaje);

            }
        });
    } else {
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
}

function getCred(uri) {
    var credencial = $("#credencial").val();
    $.ajax({
        url: "http://localhost:8080/usuarios/usu/" + credencial,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            var idC = res["id"];
            console.log(idC);
            nuevo(uri, idC);
        },
        error: function () {
            alert("Operacion fallida");
        }
    });
}