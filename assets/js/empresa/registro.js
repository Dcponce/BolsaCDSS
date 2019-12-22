$(document).ready(function () {
    var uri = "http://localhost:8080/usuarios/ingreso";
    $("#nuevo").on("click", function () {
        getCred(uri);
    });
})

function nuevo(uri, idC) {
    var email = $("#email").val();
    var clave = $("#clave").val();
    var accion = "Guardado";
    var credencial = $("#credencial").val();

    if (clave != "") {
        var data = {
            "id": null,
            "email": email,
            "id_credencial": {
                "id": idC
            },
            "clave": clave,
            "id_tipo": {
                "id": 2
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
                activar(data);

            }
        });
    } else {
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
}

function getCred(uri) {
    $.ajax({
        url: "http://localhost:8080/usuarios/usu/" + "3MPRESA",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            var idC = res["id"];
            nuevo(uri, idC);
        },
        error: function () {
            alert("Operacion fallida");
        }
    });
}

function activar(data){
    $.ajax({
        url: "http://localhost:8080/usuarios/activacion",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (res) {
            alert("Enviado")
            window.location.replace("../../confirmacion.html");
        },
        error: function () {
            alert("Envio fallido");
        }
    });
}