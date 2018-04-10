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
    <span class="c-gray en">&gt;</span> 用户组列表
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c"><form id="form-group-search"></form></div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="group_add('添加管理员','group-info','800','500')" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i>添加用户组
            </a>
        </span>
    </div>
    <table id="groupList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="9">员工列表</th></tr>
        <tr class="text-c">
            <th>序号</th>
            <th>组名称</th>
            <th>成员数</th>
            <th>备注</th>
            <th>状态</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="javascript/group-list.js"></script>
</body>
</html>