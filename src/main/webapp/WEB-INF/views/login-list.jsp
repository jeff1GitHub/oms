<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/16 0016
  Time: 上午 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span> 权限控制
    <span class="c-gray en">&gt;</span> 登录日志
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-group-search" method="get">
            <div class="text-c">
                <input id="account" name="account" type="text" placeholder="输入帐号" class="input-text" style="width:180px;">
                <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
            </div>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"></span>
    </div>
    <table id="loginLogList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="9">登录日志列表</th></tr>
        <tr class="text-c">
            <th width="10%">序号</th>
            <th>用户id</th>
            <th>账号</th>
            <th>登录时间</th>
            <th>ip</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script>
    // 表单中字段的key
    var columns = [
        {data : 'id'},
        {data : 'userId'},
        {data : 'userAccount'},
        {data : 'loginTime'},
        {data : 'ip'},
    ];

    /**
     * 提交查询信息
     */
    $("#form-group-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: "loginLog",
                success: function (jsonResult) {
                    if (jsonResult.code == 200) {
                        bindJsonTable("loginLogList", jsonResult.data, columns);
                    } else {
                        layer.msg(jsonResult.message, {icon: 1, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
        }
    });
</script>
</body>
</html>