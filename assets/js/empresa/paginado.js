$(document).ready(function () {
    listar();
});
function listar() {
    $.ajax({
        url: "http://localhost:8080/alumnos",
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
        method: "GET",
        contentType: "application/json",
        success: function (data) {
            if (data != null) {
                $.each(data, function (i, v) {
                    list.push(v);
                })
            }
        }
    });
}

var list = new Array();
var pageList = new Array();
var currentPage = 1;
var numberPerPage = 4;
var numberOfPages = 1;

function makeList() {
    console.log(list.length);
    console.log(list);
    for (var x = 0; x < list.length; x++)
    numberOfPages = getNumberOfPages();
}

function getNumberOfPages() {
    return Math.ceil(list.length / numberPerPage);
}

function nextPage() {
    currentPage += 1;
    loadList();
}

function previousPage() {
    currentPage -= 1;
    loadList();
}

function firstPage() {
    currentPage = 1;
    loadList();
}

function lastPage() {
    currentPage = numberOfPages;
    loadList();
}

function loadList() {
    var begin = ((currentPage - 1) * numberPerPage);
    var end = begin + numberPerPage;

    pageList = list.slice(begin, end);
    drawList();
    check();
}
function drawList() {
    document.getElementById("list").innerHTML = "";
    for (var r = 0; r < pageList.length; r++) {
        document.getElementById("list").innerHTML += '<div class="col-md-6">' +
            '<div class="team d-md-flex" >' +
            '<div>' +
            '<img class="profile-img img-lg rounded-circle" src="../../../BolsaCDSS/img/img_.png">' +
            '</div> ' +
            '<div class="text pl-md-4">' +
            '<span class="location mb-0">Probando</span>' +
            '<h4>' + pageList[r]["nombre"] + ' ' + pageList[r]["apellido"] + '</h4>' +
            '<span class="position"></span>' +
            '<p>222</p>' +
            '<span class="seen">correo</span>' +
            '<p><a href="cv.html?id=2" class="btn btn-primary" target="_blank">Ver curriculum</a></p>' +
            '</div>' +
            '</div>' +
            '</div>'



    }
}

function check() {
    document.getElementById("next").disabled = currentPage == numberOfPages ? true : false;
    document.getElementById("previous").disabled = currentPage == 1 ? true : false;
    document.getElementById("first").disabled = currentPage == 1 ? true : false;
    document.getElementById("last").disabled = currentPage == numberOfPages ? true : false;
}

function load() {
    makeList();
    loadList();
}

window.onload = load;

