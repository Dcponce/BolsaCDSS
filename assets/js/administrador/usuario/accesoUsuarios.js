$(document).ready(function () {
  var uri = "http://localhost:8080/usuarios";
  getData(uri);
  getRoles(uri + "/api/listaTipo", id, 0);
  $("#nuevo").on("click", function () {
    getCred();
  });
  $("#bloq").on("click", function () {
    bloquear();
  });
  $("#dbloq").on("click", function () {
    bloquear();
  });
});

function getData(uri) {

  $.ajax({
    type: "GET", //Metodo por el que se realiza la petición 
    url: uri,
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
            { data: 'email' },
            { data: 'credencial.codigo' },
            { data: 'id_tipo.descripcion' },
            { data: 'estado' },
            { data: 'activo' },
            //{data: null, "defaultContent": "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a class='btn btn-info btn-sm' class='btnModificar'> <span class='glyphicon glyphicon-wrench'></span></a> <a class='btn btn-danger btn-sm' id='btnEliminar'><span class='glyphicon glyphicon-remove'></span></a>   </div></div>"},
            //botones
            {
              "render": function (data, type, row) {
                return "<div class='row ValAcc'><div class='col-xs-12 Val-UDP'><a href='#'style='color: #d03027' onclick='editar(" + row.id + ")' data-toggle='modal' data-target='#bloqueo' title='Bloquear'> <i class='material-icons'>lock</i></a> </div><div class='col-xs-12 Val-UDP'><a href='#'style='color: #ecb731' onclick='editar(" + row.id + ")' data-toggle='modal' data-target='#desblo' title=' Desbloquear'> <i class='material-icons'>lock_open</i></a> </div></div>"
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

function getRoles(uri, id) {
  $.ajax({
    url: uri,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    type: "GET",
    dataType: "json",
    success: function (result) {
      if (result != null) {
        $("#tipo").empty();
        $('#tipo').append("<option selected disabled>Seleccione el tipo de usuario</option>");
        var fila = "";
        $.each(result, function (i, v) {

          if (v.id == id) {
            fila = '<option value="' + v.id + '" selected>' + v.descripcion + '</option>';
          } else {
            fila = '<option value="' + v.id + '">' + v.descripcion + '</option>';
          }
          $("#tipo").append(fila);
        });
      }
    },
    error: function (error) {
      location.href = "../../index.html";
    }
  });
}

function getCred() {
  var credencial = $("#credencial").val();
  $.ajax({
    url: "http://localhost:8080/usuarios/usu/" + credencial,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
      var idC = res["id"];
      console.log(idC);
      nuevo("http://localhost:8080/usuarios", idC);
    }
  });
}

function nuevo(uri, idC) {
  var email = $("#email").val();
  var clave = $("#clave").val();
  var tipo = $("#tipo").val();
  var estado = $("#estado").val();
  var activo = $("#activo").val();
  var metodo = "POST";
  var accion = "Guardado";

  if (id > 0) {
    metodo = "PUT";
    estado = "false";
    idC = $("#credencial").val();
  } else {
    id = null;
  }

  if (credencial != null && email != "" && clave != "") {
    var data = {
      "id": null,
      "email": email,
      "credencial": {
        "id": idC
      },
      "clave": clave,
      "id_tipo": {
        "id": tipo
      },
      "estado": estado,
      "activo": activo
    };

    $.ajax({
      url: uri,
      headers: {
        Authorization: JSON.parse(localStorage.getItem("Token"))
      },
      method: metodo,
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function () {
        mensaje = "Registro " + accion + " exitosamente";
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

function editar(id) {
  $.ajax({
    url: "http://localhost:8080/usuarios/getUsu/" + id,
    headers: {
      'Authorization': JSON.parse(localStorage.getItem('Token'))
    },
    type: 'GET',
    dataType: "json",
    success: function (result) {
      if (result != null) {
        $("#id").val(result.id);
        $("#iddes").val(result.id);
        $("#st").val(result.estado);
        
      }
    }
  });
}

function titulo() {
  $('#exampleModalLabel').text("Nuevo usuario")
}
function borrar(id) {
  $('#idDelete').val(id);
}

function bloquear() {
  idC = $("#id").val();
  var st = $("#st").val();
  if (idC > 0) {
    var data = {
      "id": idC,
    };

    $.ajax({
      url: "http://localhost:8080/usuarios",
      headers: {
        Authorization: JSON.parse(localStorage.getItem("Token"))
      },
      method: "PUT",
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function () {
        if (st == "false") {
          Swal.fire({
            icon: 'success',
            title: 'Excelente',
            text: 'Usuario desbloqueado',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
          }).then((result) => {
            if (result.value) {
              location.reload();
            }
          })
        } else {
          Swal.fire({
            icon: 'success',
            title: 'Excelente',
            text: 'Usuario bloqueado',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
          }).then((result) => {
            if (result.value) {
              location.reload();
            }
          })
        }
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