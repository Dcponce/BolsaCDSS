$(document).ready(function () {

    var uriDoc = "http://localhost:8080/documento/upload";

    $('#perfil').on('click', function () {
        cargarImg(uriDoc);
    });
});

function cargarImg(uriDoc) {
    var name = $('#fileName').val();

    var midata = new FormData();
    midata.append("file", name);
    $.ajax({

        url: uriDoc,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        contentType: "application/json",
        data: midata,
        processData: false,
        cache: false,
        success: function () {
            alert("Cargar exitosa");
        },
        error:function(error){
            alert(error);
        }
    });
}