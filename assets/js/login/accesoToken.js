$(document).ready(function(){
    var uri = "http://localhost:8080/login";
    
    $('#iniciar').on('click', function () {
        getToken(uri);
    })
})

function getToken(uri, email) {
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
                localStorage.setItem("Email", JSON.stringify(res["user"]["username"]));
                localStorage.setItem("Bienvenida", JSON.stringify(res["mensaje"]));
                localStorage.setItem("Token", JSON.stringify(res["token"]));
                const rol = res["user"]["authorities"][0]["authority"];
                getId(email);

                if(rol == "ROLE_ADMIN"){
                    location.href = "vistas/administrador/admin.html";

                }else if(rol == "ROLE_EMPRESA"){
                    location.href = "vistas/empresa/registro.html";

                }else if(rol == "ROLE_ALUMNO"){
                    location.href = "vistas/alumno/datos.html";

                }else{
                    location.href = "index.html";
                }
            }
        })
        
    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }
    
}

function getId(email){
    $.ajax({
        url: "http://localhost:8080/usuarios/getId/"+email,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function(res){
            localStorage.setItem("Id", JSON.stringify(res["id"]));
        },
        error: function(error){
            console.log(error);
        }
    });
}