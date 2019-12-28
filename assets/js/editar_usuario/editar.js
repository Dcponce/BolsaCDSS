$(document).ready(function() {
    getDatos();
    $("#edit").on("click", function() {
        getUsuario();
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
        $("#email").val(result.email);
        $("#clave").val(result.clave);
      }
    }
  });
}

function edit(res) {
  var email = $("#email").val();
  var clave = $("#clave").val();

  var data = {
    id: res["id"],
    email: email,
    id_credencial: {
      id: res["id_credencial"]["id"]
    },
    clave: clave,
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
      clear();
    },
    error: function(error) {
      console.log(error);
    }
  });
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
