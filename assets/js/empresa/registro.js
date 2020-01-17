$(document).ready(function () {

    $("#nuevo").on("click", function () {
        getCred();
    });

    $('#show').mousedown(function(){
        $('#clave').removeAttr('type');
        $('#show').addClass('fa-eye-slash').removeClass('fa-eye');
    
    });
    
    $('#show').mouseup(function(){
        $('#clave').attr('type', 'password');
        $('#show').addClass('fa-eye').removeClass('fa-eye-slash');

    });
})

function nuevo(idC) {
    var email = $("#email").val();
    var clave = $("#clave").val();
    var credencial = (idC)

    if (credencial != null && email!="" && clave != "") {
        var data = {
            "id": null,
            "email": email,
            "id_credencial": {
                "id": credencial
            },
            "clave": clave,
            "id_tipo": {
                "id": 2
            },
            "estado": true,
            "activo": false
        };

        $.ajax({
            url: "http://localhost:8080/usuarios",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            //processData: false,
            //cache: false,
            success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Usuario registrado',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                }).then((result) => {
                    if (result.value) {
                        $('#gif').show();
                        $('.limiter').addClass('cuerpo');
                        activar(data);
                    }
                })


            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La acción no se pudo completar'
        });
    }
}

function getCred() {
    $.ajax({
        url: "http://localhost:8080/usuarios/usu/3MPRESA",
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
            $('#gif').hide();
            $('.limiter').removeClass('cuerpo');
            localStorage.setItem("Locked", JSON.stringify(res["id"]));
            Swal.fire({
                icon: 'info',
                title: 'Revise su correo',
                text: 'Para activación de usuario.',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            }).then((result) => {
                if (result.value) {
                    window.location.replace("../../index.html");
                }
            })
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