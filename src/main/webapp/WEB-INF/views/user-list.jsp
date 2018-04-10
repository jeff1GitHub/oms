<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 上午 6:10
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
    <span class="c-gray en">&gt;</span> 员工管理
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <form id="form-user-list">
        <div class="text-c">
            <select id="group" name="group" class="input-text" style="width:120px;">
                <option value="">选择权限组</option>
                <c:forEach var="item" items="${group}"><option value="${item.code }">${item.name }</option></c:forEach>
            </select>
            <select id="status" name="status" class="input-text" style="width:120px;">
                <option value="">是否禁用</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <input id="search" name="search" type="text" placeholder="输入帐号/ip" class="input-text" style="width:180px;">
            登录时间：
            <input id="datemin" name="datemin" type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" class="input-text Wdate" style="width:180px;">-
            <input id="datemax" name="datemax" type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:180px;">
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
        </div>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"></span></div>
    <table id="userList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="10">员工列表</th></tr>
        <tr class="text-c">
            <th>编号</th>
            <th>登录账号</th>
            <th>所属组别</th>
            <th>最后登录时间</th>
            <th>最后登录ip</th>
            <th>状态</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript">
    // 表单中字段的key
    var columns = [
        {data : 'id'},
        {data : 'account'},
        {data : 'groupInfos'},
        {data : 'lastLoginTime'},
        {data : 'ip'},
        {
            data: function (e) {
                var element = "";
                if (e.isDisable == 0) {
                    element = '<span class="label label-success radius">启用</span>';
                } else {
                    element = '<span class="label label-danger radius">禁用</span>';
                }
                return element;
            },
            className: "td-status"
        },
        {
            data: function (e) {
                var element = table_disable(e.id, e.isDisable);
                element += datatable_button_edit("编辑员工", "user-info", e.id);
//                var element = "<button type='button' class='btn btn-info'data-toggle='modal'"
//                    +	"data-target='#amendGroup' onclick='getUserInfo("+e.id+");'>查看</button>&nbsp;&nbsp;";
//                element = element + "<button type='button' class='btn btn-info' onclick='search();'>操作日志</button>";
                return element;
            },
            className: "td-manage"
        },
    ];

    /**
     * 提交查询信息
     */
        $("#form-user-list").validate({
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    url: "user" ,
                    success: function(jsonResult){
                        if(jsonResult.code == 200){
                            bindJsonTable("userList", jsonResult.data, columns);
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

    $("#form-user-list").submit();

    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }

    /*禁用按钮事件-停用*/
    function disable_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {
            $.post("user/" + id, {
                status: 1
            }, function (result) {
                if (result.code == 200) {
                    disable_stop_html(obj, id);
                    layer.msg('已停用!', {icon: 6, time: 1000});
                } else {
                    layer.msg(result.message, {icon: 5, time: 1000});
                }
            }, "json");
        });
    }

    /*禁用按钮事件-启用*/
    function disable_start(obj, id) {
        layer.confirm('确认要启用吗？', function (index) {
            $.post("user/" + id, {
                status: 0
            }, function (result) {
                if (result.code == 200) {
                    disable_start_html(obj, id);
                    layer.msg('已启用!', {icon: 6, time: 1000});
                } else {
                    layer.msg(result.message, {icon: 5, time: 1000});
                }
            }, "json");
        });
    }
</script>
</body>
</html>