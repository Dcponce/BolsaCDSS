$(document).ready(function () {

    var uriP = "http://localhost:8080/proyecto";

    $('#datosPr').on('click', function () {
        guardarH(uriP);
    });

});

function guardarH(uriP) {
    var uri = uriP;
    var idc = JSON.parse(localStorage.getItem('Id'));
    var id1 = $('#idPr0').val();
    var nom = $('#nom0').val();
    var lnk = $('#link0').val();

    var id2 = $('#idPr1').val();
    var nom2 = $('#nom1').val();
    var lnk2 = $('#link1').val();

    var id3 = $('#idPr2').val();
    var nom3 = $('#nom2').val();
    var lnk3 = $('#link2').val();
    var metodo = "POST";
    var accion = "Guardados";


    if (id1 > 0) {
        metodo = "PUT";
        accion = "Actualizados";
    } else {
        id = null;
    }

    if(nom != "" && lnk != ""){
        saveH(uri, metodo, id1, nom, lnk, idc);
    }

    if(nom2 != "" && lnk2 != ""){
        saveH(uri, metodo, id2, nom2, lnk2, idc);
    }

    if(nom3 != "" && lnk3 != ""){
        saveH(uri, metodo, id3, nom3, lnk3, idc);
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
               alert("Datos guardados correctamente");
            }
        }).fail(function(){
            alert("No se pudo completar la acción");
        });
    }