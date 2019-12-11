    $(document).ready(function(){

        var base_uri="http://localhost:8080/empresa"

    });

    function nuevo(base_uri){
        var id = $('#id').val();
        var nombre = $('#nombre').val();
        var telefono = $('#telefono'). val();
        var direccion = $('#direccion').val();
        var municipio = $('#municipio').val();

        if(id > 0){
            metodo = "PUT";
            accion = "actualizado";
        }else{
            id = null;
        }

        if ( empre != "" && muni > 0) {

            var data = {
                "id": id,
                "nombre": nombre,
                "telefono": telefono,
                "id_usuario": {
                    "id": id
                },
                "id_municipio": {
                    "id": muni
                },
            };
    
            $.ajax({
                url: uri,
                method: metodo,
                contentype: "application/json",
                data: JSON.stringify(data),
                success: function () {
                    
                    getData(base_uri);
                }
            });
        }

    }

    function getDepartamen(base_uri, id) {

        $.getJSON(base_uri+"/depa/muni", function (data) {
            if (data != null) {
    
                $('#departamento').empty();
                $('#departamento').append("<option selected disabled>Seleccione un departamento</option>");
    
                var fila = "";
                $.each(data, function (i, v) {
    
                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }
                    $('#departamento').append(fila);
                });
            }
        });
    }

    function getMunicipioByDepartamen(uriD, idDepto, id) {
        $.getJSON(uriD + "/em/{id}" + idDepto, function (data) {
            if (data != null) {
                $('#municipio').empty();
                $('#municipio').append("<option selected disabled>Seleccione un municipio</option>");
                var fila = "";
                $.each(data, function (i, v) {
    
                    if (v.id == id) {
                        fila = '<option value="' + v.id + '" selected>' + v.nombre + '</option>';
                    } else {
                        fila = '<option value="' + v.id + '">' + v.nombre + '</option>';
                    }
    
                    $('#municipio').append(fila);
                });
            }
        });
    }
    
    
