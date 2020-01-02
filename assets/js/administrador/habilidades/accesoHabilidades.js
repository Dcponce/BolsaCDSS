$(document).ready(function () {
    var base_uri = "http://localhost:8080/habilidades";

    getData(base_uri);

    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });

    $('#delete').on('click', function () {
        var id = $('#idDelete').val();
        eliminar(id);
    });

});

function getData(base_uri) {

    $.ajax({
        type: "GET", //Metodo por el que se realiza la petición 
        url: base_uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        contentType: "json", // NOT dataType
        success: function (response) {
            //alert(response[2].id);
            if (!response['error']) {
                tabla = $('#example').DataTable({
                    //con esto mandamos a traer los datos de la base
                    data: response,
                    columns: [
                        { data: 'id' },
                        { data: 'descripcion' },
                        //{data: null, "defaultContent": "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a class='btn btn-info btn-sm' class='btnModificar'> <span class='glyphicon glyphicon-wrench'></span></a> <a class='btn btn-danger btn-sm' id='btnEliminar'><span class='glyphicon glyphicon-remove'></span></a>   </div></div>"},
                        //botones
                        {
                            "render": function (data, type, row) {
                                return "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a href='#'style='color: #2980b9' onclick='editar(" + row.id + ")' data-toggle='modal' data-target='#nuevoU'> <i class='material-icons'>edit</i></a> <a href='#' style='color:  #c0392b ' onclick='borrar(" + row.id + ")' data-toggle='modal' data-target='#borrar'><i class='material-icons'>delete_forever</i></a> </div></div>"
                            }
                        },
                    ],

                    language: {
                        processing: "Traitement en cours...",
                        search: "Buscar: ",
                        lengthMenu: "Mostrar _MENU_ ",
                        info: " _START_ a _END_ de _TOTAL_ credenciales",
                        infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "Aucune donnée disponible dans le tableau",
                        paginate: {
                            first: "Premier",
                            previous: "Anterior",
                            next: "siguiente",
                            last: "Dernier"
                        },
                        aria: {
                            sortAscending: ": activer pour trier la colonne par ordre croissant",
                            sortDescending: ": activer pour trier la colonne par ordre décroissant"
                        }

                    }
                });
            }
        }
    });
}

function nuevo(base_uri) {

    var id = $('#id').val();
    var descripcion = $('#descripcion').val();
    var tipo = $('#tipo').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }
    if (descripcion != null) {
        var data = {
            "id": id,
            "descripcion": descripcion,
            "tipo": tipo
        }

        $.ajax({
            url: base_uri,
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
                    text: 'Datos almacenado',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                }).then((result) => {
                    if (result.value) {
                        location.reload();
                    }
                })
                clear();
            }
        }).fail(function (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'La acción no se pudo completar'
            });
        });
    }

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/habilidades/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "DELETE",
        contentType: "application/json",
        success: function () {
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
    $('#exampleModalLabel').text("Modificar habilidad")
    $.ajax({
        url: "http://localhost:8080/habilidades/habi/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            $('#id').val(result.id);
            $('#descripcion').val(result.descripcion);
            $('#tipo').val(result.tipo);
        }
    });
}

function clear() {
    $('#id').val("");
    $('#descripcion').val("");
    $('#tipo').val("");

}

function titulo() {
    $('#exampleModalLabel').text("Nueva habilidad")
}
function borrar(id) {
    $('#idDelete').val(id);
}