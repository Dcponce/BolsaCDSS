$(document).ready(function () {
  $("#nuevo").on("click", function () {
    getCred();
  });

  $('#show').mousedown(function () {
    $('#clave').removeAttr('type');
    $('#show').addClass('fa-eye-slash').removeClass('fa-eye');
  });

  $('#show').mouseup(function () {
    $('#clave').attr('type', 'password');
    $('#show').addClass('fa-eye').removeClass('fa-eye-slash');
  });

  $('#clave').keyup(function () {
    $('#mensaje').html(checkClave($('#clave').val()));
  });

  $('#clave2').keyup(function () {
    Cclave();
  });

  $('#email').focusout(function () {
    check_email();
  });
});

function checkClave(clave) {
  var fuerza = 0
  if (clave.length == 0) {
    $('#mensaje').removeClass();
    $('#mensaje').hide();
  } else {
    if (clave.length >= 1 && clave.length <= 6) {
      $('#mensaje').show();
      $('#mensaje').removeClass();
      $('#mensaje').addClass('Short');
      return 'Muy baja.'
    }

    if (clave.length > 7) fuerza += 1
    if (clave.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) fuerza += 1
    if (clave.match(/([a-zA-Z])/) && clave.match(/([0-9])/)) fuerza += 1
    if (clave.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) fuerza += 1
    if (clave.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) fuerza += 1

    if (fuerza < 2) {
      $('#mensaje').show();
      $('#mensaje').removeClass()
      $('#mensaje').addClass('Weak')
      return 'Débil'
    } else if (fuerza == 2) {
      $('#mensaje').show();
      $('#mensaje').removeClass()
      $('#mensaje').addClass('Good')
      return 'Buena'
    } else {
      $('#mensaje').show();
      $('#mensaje').removeClass()
      $('#mensaje').addClass('Strong')
      return 'Fuerte'
    }
  }
}

function check_email() {
  var re = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
  var emaU = $('#email').val();
  if (emaU != "") {
    if (re.test($('#email').val().trim())) {
      $('#email_error').hide();
      $('#email').css("border-bottom", "2px solid #89D200");
      return false;
    } else {
      $("#email_error").html("Ingrese un correo válido.");
      $('#email_error').show();
      $('#email').css("border-bottom", "2px solid #FE0000");
      return true;
    }
  } else {
    $("#email_error").html("El campo es requerido.");
    $('#email_error').show();
    $('#email').css("border-bottom", "2px solid #FE0000");
    return true;
  }
}

function Cclave() {
  var clave = $("#clave").val();
  var clave2 = $("#clave2").val();

    if (clave == clave2) {
      $('#clave2_error').hide();
      $('#clave2').css("border-bottom", "2px solid #89D200");
      return false;
    }else{
      $("#clave2_error").html("Las claves no coinciden.");
      $('#clave2').css("border-bottom", "2px solid #FE0000");
      return true;
    }  
}

function nuevo(idC) {
  var email = $("#email").val();
  var clave = $("#clave").val();
  var credencial = idC;

  if (credencial != null && email != "" && checkClave(clave) == 'Buena' || checkClave(clave) == 'Fuerte' && Cclave() === false) {
    var data = {
      id: null,
      email: email,
      id_credencial: {
        id: credencial
      },
      clave: clave,
      id_tipo: {
        id: 3
      },
      estado: true,
      activo: false
    };

    $.ajax({
      url: "http://localhost:8080/usuarios",
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(data),
      //processData: false,
      //cache: false,
      success: function () {
        Swal.fire({
          icon: "success",
          title: "Excelente",
          text: "Usuario registrado",
          confirmButtonColor: "#3085d6",
          confirmButtonText: "Ok"
        }).then(result => {
          if (result.value) {
            $('#gif').show();
            $('.limiter').addClass('cuerpo');
            activar(data);
          }
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

function getCred() {
  var credencial = $("#credencial").val();
  $.ajax({
    url: "http://localhost:8080/usuarios/usu/" + credencial,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
      var idC = res["id"];
      nuevo(idC);
    },
    error: function () {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Operación fallida"
      });
    }
  });
}

function activar(data) {
  $.ajax({
    url: "http://localhost:8080/usuarios/activacion",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (res) {
      $('#gif').hide();
      $('.limiter').removeClass('cuerpo');
      localStorage.setItem("Locked", JSON.stringify(res["id"]));
      Swal.fire({
        icon: "info",
        title: "Revisa tu correo",
        text: "Para activación de usuario.",
        confirmButtonColor: "#3085d6",
        confirmButtonText: "Ok"
      }).then(result => {
        if (result.value) {
          window.location.replace("../../index.html");
        }
      });
    },
    error: function () {
      Swal.fire({
        icon: "error",
        title: "Ops...",
        text: "La acción no se puede completar.",
        confirmButtonColor: "#3085d6",
        confirmButtonText: "Ok"
      }).then(result => {
        if (result.value) {
          $('#gif').hide();
          $('.limiter').removeClass('cuerpo');
        }
      });
    }
  });
}
