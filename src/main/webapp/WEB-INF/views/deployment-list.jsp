<%--
  网设列表页面
  Created by IntelliJ IDEA.
  User: jeff
  Date: 2017/10/16 0016
  Time: 上午 7:28
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
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span> 信息整合
    <span class="c-gray en">&gt;</span> 服务部署资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="deployment">
            <select id="platform" name="platform" class="input-text" style="width:120px;">
                <option value="">选择平台</option>
                <c:forEach var="item" items="${platform}"><option value="${item.code }">${item.name }</option></c:forEach>
            </select>
            <select id="environment" name="environment" class="input-text" style="width:120px;">
                <option value="">选择环境</option>
                <c:forEach var="item" items="${environment}"><option value="${item.code }">${item.name }</option></c:forEach>
            </select>
            <select id="project" name="project" class="input-text" style="width:120px;">
                <option value="">选择项目</option>
                <c:forEach var="item" items="${project}"><option value="${item.code }">${item.name }</option></c:forEach>
            </select>
            <select id="service" name="service" class="input-text" style="width:120px;">
                <option value="">选择工程</option>
                <c:forEach var="item" items="${service}"><option value="${item.code }">${item.name }</option></c:forEach>
            </select>
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
        </form>
    </div>
     <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_open('添加服务部署信息','deployment-info', 800, 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加服务信息
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="11">服务列表</th></tr>
        <tr class="text-c">
            <th width="70">服务ID</th>
            <th>平台</th>
            <th>环境</th>
            <th>项目名</th>
            <th>工程名</th>
            <th>工程编号</th>
            <th>占用端口</th>
            <th>部署位置</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    // 表单中字段的key
    var columns = [
        {data: 'id'},
        {data: function (e) {
            if(e.platform != null){
                return e.platform.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            if(e.environment != null){
                return e.environment.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            if(e.project != null){
                return e.project.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            if(e.service != null){
                return e.service.name;
            }else {
                return "";
            }
        }},
        {data: 'projectName'},
        {data: 'port'},
        {data: 'server'},
        {
            "data": function (e) {
                var element = datatable_button_edit("编辑服务部署信息", "deployment-info", e.id);
                element += datatable_button_del("deployment", e.id);
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>