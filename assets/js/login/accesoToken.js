$(document).ready(function() {
  var uri = "http://localhost:8080/login";

  $("#btn").on("click", function() {
    getToken(uri);
  });
});

function getToken(uri) {
  var email = $("#correo").val();
  var clave = $("#clave").val();

  if (email != null && clave != null) {
    var data = {
      email: email,
      clave: clave
    };

    $.ajax({
      url: uri,
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function(token) {
        validar(email, token)
      }
    });
  } else {
    mensaje = "Todos los campos son requeridos";
    alert(mensaje);
  }
}

function getId(email, rol) {
  $.ajax({
    url: "http://localhost:8080/usuarios/getId/" + email,
    headers: {
      Authorization: JSON.parse(localStorage.getItem("Token"))
    },
    method: "GET",
    contentType: "json",
    success: function(res) {
      localStorage.setItem("Id", JSON.stringify(res["id"]));

      var ruta = "";

      switch (rol) {
        case "ROLE_ADMIN":
          ruta = "vistas/administrador/admin.html";
          break;
        case "ROLE_EMPRESA":
          ruta = "vistas/administrador/admin.html";
          break;
        case "ROLE_ALUMNO":
          ruta = "vistas/alumno/inicio.html";
          break;
        default:
          ruta = "index.html";
          break;
      }
      window.location.replace(ruta);
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function validar(email, token) {
  $.ajax({
    url: "http://localhost:8080/usuarios/getId/" + email,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function(res) {
      if (res["activo"] == "true") {
        localStorage.setItem("Email", JSON.stringify(token["user"]["username"]));
        localStorage.setItem("Bienvenida", JSON.stringify(token["mensaje"]));
        localStorage.setItem("Token", JSON.stringify(token["token"]));
        const rol = token["user"]["authorities"][0]["authority"];
        getId(email, rol);

      }else{
        alert("Usuario no activo");
        enviar(email);
      }
    }
  });
}

function enviar(email){
    var data = {
        "email": email
    }
    $.ajax({
        url: "http://localhost:8080/usuarios/activacion",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (res) {
            alert("Enviado")
            window.location.replace("confirmacion.html");
        },
        error: function () {
            alert("Envio fallido");
        }
    });
}
