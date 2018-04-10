var requestType = "POST";

$(function () {
    /* 通过获取网设id判断是否为空来确定是增加还是修改 */
    var id = getUrlParam("id");
    if (id != null && id != "") {
        $.request.get("devices/" + id, {}, function (result) {
            if (result.code = 200) {
                $("#id").val(result.data.id);
                $("#producer").val(result.data.producer.code);
                $("#brand").val(result.data.brand.code);
                $("#type").val(result.data.type.code);
                $("#code").val(result.data.code);
                $("#platform").val(result.data.platform.code);
                $("#serverroom").val(result.data.serverroom.code);
                $("#ip").val(result.data.ip);
                $("#port").val(result.data.port);
                $("#account").val(result.data.account);
                $("#password").val(result.data.password);
                $("#number").val(result.data.number);
                $("#setting").val(result.data.setting);
            } else {
                layer.msg(result.message, {icon: 1, time: 1000});
            }
        }, null);
    } else {
        // 没有获取到id参数,将提交方式改为新增
        requestType = "put";
    }

    $("#form-devices-edit").validate({
        rules: {
            producer: {
                required: true,
            },
            brand: {
                required: true,
            },
            type: {
                required: true,
            },
            platform: {
                required: true,
            },
            serverroom: {
                required: true,
            },
            port: {
                required: true,
                max: 65535,
                digits:true,
            },
            ip: {
                required: true,
                maxlength: 30,
                ip: true,
            },
            account: {
                required: true,
                maxlength: 30,
            },
            password: {
                required: true,
                maxlength: 30,
            },
            number: {
                maxlength: 30,
            },
            setting: {
                maxlength: 30
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: requestType,
                url: "devices",
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('成功!', {icon: 1, time: 1000});
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
    
    $('#producer').change(function(){
    	//获取供应商的id
    	var producerId=$(this).children('option:selected').val();
    	$("#linkman").empty();
    	$.ajax({
    		url:'linkman',  
            type:"get",  
            data:{
            	producerId : producerId
            },  
            success: function (result) {
            	$("#linkman").append("<option value=''>---请选择---</option>");
            	var linkmans = result.data;
                if(linkmans != null && linkmans.length > 0){
                    for(var i=0; i < linkmans.length; i++){
                        $("#linkman").append("<option value='"+linkmans[i].id+"'>"+linkmans[i].name+"</option>");  
                    }
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                layer.msg("error!", {icon: 5, time: 1000});
            }
        });  
	});
});