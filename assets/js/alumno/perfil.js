$(document).ready(function () {

    var uriDoc = "http://localhost:8080/subir/upload";

    $('#perfil').on('click', function () {
        cargarImg(uriDoc);
    });
});

function cargarImg(uriDoc) {
    var file = $('#file').val();

    var midata = new FormData();
    midata.append("file", file);
    
    $.ajax({
        url: uriDoc,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: "POST",
        contentType: "application/json",
        data: midata,
        crossDomain: true,
        processData: false,
        cache: false,
        success: function () {
            alert("Cargar exitosa");
        },
        error: function (error) {
            alert(error);
        }
    });
}