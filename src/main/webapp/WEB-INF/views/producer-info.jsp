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
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商ID：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="bandwidth" name="bandwidth" class="input-text" value="" placeholder="输入供应商ID" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" class="input-text" value="" placeholder="输入名称" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="producerTypeSelect" name="producerTypeSelect" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${role}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'producerTypeSelect', '请选择角色!');">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>公司全称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="companyName" name="companyName" class="input-text" value="" placeholder="输入公司全称" maxlength="30" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>支付宝帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="aliPay" name="aliPay" class="input-text" value="" placeholder="输入支付宝帐号" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>签章人：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="signaturePerson" name="signaturePerson" class="input-text" value="" placeholder="输入签章人" maxlength="30" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>银行类型：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="bankType" name="bankType" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${bankType}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">登录域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="loginDomain" name="loginDomain" class="input-text" value="" placeholder="输入登录域名" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>银行卡号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="bankAccount" name="bankAccount" class="input-text" value="" placeholder="输入银行卡号" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="loginAccount" name="loginAccount" class="input-text" value="" placeholder="输入登录帐号" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>登录密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="loginPassword" name="loginPassword" class="input-text" value="" placeholder="输入登录密码" maxlength="30">
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
        if (id != null && id != "") {
            $.request.get("producer/" + id, {}, function (result) {
                if (result.code = 200) {
                	
                	//Producer{id=20, name=李四, roleId=null, companyName=4314, signaturePerson=3214, 
                	//	aliPay=321432, bankType=null, bankAccount=32432, loginDomain=432, 
                	//	loginAccount=143214, loginPassword=143214, isDel=0}
                	
                    $("#id").val(result.data.id);
                    $("#name").val(result.data.name);
                    $("#role").val();
                    $("#companyName").val(result.data.companyName);
                    $("#signaturePerson").val(result.data.signaturePerson);
                    $("#aliPay").val(result.data.aliPay);
                    $("#bankAccount").val(result.data.bankAccount);
                    $("#bankType").val(result.data.bankType.code);
                    $("#loginDomain").val(result.data.loginDomain);
                    $("#loginAccount").val(result.data.loginAccount);
                    $("#loginPassword").val(result.data.loginPassword);
                    
                    getProducerInfo(result.data);
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
            	
            	// 判断角色是否重复
            	var arr = new Array(); 
            	$("input[name='producerType']").each(function(){ 
            		arr.push($(this).val());
            	}); 
                if(isRepeat(arr)){
                	layer.msg("角色重复！", {icon: 5, time: 1500});
                	return;
                }
                
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "producer",
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
        
    })
    
    //addElement(this, 'role', '选择角色!')
    function addElement(obj, name, error) {
    	var val = $("#" + name).val();
    	var text = $("#" + name).find("option:selected").text();
    	
        if (val == null || val == "") {
            layer.msg(error, {icon: 5, time: 1000});
            return;
        }
        $("#" + name).val("");

        var div1 = getElement(name, val, text);

        $(obj).parent().parent().after($(div1));
    }
    

    // 判断是否重复
	function isRepeat(arr) {
		var hash = {};
		for ( var i in arr) {
			if (hash[arr[i]])
				return true;
			hash[arr[i]] = true;
		}
		return false;
	}

	function getElement(name, val, text) {
		var div1 = $(document.createElement('div')); //创建最外层div节点
		div1.attr("class", "row cl");

		var label = $(document.createElement('label')); //创建label节点
		label.attr("class", "form-label col-xs-4 col-sm-3");
		div1.append(label);

		var div2 = $(document.createElement('div'));
		div2.attr("class", "formControls col-xs-8 col-sm-3");
		div1.append(div2);

		var hidden = $(document.createElement('input')); //创建code隐藏框
		hidden.attr("class", "input-text valid");
		hidden.attr("type", "hidden");
		hidden.attr("name", "producerType");
		hidden.attr("value", val);
		div2.append(hidden);

		var input = $(document.createElement('input')); //创建value显示框
		input.attr("class", "input-text valid");
		input.attr("name", name + "view");
		input.attr("value", text);
		input.attr("disabled", true);
		div2.append(input);

		var div3 = $(document.createElement('div'));
		div3.attr("class", "formControls col-xs-8 col-sm-1");
		div1.append(div3);

		var input = $(document.createElement('input')); //创建删除按钮
		input.attr("class", "btn btn-primary radius");
		input.attr("onclick", "delElement(this)");
		input.attr("value", "删除");
		input.attr("type", "button");
		div3.append(input);

		return div1;
	}

	function getProducerInfo(data) {
		// producerType
		for (i = 0; i < data.producerType.length; i++) {
			var div1 = getElement("producerTypeSelect",
					data.producerType[i].code, data.producerType[i].name);
			$("#producerTypeSelect").parent().parent().parent().after($(div1));
		}
	}

	function delElement(obj) {
		$(obj).parent().parent().remove();
	}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
