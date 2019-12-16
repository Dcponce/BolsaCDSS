$(document).ready(function() {
  var uri = "http://localhost:8080/usuarios";
  getData(uri + "/lista");
  getRoles(uri + "/api/listaTipo");
  $("#nuevo").on("click", function() {
    getCred();
  });
});

function getData(uri) {
  $.ajax({
    url: uri,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    type: "GET",
    dataType: "json",
    success: function(result) {
      if (result) {
        $("#tabla>tbody").empty();
        var fila = "";

        $.each(result, function(i, v) {
          fila =
            "<tr>" +
            "<td>" +
            v.email +
            "</td>" +
            "<td>" +
            v.id_credencial.codigo +
            "</td>" +
            "<td>" +
            v.id_tipo.descripcion +
            "</td>" +
            "<td>" +
            v.estado +
            "</td>" +
            "<td>" +
            v.activo +
            "</td>" +
            "<td>" +
            "<button type='button' onclick='eliminar(" +
            v.id +
            ")'>Eliminar</button>" +
            "</td>" +
            "<td>" +
            "<button type='button' onclick='eliminar(" +
            v.id +
            ")'>Editar</button>" +
            "</td>" +
            "</tr>";

          $("#tabla>tbody").append(fila);
        });
      }
    },
    error: function(error) {
      location.href = "../../index.html";
    }
  });
}

function getRoles(uri) {
  $.ajax({
    url: uri,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    type: "GET",
    dataType: "json",
    success: function(result) {
      if (result != null) {
        $("#tipo").empty();
        var fila = "";
        $.each(result, function(i, v) {
          fila =
            ' <option value=" ' + v.id + ' "> ' + v.descripcion + "</option>";

          $("#tipo").append(fila);
        });
      }
    },
    error: function(error) {
      location.href = "../../index.html";
    }
  });
}

function getCred() {
  var credencial = $("#credencial").val();
  $.ajax({
    url: "http://localhost:8080/usuarios/usu/"+credencial,
    headers: {
        'Authorization': JSON.parse(localStorage.getItem('Token'))
    },
    method: "GET",
    contentType: "json",
    success: function(res) {
        nuevo("http://localhost:8080/usuarios", JSON.stringify(res));
    }

  });
}

function nuevo(uri, res) {
  var id = $("#id").val();
  var email = $("#email").val();
  var credencial = $("#credencial").val();
  var clave = $("#clave").val();
  var tipo = $("#tipo").val();
  var metodo = "POST";
  var accion = "Guardado";

  if (id > 0) {
    metodo = "PUT";
    accion = "Actualizado";
  } else {
    id = null;
  }

  if (credencial != null) {
    var data = {
      id: id,
      email: email,
      id_credencial: {
          id: 3
      },
      clave: clave,
      id_tipo: {
          id: tipo
      },
      estado: true,
      activo: true
    };

    $.ajax({
      url: uri,
      headers: {
        Authorization: JSON.parse(localStorage.getItem("Token"))
      },
      method: metodo,
      contentType: "application/json",
      data: data,
      processData: false,
      cache: false,
      success: function() {
        mensaje = "Registro " + accion + " exitosamente";
        alert(mensaje);
        getData(uri);
      }
    });
  } else {
    mensaje = "Todos los campos son requeridos";
    alert(mensaje);
  }
}
