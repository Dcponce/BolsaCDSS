$(document).ready(function(){
    var uri = "http://localhost:8080/usuarios";
    getData(uri + "/lista");
    getRoles(uri + "/api/listaTipo");
    $('#nuevo').on('click', function () {
        nuevo(uri);
    })

})

function getData(uri){
    $.getJSON(uri, function(data){
        if(data != null){
            $("#tabla>tbody").empty();
            var fila = "";

            $.each(data, function(i, v){
                fila = 
                    "<tr>" +
                        "<td>" + v.email + "</td>" +
                        "<td>" + v.id_credencial.codigo + "</td>" +
                        "<td>" + v.id_tipo.descripcion + "</td>" +
                        "<td>" + v.estado + "</td>" +
                        "<td>" + v.activo + "</td>" +
                        "<td>"+
                            "<button type='button' onclick='eliminar("+v.id+")'>Eliminar</button>" +
                        "</td>"+
                        "<td>"+
                            "<button type='button' onclick='eliminar("+v.id+")'>Editar</button>" +
                        "</td>"+
                    "</tr>";

                    $('#tabla>tbody').append(fila);   
            });
        }
    });
}

function getRoles(uri){
    $.getJSON(uri, function (data){
         if(data != null){

             $('#tipo').empty();
             var fila = "";
             $.each(data, function(i, v){
               fila = ' <option value=" '+v.id+' "> ' + v.descripcion + '</option>';

               $('#tipo').append(fila);

             });

         }
    })
}

function getCred(uri){
    $.getJSON(uri, function(data){
        if(data != null){
            $('#idCred').empty();
            var inpu = "";
            $.each(data, function(i, v){
                inpu = '<input type="hidden" value=" '+v.id+' "> ';

                $('#idCred').append(inpu);

            });

        }
    });
}

function nuevo(uri){
    var id = $('#id').val();
    var email = $('#email').val();
    var credencial = $('#credencial').val();
    var clave = $('#clave').val();
    var tipo= $('#tipo').val();
    var mensaje = "";
    var metodo = "POST";
    var accion = "Guardado";
    var idC = 0;
    
    if(id > 0){
        metodo = "PUT";
        accion = "Actualizado"

    }else{
        id = null;
    }

    if(credencial != null){
        $.getJSON(uri+"/byCrede/"+credencial, function(data){
            
            if(data != null){

                $.each(data, function(i, v){
                   idC = v.id;
                });

                if(idC){
                    var data = {
                        "id": id,
                        "email": email,
                        "id_credencial": {
                            "id": idC
                        },
                        "clave": clave,
                        "id_tipo": {
                            "id": tipo
                        }
                    }
    
                    $.ajax({
                        url: uri,
                        method: metodo,
                        contentType: "application/json",
                        data: JSON.stringify(data),
                        success:function(){
                            mensaje = "Registro " + accion + " exitosamente";
                            alert(mensaje);
                            getData(uri+"lista");
                        }
                    })
                }else{
                    alert("Eror al registrarse")
                }
                
            }
        });
    }

}