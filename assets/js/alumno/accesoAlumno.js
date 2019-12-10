$(document).ready(function () {

    var uri = "http://localhost:8080/alumnos";

});

function guardar(uri){
    var id=$('#id').val();
    var nom=$('#nombre').val();
    var apellido=$('#apellido').val();
    var tel=$('#telefono').val();
    var cel=$('#celular').val();
    var dir=$('#direccion').val();
    var pro=$('#proyeto').val();
    var nac=$('#nacimiento').val();
    var muni=$('#municipio').val();
    var metodo = "POST";
    var accion = "Guardado";

    if(id > 0){
        metodo = "PUT";
        accion="Actualizado";
    }else{
        id=null;
    }

    if(sede !="" && muni >0){

        var data={
            "id":id,
            "nombre":nom,
            "apellido":apellido,
            "telefono":tel,
            "celular":cel,
            "direccion":dir,
            "proyecto":pro,
            "fecha":nac,
            "id_municipio":{
                "id":muni
            },
            "id_usuario":{
                "id":id
            }
        };

        $.ajax({
            url:uri,
            method:metodo,
            contentype: "application/json",
            data: JSON.stringify(data),
            success: function(){
                
            }
        });
    }
}