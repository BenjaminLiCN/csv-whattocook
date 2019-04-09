$(document).ready(function() {
    $("#csvReader").fileinput({
        showPreview: false,
        showUpload: false,
        elErrorContainer: '#kartik-file-errors',
        allowedFileExtensions: ["csv"]
        //uploadUrl: '/site/file-upload-single'
    });
    $('#csvReader').on('fileloaded', function(event, file, previewId, index, reader) {
            var reader = new FileReader();
            reader.readAsText(file);            //以文本格式读取
            reader.onload = function(evt){
                var data = evt.target.result;        //读到的数据

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


                $.ajax({
                    beforeSend: function() {
                       console.log(JSON.stringify(itemArray));
                    },
                    type: 'POST',
                    contentType:"application/json",
                    dataType:'json',
                    data: JSON.stringify(itemArray),
                    url:'http://localhost:8080/setItems',
                    success: function(data){
                        console.log(data);
                    }
                });
            }
        });

    $('#jsonReader').on('fileloaded', function(event, file, previewId, index, reader) {
        var reader = new FileReader();
        reader.readAsText(file);            //以文本格式读取
        reader.onload = function(evt){
            var data = evt.target.result;        //读到的数据
            var items = {};
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
            items.payload=itemArray;
            //location.reload();
            $.ajax({
                dataType: 'json',
                data: items,
                url:'http://localhost:8080/setItems',
                success: function(data){
                    console.log(data);
                }
            });
        }
    });

    $("#jsonReader").fileinput({
        showPreview: false,
        showUpload: false,
        elErrorContainer: '#kartik-file-errors',
        allowedFileExtensions: ["json"]
        //uploadUrl: '/site/file-upload-single'
    });

});
