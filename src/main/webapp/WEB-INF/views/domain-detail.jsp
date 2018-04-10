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
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="domainDetail">
        	<input id="domainId" name="domainId" type="hidden" >
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="13">域名资料详情</th></tr>
        <tr class="text-c">
            <th width="70">序号</th>
            <th>主机</th>
            <th>解析类型</th>
            <th>解析地址</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script>
    // 表单中字段的key
    var columns = [
        {data: 'id'},
        {data: 'domain'},
        {data: 'analyzeType'},
        {data: 'analyzeAddr'},
        {data: 'status'}
    ];

    form_search_validate();
    
    //获取域名id
    var temp = $(".layui-layer-title", parent.document).text().split("|");
    $(".layui-layer-title", parent.document).text(temp[0]);
    $("#domainId").val(temp[1]);

    $("#form-search").submit();
</script>
</body>
</html>