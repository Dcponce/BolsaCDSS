$(document).ready(function () {

    var uriP = "http://localhost:8080/proyecto";

    $('#datosPr').on('click', function () {
        guardarH(uriP);
    });

});

function guardarH(uriP) {
    var uri = uriP;
    var idc = $('#idPr').val();
    var nom = $('#nom').val();
    var lnk = $('#link1').val();
    var nom2 = $('#nom2').val();
    var lnk2 = $('#link2').val();
    var nom3 = $('#nom3').val();
    var lnk3 = $('#link3').val();
    var metodo = "POST";
    var accion = "Guardado";


    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    if(nom != "" && lnk != ""){
        saveH(uri, metodo, id, nom, lnk, idc);
    }

    if(nom2 != "" && lnk2 != ""){
        saveH(uri, metodo, id, nom2, lnk2, idc);
    }

    if(nom3 != "" && lnk3 != ""){
        saveH(uri, metodo, id, nom3, lnk3, idc);
    }

}

    function saveH(uri, metodo, id, nom, lnk, idc){

        var data = {
            "id": id,
            "nombre": nom,
            "url": lnk,
            "usuario": {
                "id": idc
            }
        };

        $.ajax({
            url: uri,
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
               return true;
            }
        }).fail(function(){
            return false;
        });
    }