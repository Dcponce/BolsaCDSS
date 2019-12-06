$(document).ready(function(){
    var uri = "http://localhost:8080/usuarios/lista";
    getData(uri);
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