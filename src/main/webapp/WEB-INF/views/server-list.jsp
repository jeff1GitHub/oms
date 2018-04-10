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
    <span class="c-gray en">&gt;</span> 服务器资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="server">
        	<label>连接地址：</label>
            <input class="input-text" id="linkAddr" name="linkAddr" type="text" style="width:180px;"/>
        	<label>服务器：</label>
        	<input id="serverId" name="serverId" type="hidden"/>
            <input class="input-text" id="serverCode" name="serverCode" type="text" list="serverList" style="width:160px;"/>
            <datalist id="serverList">
		        <c:forEach var="item" items="${serverList}"><option label="${item.id }" value="${item.code }"></option></c:forEach>
		    </datalist>&nbsp;&nbsp;
            <label>到期时间：</label>
            <input id="startDay" name="startDay" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd'})" class="input-text Wdate" style="width:160px;" placeholder="选择购置时间">
            <label>至：</label>
            <input id="endDay" name="endDay" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd'})" class="input-text Wdate" style="width:160px;" placeholder="选择到期时间">
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_full('添加服务器','server-info', 800, 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加服务器
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="11">服务器列表</th></tr>
        <tr class="text-c">
            <th width="70">编号</th>
            <th>平台</th>
            <th>地区</th>
            <th>机房</th>
            <th>连接类型</th>
            <th>连接地址</th>
            <th>连接端口</th>
            <th>宿主机</th>
            <th>序列号</th>
            <th>带宽</th>
            <th width="100">操作</th>
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
        {data: function (e) {
            if(e.platform != null){
                return e.platform.name;
            }else {
                return "";
            }
        }},
        {data: 'area'},
        {data: function (e) {
            if(e.serverroom != null){
                return e.serverroom.name;
            }else {
                return "";
            }
        }},
        {data: 'linkType'},
        {data: 'linkAddr'},
        {data: 'linkPort'},
        {data: 'host'},
        {data: 'series'},
        {data: 'bandwidth'},
        {
            "data": function (e) {
            	var element = datatable_button_edit_full("server-info", e.id, '');
            	element += datatable_button_del("server", e.id);
            	//element += datatable_button_edit_icon("增加部署信息", "server-deployment-info", e.id, "&#xe600;");
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>