function UploadData(){

    $('.modal-backdrop').remove();
    $('#fileLoader').remove();
}
$(document).ready(function() {
    $("#fileReader").fileinput({
        showPreview: true,
        showZoom:true,
        showCaption:true,
        elErrorContainer: '#kartik-file-errors',
        allowedFileExtensions: ["csv","json"],
        maxFileCount: 2,
        minFileCount: 2,
        uploadAsync: false,
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
                    $.ajax({
                        beforeSend: function() {
                        },
                        type: 'POST',
                        contentType:"application/json",
                        dataType:'json',
                        data: JSON.stringify(payload),
                        url:'http://localhost:8080/setData',
                        success: function(data){
                            console.log(data);
                        }
                    });
                }
            }
        }


    });



});
