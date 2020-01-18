    var current_page = 1;
    var records_per_page = 2;
    var listData = new Array();
    $("#btn_next").css("visibility", "hidden");
    $("#btn_prev").css("visibility", "hidden");
    $("#btn-pagination").css("display", "none");

    $(document).ready(function () {

        var base_uri = "http://localhost:8080/empresa";

        $('#nuevo').on('click', function () {
            nuevo(base_uri);
        });

        $('#buscar').on('click', function () {
            filtro();
        });


        $('#btn_next').on('click', function () {
            nextPage();
        });

        editar(base_uri)
        getCert();
        getDepto();
        createOptions();
    });

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

        if (nombre != "" && telefono != "") {

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

        }else{
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Todos los campos son requeridos"

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
                    $('#certi').append("<option selected disabled>Seleccione una certificaci�n</option>");
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


    function changePage(page) {

        var btn_next = document.getElementById("btn_next");
        var btn_prev = document.getElementById("btn_prev");
        var listing_table = document.getElementById("resultado"); //listingTable
        var page_span = document.getElementById("page");
     
        // Validate page
        if (page < 1) page = 1;
        if (page > numPages()) page = numPages();
    
        listing_table.innerHTML = "";
        for (var i = (page-1) * records_per_page; i < (page * records_per_page); i++) {
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
                    var content = '';
                    $.each(data, function (i, v) {

                        content = 
                            '<div class="col-md-6">' +
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
                            '</div>';

                        listData.push(content);

                        //$('#resultado').append(content);
                    });
                }
            }

        }).done(function(){
            changePage(1);
        });
    }