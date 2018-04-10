/*全屏打开窗口*/
function window_full(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*打开窗口*/
function window_open(title, url, w, h) {
    layer_show(title, url, w, h);
}


/** 按钮-删除 */
function datatable_button_del(url, id) {
    url += "/" + id;
    return button_button("删除", "&#xe6e2;", "func_del('" + url + "')");
}

/** 按钮-编辑 */
function datatable_button_edit(title, url, id) {
    url += "?id=" + id;
    return button_button(title, "&#xe6df;", "window_open('" + title + "', '" + url + "', 800, 500)");
}

/** 按钮-编辑-自定义图标 */
function datatable_button_edit_icon(title, url, id, icon) {
    url += "?id=" + id;
    return button_button(title, icon, "window_open('" + title + "', '" + url + "', 1000, 600)");
}

/** 按钮-详情 */
function datatable_button_detail(title, url, id) {
    url += "?id=" + id;
    return button_button(title, "&#xe60c;", "window_open('" + title + "', '" + url + "', 800, 500)");
}

/** 按钮-增加 */
function datatable_button_add(title, url, id) {
    url += "?id=" + id;
    return button_button(title, "&#xe600;", "window_open('" + title + "', '" + url + "', 800, 500)");
}

/** 按钮-编辑 */
function datatable_button_edit_full(url, id, params) {
    url += "?id=" + id + params;
    return button_button("编辑", "&#xe6df;", "window_full('编辑', '" + url + "')");
}

function datatable_button_edit_full_icon(url, id, params, icon) {
    url += "?id=" + id + params;
    return button_button("编辑", icon, "window_full('编辑', '" + url + "')");
}

/** a标签按钮 */
function button_button(title, flag, callback) {
    var a = $(document.createElement("a"));
    a.attr("title", title);
    a.attr("href", "javascript:;");
    a.attr("class", "ml-5");
    a.attr("style", "text-decoration:none");
    a.attr("onclick", callback);

    var i = $(document.createElement('i'));
    i.attr("class", "Hui-iconfont");
    i.html(flag);
    a.append(i);

    return a[0].outerHTML;
}

/**方法-删除*/
function func_del(url) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'DELETE',
            url: url,
            dataType: 'json',
            success: function (result) {
                if (result.code == 200) {
                    $("#form-search").submit();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('error!', {icon: 1, time: 1000});
            },
        });
    });
}


/**
 * 提交查询信息
 */
function form_search_validate() {
    $("#form-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (jsonResult) {
                    if (jsonResult.code == 200) {
                        bindJsonTable("datatable", jsonResult.data, columns);
                    } else {
                        layer.msg(jsonResult.message, {icon: 5, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 5, time: 1000});
                }
            });
        }
    });
}

//===========================================还需整理一下==========================================================
/*初始化表格中的禁用按钮*/
function table_disable(id, status) {
    var element;
    if (status == 0) {
        element = '<a onClick="disable_stop(this,' + id + ')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>';
    } else {
        element = '<a onClick="disable_start(this,' + id + ')" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>';
    }
    return element;
}

/*表格中的编辑按钮-弹出框*/
function table_edit(callback) {
    return '<a title="编辑" href="javascript:;" onclick="' + callback + '" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
}

/*表格中的编辑按钮-全屏*/
function table_edit_full(callback) {
    return '<a title="编辑" href="javascript:;" onclick="' + callback + '" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
}

/*表格中的删除按钮*/
function table_del(callback) {
    return '<a title="删除" href="javascript:;" onclick="' + callback + '" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';
}

/*禁用按钮事件-停用*/
function disable_stop_html(obj, id) {
    $(obj).parents("tr").find(".td-manage").prepend('<a onClick="disable_start(this,' + id + ')" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
    $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">禁用</span>');
    $(obj).remove();
}

/*禁用按钮事件-启用*/
function disable_start_html(obj, id) {
    $(obj).parents("tr").find(".td-manage").prepend('<a onClick="disable_stop(this,' + id + ')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">启用</span>');
    $(obj).remove();
}
