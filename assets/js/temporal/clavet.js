$(document).ready(function() {
  var uri = "http://localhost:8080/envio/temporal";

  $("#confirm").on("click", function() {
    var correo = $("#email").val();
     temporal(uri, correo);
  });
});

function temporal(uri, correo) {
  var data = {
    "email": correo
  };
  alert(email);
  $.ajax({
    url: uri,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function(res) {
      alert("Enviado");
    }
  });
}
