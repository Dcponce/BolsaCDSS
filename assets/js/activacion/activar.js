$("#btnAc").on("click", function() {
  activar();
});

function activar() {
  $.ajax({
    url:
      "http://localhost:8080/usuarios/usuario/" +
      localStorage.getItem("Locked"),
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
        localStorage.removeItem('Locked');
        window.top.close();
    }
  })
}
