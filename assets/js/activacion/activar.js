$("#btnAc").on("click", function () {
    activar();
  });

  function activar(){
    $.ajax({
      url: "http://localhost:8080/usuarios/usuario/" + localStorage.getItem('Activo'),
      method: "GET",
      dataType: "json",
      contentType: "application/json",
      success: function (res) {
        Swal.fire({
          icon: 'info',
          title: 'Excelente',
          text: 'Usuario Activado',
          confirmButtonColor: '#3085d6',
          confirmButtonText: 'Ok'
        }).then((result) => {
          if (result.value) {
          }
        })
      }  
    });
  }
  