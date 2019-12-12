$(document).ready(function () {
    var base_uri = "http://localhost:8080/habilidades";

    getData(base_uri);// obtener toda la informacion

    // nuevo registro
    $('#nuevo').on('click', function () {
        nuevo(base_uri);
    });


});

function getData(base_uri) {
    $.ajax({
        url: base_uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            if (result != null) { 
                $('#tabla>tbody').empty();
                var fila = "";
                $.each(result, function (i, v) {
                    fila = "<tr>" +
                        "<td>" + v.id + "</td>" +
                        "<td>" + v.descripcion + "</td>" +
                        "<td>" + v.tipo + "</td>" +
                        "<td>" +
                        "<button type = 'button' class='btn btn-warning'  onclick='editar(" + v.id + ")'>Editar</button>" +
                        "</td>" +
                        "<td>" +
                        "<button type = 'button' class='btn btn-danger' onclick='eliminar(" + v.id + ")'>Eliminar</button>" +
                        "</td>" +

                        "</tr>";
                    $('#tabla>tbody').append(fila);
                });
            }
        }
    });
}

function nuevo(base_uri) {

    var id = $('#id').val();
    var descripcion = $('#descripcion').val();
    var tipo = $('#Tipo').val();
    var metodo = "POST";

    if (id > 0) {
        metodo = "PUT";
        accion = "Actualizado";
    } else {
        id = null;
    }

    var data = {
        "id": id,
        "descripcion": descripcion,
        "tipo": tipo
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
            alert("Registro guardado Existosamente !!!");
            getData(base_uri);
            clear();
        }
    });
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/habilidades/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "DELETE",
        contentType: "application/json",
        success: function () {
            alert("Registro eliminado Existosamente !!!");
            getData("http://localhost:8080/habilidades/");
        }
    });
}

function editar(id) {
    $.ajax({
        url: "http://localhost:8080/habilidades/habi/" + id,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        type: 'GET',
        dataType: "json",
        success: function (result) {
            $('#id').val(result.id);
            $('#descripcion').val(result.descripcion);
            $('#Tipo').val(result.tipo);
            getData("http://localhost:8080/habilidades");
        }
    });
}

function clear() {
    $('#id').val("");
    $('#descripcion').val("");
    $('#tipo').val("");

}