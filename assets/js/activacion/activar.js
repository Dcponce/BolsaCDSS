$("#btnAc").on("click", function () {
  activar();
});

function activar() {
  let params = new URLSearchParams(location.search);
  var id = params.get('id');
  $.ajax({
    url:
      "http://localhost:8080/usuarios/usuario/" + id,
    method: "GET",
    dataType: "json",
    contentType: "application/json"
  });
  Swal.fire({
    icon: 'info',
    title: 'Usuario Activado.',
    text: 'Ya puedes Iniciar Sesion',
    confirmButtonColor: '#3085d6',
    confirmButtonText: 'Ok',
  }).then((result) => {
    if (result.value) {
      window.top.close();
    }
  })
}
