<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/18 0018
  Time: 上午 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-server-deployment-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select">
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>环境：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="environment" name="environment" class="select">
                        <c:forEach var="item" items="${environment}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>项目名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="project" name="project" class="select">
                        <c:forEach var="item" items="${project}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>工程名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="service" name="service" class="select">
                        <c:forEach var="item" items="${service}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>工程编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="" id="projectName" name="projectName">
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>占用端口：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" placeholder="" name="port" id="port">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">部署IP：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <textarea id="deployedIp" name="deployedIp" cols="" rows="" class="textarea" dragonfly="true" placeholder="请输入部署ip"></textarea>
            </div>
            <label class="form-label col-xs-4 col-sm-3">服务器：</label>
            <div class="formControls col-xs-8 col-sm-3">
            	<input id="server" name="server" type="hidden"/>
                <input type="text" class="input-text" placeholder="" name="serverName" id="serverName" disabled=true>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">测试方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="test" name="test" cols="" rows="" class="textarea" dragonfly="true" placeholder="说点什么...100个字符以内"></textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        /* 通过获取id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        
        $("#server").val(id);
        // 没有获取到id参数,将提交方式改为新增
        
        $.request.get("server/" + id, {}, function (result) {
            if (result.code == 200) {
                var data = result.data;
                //$("#id").val(data.id);
                //$("#platform").val(data.platform.code);
                //$("#environment").val(data.environment.code);
                //$("#project").val(data.project);
                //$("#service").val(data.service);
                //$("#projectName").val(data.projectName);
                //$("#port").val(data.port);
                $("#serverName").val(data.code);
                //$("#test").val(data.test);
            } else {
                layer.msg(result.message, {icon: 1, time: 1000});
            }
        }, null);

        $("#form-server-deployment-add").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: "put",
                    url: "deployment",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg(result.message, {icon: 1, time: 1000});
                            parent.$("#form-search").submit();
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
</script>
</body>
</html>
