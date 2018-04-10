<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/16 0016
  Time: 上午 7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="pos-r">
<div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
    <ul id="treeDemo" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> 系统设置
        <span class="c-gray en">&gt;</span> 字典管理
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <div class="text-c"><form id="form-search" action=""></form></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a class="btn btn-primary radius" onclick="code_edit('添加','system-code-info')" href="javascript:;">
                    <i class="Hui-iconfont">&#xe600;</i>
                    <span id="addButtonLabel">新增</span>
                    <input type="hidden" id="codeType" value="">
                </a>
            </span>
        </span>
        </div>
        <table id="datatable" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="100">ID</th>
                <th width="100">名称</th>
                <th width="100">值</th>
                <th width="100">排序</th>
                <th>描述</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
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
            bindJsonTable("datatable", result.data, columns);
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
//                element += table_edit("code_edit('编辑字典','system-code-info?id=" + e.id + "')");
                element += datatable_button_edit("编辑字典", "system-code-info", e.id);
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
</script>
</body>
</html>