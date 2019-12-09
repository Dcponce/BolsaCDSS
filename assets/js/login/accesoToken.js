$(document).ready(function(){
    var uri ="http://localhost:8080/login";
    $('#iniciar').on('click', function () {
        getToken(uri);
    })
})

function getToken(uri){
    var email = $('#email').val();
    var clave = $('#clave').val();

    if(email != null && clave != null){
        var data = {
            "email": email,
            "clave": clave
        }

        $.ajax({
            url: uri,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(){
                mensaje = "Token obtenido con exito"
                alert(mensaje);
            }
        })
        
    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
    
}