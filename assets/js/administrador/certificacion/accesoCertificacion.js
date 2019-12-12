$(document).ready(function(){
    var uri = "http://localhost:8080/certificacion";
    getData(uri );
    $('#nuevo').on('click', function () {
        nuevo(uri);
    })
})

function getData(uri){
    $.ajax({
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null){
                $("#tabla>tbody").empty();
            var fila = "";

            $.each(result,  function(i, v){
                fila = 
                    "<tr>" +
                        "<td>" + v.nombre + "</td>" +
                        "<td>"+
                            "<button type='button' onclick='eliminar("+v.id+")'>Eliminar</button>" +
                        "</td>"+
                        "<td>"+
                            "<button type='button' onclick='editar("+v.id+")'>Editar</button>" +
                        "</td>"+
                    "</tr>";

                    $('#tabla>tbody').append(fila);   
            });
            }
        }   
    }); 
}
function nuevo(uri){
    var id = $('#id').val();
    var nombre = $('#nombre').val();
    var metodo = "POST";
    var accion = "Guardado"

    if (id > 0) {
        metodo = "PUT"
        accion = "Actualizado"

    } else {
        id = null;
    }

    if(nombre != null){
        
        var data = {
            "id": id,
            "nombre": nombre
        }

        $.ajax({
            url: uri,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success:function(){
                mensaje = "Registro " + accion + " exitosamente";
                alert(mensaje);
                getData(uri);
                clear();
            },
            error: function (error){
                console.log(error);
            }
        })

    }else{
        mensaje = "Todos los campos son requeridos";
        alert(mensaje);
    }

}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/certificacion/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token')),
        },
        method: "DELETE",
        contentType: "application/json",
        success: function (res) {
            mensaje = "Registro eliminado exitosamente";
            alert(mensaje);
            getData("http://localhost:8080/certificacion");
        }
    }).fail(function (error) {
        mensaje = "Error: " + error
       alert(mensaje);
    });
}

function editar(id) {
    $.ajax({
        url: "http://localhost:8080/certificacion/byId/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result){
            if (result != null) {
                $('#id').val(result.id);
                $('#nombre').val(result.nombre);
            }
        }
    });
}

function clear(){
    $('#id').val("");
    $('#nombre').val("");

}