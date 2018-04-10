var setting = {
    view: {
        dblClickExpand: false,
        showLine: false, //设置是否显示节点与节点之间的连线
        selectedMulti: false, //设置是否能够同时选中多个节点
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""
        }
    },
    callback: {
        /** 点击事件,执行请求服务器数据,并绑定到datatables */
        onClick: function (e, treeId, node) {// 点击事件
            $("#codeType").val(node.id);
            $("#addButtonLabel").html("新增" + node.name);
            dataBind(node.id);
        },
        /* 点击事件前置,判断是否为目录借点 */
        beforeClick: function (treeId, treeNode) {
            if (treeNode.isParent) {
                return false;
            } else {
                return true;
            }
        }
    }
};

/*绑定datatable数据*/
function dataBind(type) {
    $.get("code", {
        type: type
    }, function (result) {
        bindJsonTable("syscodeList", result.data, columns);
    }, "json");
}

// 表单中字段的key
var columns = [
    {data: 'id'},
    {data: 'name'},
    {data: 'value'},
    {data: 'index'},
    {data: 'desc'},
    {
        data: function (e) {
            var element = "";
            if (e.disable == 0) {
                element = '<span class="label label-success radius">已启用</span>';
            } else {
                element = '<span class="label label-default radius">已禁用</span>';
            }
            return element;
        },
        className: "td-status"
    },
    {
        "data": function (e) {
            var element = table_disable(e.id, e.disable);
            element += table_edit("code_edit('编辑字典','system-code-info?id=" + e.id + "')");
            return element;
        },
        className: "td-manage"
    }
];

/* 初始化属性菜单 */
$.get("codetype", null, function (result) {
    $.fn.zTree.init($("#treeDemo"), setting, result.data);
}, "json");

/*产品-编辑*/
function code_edit(title, url) {
    var codeType = $("#codeType").val();
    if (codeType == null || codeType == "") {
        layer.msg('请选择栏目!', {icon: 5, time: 1000});
        return;
    }

    if (url.split('?').length > 1) {
        url += "&";
    } else {
        url += "?";
    }
    url += "type=" + codeType;
    layer_show(title, url, '800', '500');
}

/*禁用按钮事件-停用*/
function disable_stop(obj, id) {
    layer.confirm('确认要停用吗？', function (index) {
        $.post("code/" + id, {
            status: 1
        }, function (result) {
            if (result.code == 200) {
                disable_stop_html(obj, id);
                layer.msg('已停用!', {icon: 6, time: 1000});
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    });
}

/*禁用按钮事件-启用*/
function disable_start(obj, id) {
    layer.confirm('确认要启用吗？', function (index) {
        $.post("code/" + id, {
            status: 0
        }, function (result) {
            if (result.code == 200) {
                disable_start_html(obj, id);
                layer.msg('已启用!', {icon: 6, time: 1000});
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    });
}