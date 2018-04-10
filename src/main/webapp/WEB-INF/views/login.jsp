<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/13 0013
  Time: 上午 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <%@include file="include/head.html" %>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" id="user-login">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="account" name="account" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online"><input type="checkbox" name="online" id="online" value="">记住密码</label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-5">
                    <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright 运维管理系统  by oms v2.0</div>

<script type="text/javascript">
    $("#user-login").validate({
        rules: {
            account: {
                required: true,
                maxlength: 30,
            },
            password: {
                required: true,
                maxlength: 30,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: 'post',
                url: "user-login",
                success: function (result) {
                    if (result.code == 200) {
                        $(location).attr('href', '/');
                        layer.msg('登录成功!', {icon: 1, time: 1000});
                    } else {
                        layer.msg(result.message, {icon: 2, time: 2000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 5, time: 1000});
                }
            });
        }
    });
</script>
</body>
</html>
