$(document).ready(function () {

    modificar();
    modificarE();
    modificarPro();
});

function modificar() {
    $.ajax({
        url: "http://localhost:8080/alumnos/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {                   
                $('#id').val(data.id);
                $('#nombre').val(data.nombre);
                $('#apellido').val(data.apellido);
                $('.user-name').text(data.nombre);
                $('.display-income').text(data.apellido);
                $('#telefono').val(data.telefono);
                $('#celular').val(data.celular);
                $('#direccion').val(data.direccion);
                $('#proyecto').val(data.proyecto);
                var fe = new Date(data.fecha);

                var mes = (parseInt(fe.getMonth()+1) < 10 )? "0"+(fe.getMonth()+1): (fe.getMonth()+1);
                var fecha = fe.getFullYear() +"-"+ mes +"-"+ fe.getDate();
                $('#nacimiento').val(fecha);
                var uri = "http://localhost:8080/municipios";
                var idDepto = data.id_municipio.departamento.id;
                var idMuni = data.id_municipio.id;

                getDepto(uri, idDepto);
                getMunicipioByDepto(uri, idDepto, idMuni);
            } 
        }
    });
}
function modificarE() {
    $.ajax({
        url: "http://localhost:8080/educacion/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#idE').val(data.id);
                $('#universidad').val(data.universidad);
                $('#carrera').val(data.carrera);
                $('#estado').val(data.estado);
                var idCer = data.id_certificacion.id;
                $('#nivel').val(data.nivel);
                getCert(idCer);
            }
        }
    });
}
function modificarPro() {
    $.ajax({
        url: "http://localhost:8080/proyecto/usuario/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {

            if (data != null) {
                var total = data.length;

                $.each(data, function (i, v) {

                    $('#formulario').append('<input type="hidden" id="idPr' + i + '" value="' + v.id + '">' +
                        '<label>Nombre del proyecto</label>'+'<input type="text" name="txtNombre" id="nom' + i + '" placeholder="Nombre de proyecto" class="form-control" value="' + v.nombre + '"><br>' +
                        '<label>Link del proyecto</label>'+'<input type="text" name="txtLink" id="link' + i + '" placeholder="Link de proyecto" class="form-control" value="' + v.url + '">' +
                        '<a href="#" style=" color:  #c0392b " onclick="borrar(\'' + v.id + '\')" title="Eliminar"><i class="material-icons">delete_forever</i></a>');

                   

                });

                if (total < 3) {
                    for (var i = 0; i < (3 - total); i++) {
                        $('#formulario').append('<input type="hidden" id="idPr' + (i+1) + '">' +
                            '<br>' + '<label>Nombre del proyecto</label>' + '<input type="text" name="txtNombre" id="nom' + (i + 1) + '" placeholder="Nombre de proyecto" class="form-control"><br>' +
                            '<label>Link del proyecto</label>' + '<input type="text" name="txtLink" id="link' + (i + 1) + '" placeholder="Link de proyecto" class="form-control"><br>');
                    }
                }

            } else{
                for (var i = 0; i < 3; i++) {
                    $('#formulario').append('<input type="hidden" id="idPr' + i + '">' +
                        '<label>Nombre del proyecto</label>'+'<input type="text" name="txtNombre" id="nom' + i + '" placeholder="Nombre de proyecto" class="form-control"><br>' +
                        '<label>Link del proyecto</label>'+'<input type="text" name="txtLink" id="link' + i + '" placeholder="Link de proyecto" class="form-control"><br>');
                }
            }
        }
    });
}

function borrar(id) {
    Swal.fire({
        icon: 'success',
        title: 'Excelente',
        text: 'Datos eliminados',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: "http://localhost:8080/proyecto/" + id,
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('Token')),
                },
                method: "DELETE",
                contentType: "application/json",
                success: function (res) {
                    location.reload();
                }
            }).fail(function (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'La acción no se pudo completar'
                });
            });
        }
    })
}















