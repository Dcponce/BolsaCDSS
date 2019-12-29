$(document).ready(function() {
  var uri = "http://localhost:8080/envio/temporal";

  $("#confirm").on("click", function() {
    var correo = $("#email").val();
    temporal(uri, correo);
  });

  $("#aut").on("click", function() {
    autenticacion();
  });
});

function temporal(uri, correo) {
  var data = {
    email: correo
  };
  $.ajax({
    url: uri,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function(res) {
      window.location.replace("autenticacion.html");
    }
  });
}

function autenticacion() {
  var clavet = $("#temporal").val();

  $.ajax({
    url: "http://localhost:8080/temporal/" + clavet,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function(res) {
      var idUsuario = res["usuario"]["id"];
      var password = res["clavet"];
      var id = res["id"];
      getUsuario(idUsuario, password, id);
    }
  });
}

function getUsuario(idUsuario, password, id) {
  $.ajax({
    url: "http://localhost:8080/usuarios/getUsu/" + idUsuario,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function(res) {
      updateClave(res, password, id);
    }
  });
}

function updateClave(res, password, id) {
  var data = {
    id: res["id"],
    email: res["email"],
    id_credencial: {
      id: res["id_credencial"]["id"]
    },
    clave: password,
    id_tipo: {
      id: res["id_tipo"]["id"]
    },
    estado: res["estado"],
    activo: res["activo"]
  };

  $.ajax({
    url: "http://localhost:8080/usuarios/clavet",
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function() {
      eliminar(id);
    }
  });
}

function eliminar(id) {
  $.ajax({
    url: "http://localhost:8080/temporal/" + id,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    method: "DELETE",
    contentType: "application/json",
    success: function(res) {
      Swal.fire({
        icon: 'info',
        title: 'Inicia Sesion',
        text: '¡Utiliza tu nueva clave!',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok'
      }).then((result) => {
        if (result.value) {
            window.location.replace("../../login.html");
        }
    })
    }
  });
}
