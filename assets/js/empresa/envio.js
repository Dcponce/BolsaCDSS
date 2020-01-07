$(document).ready(function () {
    personales();

    $("#nuevo").on("click", function () {
        envio();
    });
});

function personales() {
    let params = new URLSearchParams(location.search);
    var id = params.get('correo');
    $.ajax({
        url: "http://localhost:8080/alumnos/alumno/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#para').val(data.nombre + " " + data.apellido);
                $('#id').val(data.id);
            }
        }
    });
}

function envio() {
    var email = $("#id").val();
    var asunto = $("#asunto").val();
    var contenido = $("#msg").val();
    var contacto = $("#contac").val();
    var puesto = $("#puesto").val();
    var salario = $("#sal").val();
    var direccion = $("#direc").val();
    var link = $("#link").val();
    var info = $('#info').val();

    if (email != null && asunto != "" && contenido != "") {
        var data = {
            "asunto": asunto,
            "contenido": contenido,
            "estado": "A",
            "receptor": {
                "id": email
            },
            "emisor": {
                "id": JSON.parse(localStorage.getItem('Id'))
            }
        };

        $('#gif').show();
        $('.limiter').addClass('cuerpo');

        $.ajax({
            url: "http://localhost:8080/envio/propuesta?contacto=" + contacto + "&puesto=" + puesto + "&salario=" + salario + "&direccion=" + direccion + "&link=" + link + "&info=" + info + "",
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            //processData: false,
            //cache: false,
            success: function () {
                $('#gif').hide();
                $('.limiter').removeClass('cuerpo');
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Has enviado una nueva propuesta laboral',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                }).then((result) => {
                    if (result.value) {
                    }
                })
            }, error: function () {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Operación fallida'
                })
            }
        });

    } else {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Todos los campos son requeridos"
        });
    }
}