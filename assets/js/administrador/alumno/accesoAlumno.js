$(document).ready(function () {
    var base_uri = "http://localhost:8080/alumnos";

    getData(base_uri);


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
                tabla = $('#table').DataTable({
                    //con esto mandamos a traer los datos de la base
                    data: response,
                    columns: [
                        { data: 'id' },
                        { data: 'nombre' },
                        { data: 'apellido' },
                        { data: 'telefono' },
                        { data: 'celular' },
                        { data: 'id_usuario.email' },
                        { data: 'proyecto.nombre' },
                        //{data: null, "defaultContent": "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a class='btn btn-info btn-sm' class='btnModificar'> <span class='glyphicon glyphicon-wrench'></span></a> <a class='btn btn-danger btn-sm' id='btnEliminar'><span class='glyphicon glyphicon-remove'></span></a>   </div></div>"},
                        //botones
                        //{
                            //"render": function (data, type, row) {
                               // return "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a href='#'style='color: #2980b9' onclick='editar(" + row.id + ")' data-toggle='modal' data-target='#nuevoU'> <i class='material-icons'>edit</i></a> <a href='#' style='color:  #c0392b ' onclick='borrar(" + row.id + ")' data-toggle='modal' data-target='#borrar'><i class='material-icons'>delete_forever</i></a> </div></div>"
                            //}
                       // },
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

