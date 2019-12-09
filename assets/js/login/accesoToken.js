$(document).ready(function(){
    var uri ="http://localhost:8080/login";
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
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
            success: function(res){
                mensaje = "Token obtenido con exito"
                alert(res);
            }
        })
        
    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
    
}