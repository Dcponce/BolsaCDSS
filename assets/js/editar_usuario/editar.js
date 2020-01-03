$(document).ready(function() {
  getDatos();
  $("#edit").on("click", function() {
    validar();
  });
});

function getDatos() {
  $.ajax({
    url: "http://localhost:8080/usuarios/getUsu/" + localStorage.getItem("Id"),
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    type: "GET",
    dataType: "json",
    success: function(result) {
      if (result != null) {
        $("#emailU").val(result.email);
        $("#claveU").val(result.clave);
      }
    }
  });
}

function edit(res) {
  var emailU = $("#emailU").val();
  var claveU = $("#claveU").val();

  if (emailU != "" && claveU != "") {
    var data = {
      id: res["id"],
      email: emailU,
      id_credencial: {
        id: res["id_credencial"]["id"]
      },
      clave: claveU,
      id_tipo: {
        id: res["id_tipo"]["id"]
      },
      estado: res["estado"],
      activo: res["activo"]
    };

    $.ajax({
      url: "http://localhost:8080/usuarios/editU",
      headers: {
        Authorization: JSON.parse(localStorage.getItem("Token"))
      },
      method: "PUT",
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function() {
        Swal.fire({
          icon: "success",
          title: "Excelente",
          text: "Datos almacenado",
          confirmButtonColor: "#3085d6",
          confirmButtonText: "Ok"
        }).then(result => {
          if (result.value) {
            location.reload();
          }
        });
      },
      error: function () {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Envio fallido'
        });
    }
    });
    
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Todos los campos son requerido"
    });
  }
}

function getUsuario() {
  $.ajax({
    url: "http://localhost:8080/usuarios/getUsu/" + localStorage.getItem("Id"),
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    type: "GET",
    dataType: "json",
    success: function(res) {
      if (res != null) {
        edit(res);
      }
    }
  });
}

function validar(){
  var correo = $('#correo').val();
  var clave = $('#clave').val();

  if (correo != "" && clave != "") {
    var data = {
      email: correo,
      clave: clave
    };

    $.ajax({
      url: "http://localhost:8080/login",
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function (token) {
        if(localStorage.getItem('Bienvenida') == JSON.stringify(token["mensaje"])){
          getUsuario();

        }else{
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "El usuario que ingreso no le pertenece"
          });
        }
        
      }
    }).fail(function () {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Usuario o contraseña incorrectos"
      });
    });

  }else{
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Todos los campos son requeridos"
    });
  }
}
