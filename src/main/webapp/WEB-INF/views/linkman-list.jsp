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
        <form id="form-search" action="linkman">
        	<input id="producerId" name="producerId" type="hidden" >
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" id="addLinkmanBtn" onclick="window_open('添加联系人','linkman-info', 800, 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加联系人
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="13">联系人列表</th></tr>
        <tr class="text-c">
            <th width="70">序号</th>
            <th>联系人名称</th>
            <th>供应商</th>
            <th>默认联系方式</th>
            <th>地址</th>
            <%--<th>QQ</th>--%>
            <%--<th>Email</th>--%>
            <%--<th>手机</th>--%>
            <%--<th>固话</th>--%>
            <%--<th>微信</th>--%>
            <%--<th>TG</th>--%>
            <%--<th>Skype</th>--%>
            <%--<th>whatapp</th>--%>
            <th width="70">操作</th>
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
        {data: 'name'},
        {data: function (e) {
            if(e.prodoucer != null){
                return e.prodoucer.name;
            }else {
                return "";
            }
        }},
        {data: 'defaultContactInfo'},
        {data: 'addr'},
//        {data: 'qq'},
//        {data: 'email'},
//        {data: 'phone'},
//        {data: 'mob'},
//        {data: 'vchat'},
//        {data: 'tg'},
//        {data: 'skype'},
//        {data: 'whatapp'},

        {
            "data": function (e) {
                var element = datatable_button_edit("编辑联系人", "linkman-info", e.id);
                element += datatable_button_del("linkman", e.id);
                return element;
            }
        }
    ];

    form_search_validate();
    
    //曲线救国
    //<div class="layui-layer-title" style="cursor: move;" move="ok">设置联系人|10</div>
    var temp = $(".layui-layer-title", parent.document).text().split("|");
    $(".layui-layer-title", parent.document).text(temp[0]);
    $("#producerId").val(temp[1]);
    $("#addLinkmanBtn").attr("onclick", "window_open('添加联系人','linkman-info?linkmanId="+ temp[1] +"', 800, 500)");
    

    $("#form-search").submit();
</script>
</body>
</html>