function UploadData(){
     $('#myModal').modal('show');
}
$(document).ready(function() {
    $('#rSave').prop('disabled', true);
    $('#myModal').modal('hide');
    $("#fileReader").fileinput({
        showPreview: true,
        showZoom:true,
        showCaption:true,
        elErrorContainer: '#kartik-file-errors',
        allowedFileExtensions: ["csv","json"],
        maxFileCount: 2,
        minFileCount: 2,
        browseLabel: 'Select csv and json file'
    });

    $('#fileReader').on('filebatchselected', function(event, files) {
        var items;
        var recipes;
        var payload={};
        for(var i = 0;i < 2;i++){
            var file=files[i];
            //console.log(file);
            var reader = new FileReader();
            reader.readAsText(file);            //以文本格式读取
            reader.onload = function(evt){
                var data = evt.target.result;        //读到的数据
                try{
                    var recipe = $.parseJSON( data );
                    recipes = recipe;
                    payload.rcps=Object(recipes);


                }catch(err){
                    var itemArray = [];
                    var items = data.split(/\r?\n|\r/);
                    items.forEach(function(item) {
                        var elements = item.split(",");
                        var obj = {};
                        obj.Item=elements[0];
                        obj.Amount=elements[1];
                        obj.Unit=elements[2];
                        obj.Date=elements[3];
                        itemArray.push(obj);
                    });
                    items=itemArray;
                    payload.itms=Object(items);
                }finally{
                    if(typeof(payload.itms) == 'undefined'||typeof(payload.rcps) == 'undefined')
                        return;
                    $.ajax({
                        beforeSend: function() {

                        },
                        type: 'POST',
                        contentType:"application/json",
                        dataType:'json',
                        data: JSON.stringify(payload),
                        url:'http://localhost:8080/setData',
                        success: function(data){
                            $('#rSave').prop('disabled', false);
                            var rName = data.name;
                            $('#rName').html(rName);
                            var ingredients = data.ingredients;
                            var number = 0;
                            for(var m =0;m<ingredients.length;m++){
                                number += 1;
                                var tab = document.getElementById('rTable');
                                for(var j=0;j<3;j++){
                                    var rowIndex = tab.rows.length+1;
                                    var tr  = tab.insertRow();
                                    var td1 = tr.insertCell();
                                    var td2 = tr.insertCell();
                                    if(j==0){
                                        td1.innerHTML = "Ingredient "+number;
                                        td2.innerHTML = "Item: "+ingredients[m].item;
                                    }
                                    if(j==1){
                                        td1.innerHTML = "";
                                        td2.innerHTML = "Amout: "+ingredients[m].amount;
                                    }
                                    if(j==2){
                                        td1.innerHTML = "";
                                        td2.innerHTML = "Unit: "+ingredients[m].unit;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }


    });



});
