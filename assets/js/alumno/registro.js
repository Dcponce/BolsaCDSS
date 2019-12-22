$(document).ready(function () {

    $("#nuevo").on("click", function () {
        getCred();
    });
})

function nuevo(idC) {
    var email = $("#email").val();
    var clave = $("#clave").val();
    var accion = "Guardado";
    var credencial = (idC)

    if (credencial != null) {
        var data = {
            "id": null,
            "email": email,
            "id_credencial": {
                "id": credencial
            },
            "clave": clave,
            "id_tipo": {
                "id": 3
            },
            "estado": true,
            "activo": false
        };

        $.ajax({
            url: "http://localhost:8080/usuarios/ingreso",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            //processData: false,
            //cache: false,
            success: function () {
                mensaje = "Registro " + accion + " exitosamente";
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Usuario registrado',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                }).then((result) => {
                    if (result.value) {
                        activar(data);
                    }
                })


            }
        });
    } else {
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
}

function getCred() {
    var credencial = $("#credencial").val();
    $.ajax({
        url: "http://localhost:8080/usuarios/usu/" + credencial,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            var idC = res["id"];
            nuevo(idC);
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Operación fallida'
            });
        }
    });
}

function activar(data) {
    $.ajax({
        url: "http://localhost:8080/usuarios/activacion",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (res) {
            Swal.fire({
                icon: 'info',
                title: 'Revisa tu correo',
                text: 'Para activación de usuario.'
            });
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Envio fallido'
            });
        }
    });
}