// 表单中字段的key
var columns = [
    {data: "id"},
    {data: 'name'},
    {data: 'userCount'},
    {data: 'remark'},
    {data: function (e) {
		        var element = "";
		        if (e.isDisable === '否') {
		            element = '<span class="label label-success radius">启用</span>';
		        } else {
		            element = '<span class="label label-danger radius">禁用</span>';
		        }
		        return element;
		    },className: "td-status"
    },
    {
        data: function (e) {
            var element =
                  "<a style=\"text-decoration:none\" onClick=\"group_start(this,'" + e.id + "','" + e.isDisable + "')\" href=\"javascript:;\" title=\"启用\">"
                + "<i class=\"Hui-iconfont\">&#xe615;</i></a>&nbsp;"
                + "<a title=\"删除\" href=\"javascript:;\" onclick=\"admin_del(this,'" + e.id + "')\" class=\"ml-5\" style=\"text-decoration:none\">"
                + "<i class=\"Hui-iconfont\">&#xe6e2;</i></a>"
                + datatable_button_edit_full_icon('group-permis', e.id, '', '&#xe63f;');
	            //+ "<a title=\"权限组编辑\" href=\"javascript:;\" onclick=\"group_edit('权限组编辑','group-info','" + e.id + "','800','500')\" class=\"ml-5\" style=\"text-decoration:none\">"
	            //+ "<i class=\"Hui-iconfont\">&#xe6df;</i></a>"
            element += datatable_button_edit("权限组编辑","group-info", e.id);
            return element;
        }
    }
];

/**
 * 提交查询信息
 */
$("#form-group-search").validate({
    submitHandler: function (form) {
        $(form).ajaxSubmit({
            url: "group",
            success: function (jsonResult) {
                if (jsonResult.code == 200) {
                    bindJsonTable("groupList", jsonResult.data, columns);
                } else {
                    layer.msg(jsonResult.message, {icon: 2, time: 1000});
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                layer.msg('error!', {icon: 1, time: 1000});
            }
        });
    }
});

$("#form-group-search").submit();

/*用户组-编辑*/
function group_edit(title, url, id, w, h) {
    layer_show(title, url, w, h);
}

/*用户组-添加*/
function group_add(title, url, w, h) {
    layer_show(title, url, w, h);
}

/*用户组-启用*/
function group_start(obj, id, isDisable) {
	var code = 0
	var text1 = "";
	var text2 = "";
	if(isDisable == "是"){
		text1 = "确认要禁用吗？";
		text2 = "已禁用!";
		code = 0;
	} else {
		text1 = "确认要启用吗？";
		text2 = "已启用!";
		code = 1;
	}
    layer.confirm(text1, function (index) {
        //此处请求后台程序，下方是成功后的前台处理……
    	$.ajax({
            type: 'POST',
            url: 'group/disable?groupId=' + id + "&isDisable=" + code ,
            dataType: 'json',
            success: function (result) {
                if (result.code == 200) {
                    $("#form-group-search").submit();
                    layer.msg(result.message, {icon: 1, time: 1000});
                } else {
                    layer.msg(text2, {icon: 1, time: 1000});
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });

        //$(obj).remove();
        layer.msg(text2, {icon: 6, time: 1000});
    });
}

/*管理员-删除*/
function admin_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'DELETE',
            url: 'group/' + id,
            dataType: 'json',
            success: function (result) {
                if (result.code == 200) {
                    $("#form-group-search").submit();
                    layer.msg(result.message, {icon: 1, time: 3000});
                } else {
                    layer.msg('已删除!', {icon: 1, time: 3000});
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}