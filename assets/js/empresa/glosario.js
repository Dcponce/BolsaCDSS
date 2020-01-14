$(document).ready(function(){
    var baseUri="http://localhost:8080/glosario";
 
    getData(baseUri);
     
 });  
 
 function getData(uri){
 
    $.ajax({
        type: "GET", //Metodo por el que se realiza la petición 
        url: uri,
        headers: {
            'Authorization': JSON.parse(localStorage.getItem('Token'))
        },
          contentType: "application/json",
          //data: JSON.stringify(data),
          success: function (data) {
             var tr ='';
 
             if(data.length > 0){
                $("#table>tbody").empty(tr);
                $.each(data, function (i, v) {
                      tr +='<tr>'+
                      
                      '<td>'+v.nombre+'</td>'+
                      '<td>'+v.descripcion+'</td>'+
                      '</tr>';
                
                });
 
                   $("#table>tbody").append(tr);
             }
          }
       
    });
 }
 