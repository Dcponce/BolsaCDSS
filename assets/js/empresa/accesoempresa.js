$(document).ready(function () {

    var base_uri = "http://localhost:8080/empresa";

    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });

    $('#buscar').on('click', function () {
        filtro();
    });

    editar(base_uri)
    getCert();
    getDepto();
    createOptions();
});

function nuevo(base_uri) {
    var id = $('#id').val();
    var nombre = $('#nombre').val();
    var telefono = $('#telefono').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
    } else {
        id = null;
    }

    if (nombre != null) {

        var data = {
            "id": id,
            "nombre": nombre,
            "telefono": telefono,
            "id_usuario": {
                "id": JSON.parse(localStorage.getItem('Id')),
            }
        };

        $.ajax({
            url: base_uri,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: metodo,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Excelente',
                    text: 'Datos almacenados correctamente'
                });
            }
        });
    }
}

function editar(base_uri) {
    $.ajax({
        url: base_uri + "/empre/" + JSON.parse(localStorage.getItem('Id')),
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#id').val(data.id);
                $('#nombre').val(data.nombre);
                $('#nom').text(data.nombre);
                $('#telefono').val(data.telefono);
            }
        }
    });
}

function getCert() {
    $.ajax({
        url: "http://localhost:8080/certificacion",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#certi').empty();
                $('#certi').append("<option selected disabled>Seleccione una certificación</option>");
                var fila = "";
                $.each(result, function (i, v) {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    $('#certi').append(fila);
                });
            }
        }
    });
}

function getDepto() {
    $.ajax({
        url: "http://localhost:8080/municipios",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#depa').empty();
                $('#depa').append("<option selected disabled>Seleccione un departamento</option>");

                var fila = "";
                $.each(result, function (i, v) {
                    fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    $('#depa').append(fila);
                });
            }
        }
    });
}

function createOptions() {
    $.ajax({
        url: "http://localhost:8080/habilidades",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) {

                $('#select').empty();

                var option = "";
                var options = [], _options;

                $.each(result, function (i, v) {
                    option = "";
                    option = '<option value="' + v.id + '">' + v.descripcion + '</option>';
                    options.push(option);
                });

                _options = options.join('');
                $('#select')[0].innerHTML = _options;
            }
        }
    }).done(function () {
        var multipleCancelButton = new Choices('#select', {
            removeItemButton: true,
            maxItemCount: 10,
            searchResultLimit: 5
            //renderChoiceLimit:5
        });
    });
}

function filtro() {
    var certi = $('#certi').val();
    var depto = $('#depa').val();
    var habil = [$('#select').val()];


    $.ajax({
        url: "http://localhost:8080/alumnos/filter?depto=" + depto + "&certi=" + certi + "&habil=" + habil + "",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                $('#resultado').empty();
                $.each(data, function (i, v) {
                    $('#resultado').append('<div class="col-md-6">' +
                        '<div class= "team d-md-flex" >' +
                        '<div>' +
                        '<img class="profile-img img-lg rounded-circle" src="../../../BolsaCDSS/img/img_' + v.id_usuario.id + '.png">' +
                        '</div> ' +
                        '<div class="text pl-md-4">' +
                        '<span class="location mb-0">' + v.id_municipio.departamento.nombre + ' / ' + v.id_municipio.nombre + '</span>' +
                        '<h4>' + v.nombre + ' ' + v.apellido + '</h4>' +
                        '<span class="position"></span>' +
                        '<p>' + v.celular + '</p>' +
                        '<span class="seen">' + v.id_usuario.email + '</span>' +
                        '<p><a href="cv.html?id=' + v.id +'" class="btn btn-primary" target="_blank">Ver curriculum</a></p>' +
                        '</div>' +
                        '</div>' +
                        '</div>')
                });
            }
        }

    });
}