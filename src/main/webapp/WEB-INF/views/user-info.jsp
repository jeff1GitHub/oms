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
    <form class="form form-horizontal" id="form-user-edit">
    	<input id="id" name="id" type="hidden" >
        <div class="row cl">
            

            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户账号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" placeholder="" id="account" name="account">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户组别：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="groupSelect" name="groupSelect" class="select">
                    	<option value="">---请选择---</option>
                        <c:forEach var="item" items="${groupTypes}">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>
				</span>
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'groupSelect', '请选择用户组别!');">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">

	var requestType = "POST";
	
    $(function(){
    	/* 通过获取网设id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("user/" + id, {}, function (result) {
                if (result.code = 200) {
                	
                    $("#id").val(result.data.id);
                    $("#account").val(result.data.account);
                    
                    getUserInfo(result.data);
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-user-edit").validate({
            rules:{
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "user" ,
                    success: function(data){
                        layer.msg('修改成功!',{icon:1,time:1000});
                        
                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('error!',{icon:1,time:1000});
                    }
                });
                var index = parent.layer.getFrameIndex(window.name);
                parent.$("#form-user-list").submit();
                parent.layer.close(index);
            }
        });
    });
    
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
    
    function getElement(name, val, text){
        var div1 = $(document.createElement('div'));  //创建最外层div节点
        div1.attr("class", "row cl");

        var label = $(document.createElement('label'));  //创建label节点
        label.attr("class", "form-label col-xs-4 col-sm-3");
        div1.append(label);

        var div2 = $(document.createElement('div'));
        div2.attr("class", "formControls col-xs-8 col-sm-3");
        div1.append(div2);

        var hidden = $(document.createElement('input'));  //创建code隐藏框
        hidden.attr("class", "input-text valid");
        hidden.attr("type", "hidden");
        hidden.attr("name", "groups");
        hidden.attr("value", val);
        div2.append(hidden);
        
        var input = $(document.createElement('input'));  //创建value显示框
        input.attr("class", "input-text valid");
        input.attr("name", name+"view");
        input.attr("value", text);
        input.attr("disabled", true);
        div2.append(input);

        var div3 = $(document.createElement('div'));
        div3.attr("class", "formControls col-xs-8 col-sm-1");
        div1.append(div3);

        var input = $(document.createElement('input'));  //创建删除按钮
        input.attr("class", "btn btn-primary radius");
        input.attr("onclick", "delElement(this)");
        input.attr("value", "删除");
        input.attr("type", "button");
        div3.append(input);

        return div1;
    }
    
    function getUserInfo(data) {
        // producerType
        for(i = 0; i < data.groups.length; i++){
            var div1 = getElement("groupSelect", data.groups[i].code, data.groups[i].name);
            $("#groupSelect").parent().parent().parent().after($(div1));
        }
    }
    
    function delElement(obj) {
        $(obj).parent().parent().remove();
    }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
