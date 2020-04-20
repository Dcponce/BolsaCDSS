$(document).ready(function () {

    filtro();

    $('#buscar').on('click', function () {
        filtro();
    });
});

var current_page = 1;
var records_per_page = 10;
var listData = new Array();
$("#btn_next").css("visibility", "hidden");
$("#btn_prev").css("visibility", "hidden");
$("#btn-pagination").css("display", "none");

function prevPage() {
    if (current_page > 1) {
        current_page--;
        changePage(current_page);
    }
}

function nextPage() {
    if (current_page < numPages()) {
        current_page++;
        changePage(current_page);
    }
}

function numPages() {
    return Math.ceil(listData.length / records_per_page);
}
function changePage(page) {
    var listing_table = document.getElementById("resultado"); //listingTable
    var page_span = document.getElementById("page");

    // Validate page
    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    $("#resultado").empty();
    listing_table.innerHTML = "";
    for (var i = (page - 1) * records_per_page; i < (page * records_per_page); i++) {

        if (listData[i] == "undefined" || listData[i] == undefined) break;
        listing_table.innerHTML += listData[i] + "<br>";

    }

    page_span.innerHTML = page;

    if (page == 1) {
        $("#btn_prev").css("visibility", "hidden");
    } else {
        $("#btn_prev").css("visibility", "visible");
    }

    if (page == numPages()) {
        $("#btn_next").css("visibility", "hidden");
    } else {
        $("#btn_next").css("visibility", "visible");
        $("#btn-pagination").css("display", "block");
    }
}
var idUsuarios = new Array();

function filtro() {
    var certi = $('#certi').val();
    var depto = $('#depa').val();
    var habil = [$('#select').val()];
    var centro = $('#proyecto').val();

    if (certi > 0 || depto > 0 || habil[0].length > 0) {
        current_page = 1;
        listData = [];
    }

    $.ajax({
        url: "http://localhost:8080/alumnos/filter?depto=" + depto + "&certi=" + certi + "&habil=" + habil + "&cent=" + centro + "",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "json",
        success: function (data) {
            if (data != null) {
                var content = '';
                listData.length = 0
                var contador = 0;
                $.each(data, function (i, v) {
                    content =
                        '<div class="col-md-6">' +
                        '<div class= "team d-md-flex" >' +
                        '<div>' +
                        '<img class="profile-img img-lg rounded-circle" src="../../img/img_' + v.id_usuario.id + '.png" onerror="imgError(this);">' +
                        '</div> ' +
                        '<div class="text pl-md-4">' +
                        '<p>' + v.proyecto.nombre + '</p>' +
                        '<h4>' + v.nombre + ' ' + v.apellido + '</h4>' +
                        '<span class="location mb-0">' + v.id_municipio.departamento.nombre + ' / ' + v.id_municipio.nombre + '</span>' +
                        '<span class="position"></span>' +
                        '<p id="cn' + i + '"></p>' +
                        '<span class="position"></span>' +
                        '<p>' + v.celular + '</p>' +
                        '<span class="seen">' + v.id_usuario.email + '</span>' +
                        '<p><a href="cv.html?id=' + v.id + '" class="btn btn-danger" target="_blank">Ver currículum</a></p>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                    contador++;
                    listData.push(content);
                    idUsuarios.push(v.id_usuario.id);

                });
                $('#cantidad').text(contador);
            }
            educacion(idUsuarios);
        }

    }).done(function () {
        changePage(current_page);
    });
}

function educacion(id) {
    $.each(id, function (i, v) {
        $.ajax({
            url: "http://localhost:8080/educacion/usuario/" + v,
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('Token'))
            },
            method: "GET",
            contentType: "json",
            success: function (data) {
                if (data != null) {
                    $('#cn' + i).text(data.id_certificacion.nombre);
                }
            }
        });
    });
    idUsuarios.length = 0;
}