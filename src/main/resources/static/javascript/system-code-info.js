var requestType = "POST";

$(function () {
    /* 通过获取id判断是否为空来确定是增加还是修改 */
    var id = getUrlParam("id");
    var type = getUrlParam("type");
    $("#type").val(type);
    if (id != null && id != "") {
        $.request.get("code/" + id, {}, function (result) {
            if (result.code == 200) {
                $("#id").val(id);
                $("#name").val(result.data.name);
                $("#value").val(result.data.value);
                $("#index").val(result.data.index);
                $("#desc").val(result.data.desc);

                // 不允许修改值,禁用
                $("#value").attr("disabled", true);
            } else {
                layer.msg(result.message, {icon: 1, time: 1000});
            }
        }, null);
    } else {
        // 没有获取到id参数,将提交方式改为新增
        requestType = "put";
    }

    $("#form-group-add").validate({
        rules: {
            name: {
                required: true,
                maxlength: 30,
            },
            value: {
                required: true,
                maxlength: 30,
            },
            index: {
                required: true,
            },
            desc: {
                maxlength: 150,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: requestType,
                url: "code",
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('添加成功!', {icon: 1, time: 1000});
                        parent.dataBind(type);

                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        layer.msg(result.message, {icon: 1, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
        }
    });
});