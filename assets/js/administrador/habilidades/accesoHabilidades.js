    $(document).ready(function(){
        var base_uri="http://localhost:8080/habilidades";

        getData(base_uri);// obtener toda la informacion

        // nuevo registro
        $('#nuevo').on('click', function(){
            nuevo(base_uri);
        });


    });//END READY

    function getData(base_uri){
        $.getJSON(base_uri, function(data){
            if(data != null){ // verificar si viene informacion

                $('#tabla>tbody').empty();
                var fila ="";

                $.each(data, function(i,v){
                    fila= "<tr>"+
                                "<td>"+v.id+"</td>"+
                                "<td>"+v.descripcion+"</td>"+
                                "<td>"+v.tipo+"</td>"+
                                "<td>"+
                                    "<button type = 'button' class='btn btn-warning'  onclick='editar("+v.id+")'>Editar</button>"+
                                "</td>"+
                                "<td>"+
                                    "<button type = 'button' class='btn btn-danger' onclick='eliminar("+v.id+")'>Eliminar</button>"+
                                "</td>"+
                    
                        "</tr>";
                    $('#tabla>tbody').append(fila);
                });
            }
        });
    }

    function nuevo(base_uri){
        
        var id= $('#id').val();
        var descripcion = $('#descripcion').val();
        var tipo = $('#Tipo').val();
        var metodo = "POST";

        if(id > 0){
                metodo = "PUT";
                accion = "Actualizado";
        }else{
            id = null;
        }

        var data={
            "id": id,
            "descripcion": descripcion,
            "tipo": tipo
        };

            $.ajax({
                url: base_uri,
                method: metodo,
                contentType:"application/json",
                data: JSON.stringify(data),
                success: function(){
                    alert("Registro guardado Existosamente !!!");
                    getData(base_uri);
                    clear();
                }
            });
        }

    function eliminar(id){
        $.ajax({

            url:"http://localhost:8080/habilidades/"+id,
            method:"DELETE",
            contentType:"application/json",
            success: function(){
                alert("Registro eliminado Existosamente !!!");
                getData("http://localhost:8080/habilidades/");
            }
        });
    }

    function editar(id){
        $.getJSON("http://localhost:8080/habilidades/habi/"+id, function(res){
            
            $('#id').val(res.id);
            $('#descripcion').val(res.descripcion);
            $('#Tipo').val(res.tipo);
            getData(base_uri);

        });

    }

    function clear(){
        $('#id').val("");
        $('#descripcion').val("");
        $('#tipo').val(res.tipo);

    }