var language = {// 语言设置
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 项结果",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索:",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
    }
};

function bindDataTable(tableName, requestUrl, queryData, columns) {
    $("#" + tableName).DataTable({
        "ajaxSource": requestUrl,
        "fnServerParams": queryData,
        "language": language,
        "columns": columns,
        "autoWidth": false,
        "displayLength": 20,
        "lengthChange": false,
        "searching": false,
        "bServerSide": false

    });
};


function bindJsonTable(tableName, data, columns) {

    if($.fn.dataTable.isDataTable("#" + tableName)){
        $("#" + tableName).DataTable().destroy()
    }

    $("#" + tableName).dataTable({
        "data": data,
        "language": language,
        "columns": columns,
        "autoWidth": false,
        "pageLength": 20,
        "lengthChange": false,
        "searching": false,
        "serverSide": false,
        "order": [[ 0, 'desc' ], [ 1, 'desc' ], [ 2, 'desc' ]]
    });

}
