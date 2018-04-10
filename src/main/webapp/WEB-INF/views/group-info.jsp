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
    <form class="form form-horizontal" id="form-group-add">
    	<input type="hidden" id="id" name="id" value=""/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>归属权限组：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="parentId" name="parentId" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${parentGroup}">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限组名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" type="text" class="input-text" value="" placeholder="输入权限组名称">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">权限组描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="remark" name="remark" cols="" rows="" class="textarea" dragonfly="true"
                          placeholder="说点什么...100个字符以内"></textarea>
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

	var requestType = "post";
    $(function(){
    	
    	/* 通过获取机房id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("group/" + id, {}, function (result) {
                if (result.code == 200) {
                	$("#id").val(result.data.id);
                	$("#parentId").val(result.data.parentId);
                	$("#name").val(result.data.name);
                	$("#remark").val(result.data.remark);

                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-group-add").validate({
            rules:{
                parentId:{
                    required:true,
                },
                name:{
                    required:true,
                },
                remark:{
                    required:true,
                },
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "group" ,
                    success: function(result){
                        if (result.code == 200) {
                            layer.msg('操作成功!', {icon: 1, time: 1000});
                            parent.$("#form-group-search").submit();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.msg(result.message, {icon: 2, time: 1000});
                        }
                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                    	layer.msg(result.message, {icon: 2, time: 1000});
                    }
                });
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.$('.btn-refresh').click();
//                parent.layer.close(index);
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
