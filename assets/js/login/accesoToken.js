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
            success: function(res){
                mensaje = "Token obtenido con exito"
                alert(mensaje);
                console.log(res)
                localStorage.setItem("token", JSON.stringify(res["token"]));
                //localStorage.setItem("Rol", JSON.stringify(res["user"]["authorities"]["authority"]));
               
            }
        })
        
    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
    
}