$(document).ready(function () {
    $('#perfil').on('click', function () {
        cargarImg();
    });
    $('#curriculum').on('click', function () {
        cargarCv();
    });
});

function cargarImg() {
    var formData = new FormData();
    var file = $('#archivo').prop('files')[0];
    console.log(file);
    formData.append('file', file);

    $.ajax({
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        dataType: "html",
        data: formData,
        url: "http://localhost:8080/subir/upload"
    }).done(function (data) {
        Swal.fire({
            icon: 'success',
            title: 'Excelente',
            text: 'Archivo cargado con exito',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        }).then((result) => {
            if (result.value) {
                location.reload();
            }
        });
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error al cargar el carchivo'
        });
    });
}

function cargarCv() {
    var formData = new FormData();
    var file = $('#archivocv').prop('files')[0];
    console.log(file);
    formData.append('file', file);

    $.ajax({
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        dataType: "html",
        data: formData,
        url: "http://localhost:8080/subir/curriculum"
    }).done(function (data) {
        Swal.fire({
            icon: 'success',
            title: 'Excelente',
            text: 'Archivo cargado con exito',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        }).then((result) => {
            if (result.value) {
                location.reload();
            }
        });
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error al cargar el carchivo'
        });
    });
}