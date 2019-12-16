$(document).ready(function () {

    var uriP = "http://localhost:8080/proyecto";

    $('#datosPr').on('click', function () {
        guardarH(uriP);
    });

});

function guardarH(uriP) {
    var uri = uriP;
    var idc = JSON.parse(localStorage.getItem('Id'));
    var nom = $('#nom').val();
    var lnk = $('#link1').val();
    var nom2 = $('#nom2').val();
    var lnk2 = $('#link2').val();
    var nom3 = $('#nom3').val();
    var lnk3 = $('#link3').val();
    var metodo = "POST";
    var accion = "Guardados";


    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizados";
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
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
               alert("Datos "+accion+" correctamente");
            }
        }).fail(function(){
            alert("No se pudo completar la acción");
        });
    }