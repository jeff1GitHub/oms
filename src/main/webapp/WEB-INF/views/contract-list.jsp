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
    <span class="c-gray en">&gt;</span> 信息整合
    <span class="c-gray en">&gt;</span> 合同资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c"><form id="form-search" action="contract"></form></div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_open('添加合同','contract-info', 800, 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加合同
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="10">合同列表</th></tr>
        <tr class="text-c">
            <th>合同ID</th>
            <th>合同类型</th>
            <th>合同保存路径</th>
            <th>合同签署日期</th>
            <th>合同终止日期</th>
            <th>供应商</th>
            <th>签章公</th>
            <th>签章名称</th>
            <th>备注</th>
            <th>操作</th>
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
        {data: 'type.name'},
        {data: 'path'},
        {data: 'startTime'},
        {data: 'endTime'},
        {data: function (e) {
            if(e.producer != null){
                return e.producer.name;
            }else {
                return "";
            }
        }},
        {data: 'signatureCompany'},
        {data: 'signaturePerson'},
        {data: 'remark'},
        {
            "data": function (e) {
                var element = datatable_button_edit("编辑合同", "contract-info", e.id);
                element += datatable_button_del("contract", e.id);
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>