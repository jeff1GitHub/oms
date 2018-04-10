var requestType = "POST";

$(function () {
    /* 通过获取机房id判断是否为空来确定是增加还是修改 */
    var serverroomId = getUrlParam("id");
    if (serverroomId != null && serverroomId != "") {
        $.request.get("serverroom/" + serverroomId, {}, function (result) {
            if (result.code == 200) {
                $("#id").val(result.data.id);
                $("#name").val(result.data.name);
                $("#producer").val(result.data.producer.code);
                $("#area").val(result.data.area);
                $("#remark").val(result.data.remark);
            } else {
                layer.msg(result.message, {icon: 1, time: 1000});
            }
        }, null);
    } else {
        // 没有获取到id参数,将提交方式改为新增
        requestType = "put";
    }

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-group-add").validate({
        rules: {
            producer: {
                required: true,
            },
            name: {
                required: true,
                maxlength: 30,
            },
            area: {
                required: true,
                maxlength: 30,
            },
            remark: {
                maxlength: 150
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: requestType,
                url: "serverroom",
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('添加成功!', {icon: 1, time: 1000});
                        parent.$("#form-search").submit();

                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        layer.msg(result.message, {icon: 5, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 5, time: 1000});
                }
            });
        }
    });
});