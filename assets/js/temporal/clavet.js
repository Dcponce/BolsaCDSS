$(document).ready(function () {
  var uri = "http://localhost:8080/envio/temporal";

  $("#confirm").on("click", function () {
    var correo = $("#email").val();

    if (correo != "") {
      temporal(uri, correo);

    } else {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Todos los campos son requeridos"
      });
    }
  });
  $("#finalizar").on("click", function () {
    getUsuario(localStorage.getItem('idUser'), localStorage.getItem('idPass'))
  });

  $("#aut").on("click", function () {
    autenticacion();
  });
});

function temporal(uri, correo) {
  $('#gif').show();
  $('.limiter').addClass('cuerpo');
  var data = {
    "email": correo
  };
  $.ajax({
    url: uri,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (res) {
      window.location.replace("autenticacion.html");
    }
  });
}

function autenticacion() {
  var clavet = $("#temporal").val();

  if (clavet != "") {
    $.ajax({
      url: "http://localhost:8080/temporal/" + clavet,
      method: "GET",
      dataType: "json",
      contentType: "application/json",
      success: function (res) {
        localStorage.setItem("idUser", JSON.stringify(res["usuario"]["id"]))
        localStorage.setItem("idPass", JSON.stringify(res["id"]))
        window.location.replace("editar.html");
      },
      error: function(error){
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Usted no cuenta con una contraseña temporal."
        });
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

function getUsuario(idUsuario, id) {
  var password = $("#nueva").val();

  $.ajax({
    url: "http://localhost:8080/usuarios/getUsu/" + idUsuario,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
      updateClave(res, id, password);
    }
  });
}

function updateClave(res, id, password) {
  var data = {
    id: res["id"],
    email: res["email"],
    credencial: {
      id: res["credencial"]["id"]
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
    success: function () {
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
    success: function (res) {
      localStorage.removeItem('idUser');
      localStorage.removeItem('idPass');
      Swal.fire({
        icon: 'info',
        title: 'Contraseña Actualizada',
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
