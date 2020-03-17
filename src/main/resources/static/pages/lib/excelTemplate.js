$(document).ready(function () {
    
    $('#upload').click(function(){
        var excel = $("#excel")[0].files[0];
        var formData = new FormData();
        formData.append("excel", excel);
        formData.append("template", $('#template').val());
        formData.append("sheetNum", $('#sheetNum').val());
        formData.append("startRow", $('#startRow').val());
        formData.append("endRow", $('#endRow').val());
        $.ajax({
            url: "/excelTemplate/upload",
            data: formData,
            processData: false,   // jQuery不要去处理发送的数据
            contentType: false,   // jQuery不要去设置Content-Type请求头
            method: "POST",
            success: function (result) {
                $('#result').text(result)
            }
        });
    });
});