<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/18 0018
  Time: 上午 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-edit">
    	<input id="producerId" name="producerId" type="hidden" >
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" class="input-text" value="" placeholder="输入联系人名称" maxlength="30" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>地址：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="addr" name="addr" class="input-text" value="" placeholder="输入联系人地址" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="prodoucer" name="prodoucer" class="select" onfocus="this.defaultIndex=this.selectedIndex;" 
									onchange="this.selectedIndex=this.defaultIndex;">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${producer}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>默认联系人：</label>
            <div class="radio-box">
                <input type="radio" id="isDefault-1" name="isDefault" value="1">
                <label for="isDefault-0">是</label>
            </div>
            <div class="radio-box">
                <input type="radio" id="isDefault-0" name="isDefault" value="0" checked>
                <label for="isDefault-1">否</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">QQ：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="qq" name="qq" class="input-text" value="" placeholder="输入QQ号" maxlength="30">
                <input name="defaultContactType" id="defaultContactType1" type="radio" value="2" checked/>
                <label for="defaultContactType1">默认</label>
            </div>
            <label class="form-label col-xs-4 col-sm-2">Email：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="email" name="email" class="input-text" value="" placeholder="输入Email" maxlength="30">
                <input name="defaultContactType" id="defaultContactType2" type="radio" value="3" />
                <label for="defaultContactType2">默认</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">手机：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="mob" name="mob" class="input-text" value="" placeholder="输入手机号码" maxlength="30">
                <input name="defaultContactType" id="defaultContactType3" type="radio" value="4" />
                <label for="defaultContactType3">默认</label>
            </div>
            <label class="form-label col-xs-4 col-sm-2">固话：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="phone" name="phone" class="input-text" value="" placeholder="输入固话" maxlength="30">
                <input name="defaultContactType" id="defaultContactType4" type="radio" value="5" />
                <label for="defaultContactType4">默认</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">微信：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="vchat" name="vchat" class="input-text" value="" placeholder="输入微信" maxlength="30">
                <input name="defaultContactType" id="defaultContactType5" type="radio" value="6" />
                <label for="defaultContactType5">默认</label>
            </div>
            <label class="form-label col-xs-4 col-sm-2">TG：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="tg" name="tg" class="input-text" value="" placeholder="输入TG" maxlength="30">
                <input name="defaultContactType" id="defaultContactType6" type="radio" value="7" />
                <label for="defaultContactType6">默认</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">Skype：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="skype" name="skype" class="input-text" value="" placeholder="输入Skype" maxlength="30">
                <input name="defaultContactType" id="defaultContactType7" type="radio" value="8" />
                <label for="defaultContactType7">默认</label>
            </div>
            <label class="form-label col-xs-4 col-sm-2">whatapp：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="whatapp" name="whatapp" class="input-text" value="" placeholder="输入whatapp" maxlength="30">
                <input name="defaultContactType" id="defaultContactType8" type="radio" value="9" />
                <label for="defaultContactType8">默认</label>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-5">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    var requestType = "POST";

    $(function () {
        /* 通过获取网设id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        var linkmanId = getUrlParam("linkmanId");
        if (id != null && id != "") {
            $.request.get("linkman/" + id, {}, function (result) {
                if (result.code = 200) {
                    $("#id").val(result.data.id);
                    $("#name").val(result.data.name);
                    $("#prodoucer").val(result.data.prodoucer.code);
                    $("#isDefault").val(result.data.isDefault);
                    $("#addr").val(result.data.addr);
                    
                    $("#qq").val(result.others.qq);
                    $("#email").val(result.others.email);
                    $("#phone").val(result.others.phone);
                    $("#mob").val(result.others.mob);
                    $("#vchat").val(result.others.vchat);
                    $("#tg").val(result.others.tg);
                    $("#skype").val(result.others.skype);
                    $("#whatapp").val(result.others.whatapp);
                    
                    $('input[name="isDefault"]').prop('checked',false);
                    $("#isDefault-"+result.data.isDefault).prop('checked',true);
                    
                   	var radioIndx = result.others.defaultContactType;
                   	$('input[name="defaultContactType"]').prop('checked',false);
                   	
                    if(radioIndx>1){
                    	$('input[name="defaultContactType"]').eq(radioIndx-2).prop('checked',true);
                    } else {
                    	$('input[name="defaultContactType"]').eq(0).prop('checked',true);
                    }
                    
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
        	$("#prodoucer").val(linkmanId);
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "linkman",
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
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
