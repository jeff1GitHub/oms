<%--
  Created by IntelliJ IDEA.
  User: Administrator
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
    <span class="c-gray en">&gt;</span> 权限控制
    <span class="c-gray en">&gt;</span> 操作日志
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-log-list">
       		<label>账号：</label>
        	<input id="account" name="account" type="text" placeholder="输入帐号" class="input-text" style="width:180px;">
        	<select id="logType" name="logType" class="input-text" style="width:180px;">
                <option value="">日志类型</option>
                <c:forEach var="item" items="${logType}">
                    <option value="${item.code }">${item.name }</option>
                </c:forEach>
            </select>
            <label>操作时间：</label>
            <input id="startDay" name="startDay" onfocus="WdatePicker({})" class="input-text Wdate" style="width:160px;" placeholder="选择时间">
            <label>至：</label>
            <input id="endDay" name="endDay" onfocus="WdatePicker({})" class="input-text Wdate" style="width:160px;" placeholder="选择时间">
            
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="9">登录日志列表</th></tr>
        <tr class="text-c">
            <th width="5%">序号</th>
            <th>用户编号</th>
            <th>账号</th>
            <th>操作类型</th>
            <th>操作内容</th>
            <th>操作时间</th>
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
        {data : function (e) {
            if(e.logType != null){
                return e.logType.name;
            }else {
                return "";
            }
        }},
        {data : 'context'},
        {data : 'createTime'}
    ];
    
    $("#form-log-list").validate({
        submitHandler:function(form){
            $(form).ajaxSubmit({
                url: "operLog" ,
                success: function(jsonResult){
                    if(jsonResult.code == 200){
                        bindJsonTable("datatable", jsonResult.data, columns);
                    }else{
                        layer.msg(jsonResult.message,{icon:2,time:2000});
                    }
                },
                error: function(XmlHttpRequest, textStatus, errorThrown){
                    layer.msg('error!',{icon:2,time:1000});
                }
            });
        }
    });

    $("#form-log-list").submit();
</script>
</body>
</html>