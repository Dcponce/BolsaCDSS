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
            success: function(res){ //nehemias@gmail.com
                console.log(res)
                localStorage.setItem("Email", JSON.stringify(res["user"]["username"]));
                localStorage.setItem("Rol", JSON.stringify(res["user"]["authorities"][0]["authority"]));
                localStorage.setItem("Bienvenida", JSON.stringify(res["mensaje"]));
                localStorage.setItem("Token", JSON.stringify(res["token"]));
            }
        })
        
    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
    
}