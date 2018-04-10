<%--
  网设列表页面
  Created by IntelliJ IDEA.
  User: jeff
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
    <span class="c-gray en">&gt;</span> 信息整合
    <span class="c-gray en">&gt;</span> 线路资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="network"></form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_open('添加线路','network-info', 800, 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加线路
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="13">线路列表</th>
        </tr>
        <tr class="text-c">
            <th width="70">线路ID</th>
            <th>平台</th>
            <th>线路品牌</th>
            <th>线路类型</th>
            <th>带宽大小</th>
            <th>ip段</th>
            <th>线路作用范围</th>
            <th>合同编号</th>
            <th>供应商</th>
            <th>落地机房</th>
            <th>线路购置时间</th>
            <th>线路到期时间</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="javascript/public.js"></script>
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
            if(e.brand != null){
                return e.brand.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            if(e.type != null){
                return e.type.name;
            }else {
                return "";
            }
        }},
        {data: 'bandwidth'},
        {data: 'ip'},
        {data: 'range'},
        {data: 'contract'},
        {data: function (e) {
            if(e.producer != null){
                return e.producer.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            if(e.serverroom != null){
                return e.serverroom.name;
            }else {
                return "";
            }
        }},
        {data: 'startTime'},
        {data: 'endTime'},
        {
            "data": function (e) {

                var element = datatable_button_edit("编辑线路", "network-info", e.id);
                element += datatable_button_del("network", e.id);
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>