$(document).ready(function () {
    var url = "http://localhost:8080/centros"
       getData(url);
       $('#nuevo').click(function () { 
           nuevo(url);
       });

       $('#delete').on('click', function () {
        var id = $('#idDelete').val();
        eliminar(id);
    });
});

function getData(url){
    $.ajax({
        url: url,
        headers: {
          Authorization: JSON.parse(localStorage.getItem("Token"))
        },
        method: "GET",
        contentType: "json",
        success: function (res) {
            var tr = "";
            $('#table>tbody').empty();
            $.each(res, function (i, v) { 
                tr += '<tr>'+
                            '<td>'+v.id+'</td>'+
                            '<td>'+v.nombre+'</td>'+
                            '<td>'+'<div class="row ValAcc"><div class="col-xs-12 Val-UDP"><a href="#"style="color: #2980b9" onclick="editar(' + v.id + ')" data-toggle="modal" data-target="#nuevoU" title="Editar"> <i class="material-icons">edit</i></a> <a href="#" style="color:  #c0392b " onclick="borrar(' + v.id + ')" data-toggle="modal" data-target="#borrar" title="Eliminar"><i class="material-icons">delete_forever</i></a> </div></div></td>'+
                        '</tr>'    
            });
            $('#table>tbody').append(tr);
        }
      });
}

function nuevo(uri) {
    var id = $('#id').val();
    var nombre = $('#nombre').val();
    var metodo = "POST";
    if (id > 0) {
        metodo = "PUT"

    } else {
        id = null;
    }

    if (nombre != "") {

        var data = {
            "id": id,
            "nombre": nombre
        }

        $.ajax({
            url: uri,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Datos almacenados',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                }).then((result) => {
                    if (result.value) {
                        location.reload();
                    }
                })
                clear();
            },
            error: function (error) {
                console.log(error);
            }
        })

    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La acción no se pudo completar'
        });
    }

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/centros/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token')),
        },
        method: "DELETE",
        contentType: "application/json",
        success: function (res) {
            Swal.fire({
                icon: 'success',
                title: 'Excelente',
                text: 'Datos eliminados',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            }).then((result) => {
                if (result.value) {
                    location.reload();
                }
            })
        }
    }).fail(function (error) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La acción no se pudo completar'
        });
    });
}

function editar(id) {
    $('#exampleModalLabel').text("Modificar")
    $.ajax({
        url: "http://localhost:8080/centros/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {
                $('#id').val(result.id);
                $('#nombre').val(result.nombre);
            }
        }
    });
}

function clear(){
    $('#nombre').val("");
}

function titulo() {
    $('#exampleModalLabel').text("Nueva")
}

function borrar(id) {
    $('#idDelete').val(id);
}