$(document).ready(function () {

    var uriDoc = "http://localhost:8080/subir/upload";


    $('#perfil').on('click', function () {
        cargarImg(uriDoc);
    });
});

function cargarImg(uriDoc) {
    var file = $('#file').val();

    $.ajax({
        data:{
            "file": file
        },
        url: uriDoc,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: "POST",
        success: function () {
            alert("Cargar exitosa");
        },
        error: function (error) {
            alert(error);
        }
    });
}