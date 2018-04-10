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
    <span class="c-gray en">&gt;</span> 站点资料

    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="station">
            <select id="platform" name="platform" class="input-text" style="width:180px;">
                <option value="">所属平台</option>
                <c:forEach var="item" items="${platform}">
                    <option value="${item.code }">${item.name }</option>
                </c:forEach>
            </select>
            <select id="level" name="level" class="input-text" style="width:180px;">
                <option value="">客户级别</option>
                <c:forEach var="item" items="${level}">
                    <option value="${item.code }">${item.name }</option>
                </c:forEach>
            </select>
            <select id="type" name="type" class="input-text" style="width:180px;">
                <option value="">主站/子站</option>
                <c:forEach var="item" items="${type}">
                    <option value="${item.code }">${item.name }</option>
                </c:forEach>
            </select>
            <input type="text" id="code" name="code" class="input-text" style="width:180px;" placeholder="输入站点编号">
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_full('添加站点','station-info')" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加站点
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="11">站点列表</th></tr>
        <tr class="text-c">
            <th width="70">序号</th>
            <th>所属平台</th>
            <th>站点编号</th>
            <th>主站/子站</th>
            <th>客户级别</th>
            <th>启禁用状态</th>
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
        {data: 'code'},
        {data: function (e) {
            if(e.type != null){
                return e.type.name;
            }else {
                return "";
            }
        }},
        {data: function (e) {
            var ret = "";
            if(e.level != null){
                ret = e.level.name;
            }
            return ret ;
        }},
        {data: function (e) {
            var element = "";
            if (e.isDisable === '否') {
                element = '<span class="label label-success radius">启用</span>';
            } else {
                element = '<span class="label label-danger radius">停用</span>';
            }
            return element;
        },
        className: "td-status"
    	},
        {
            "data": function (e) {
                var element = "<a style=\"text-decoration:none\" onClick=\"stationDisable(this,'" + e.id + "','" + e.isDisable + "')\" href=\"javascript:;\" title=\"启用\">"
                + "<i class=\"Hui-iconfont\">&#xe615;</i></a>&nbsp;"
                + datatable_button_edit_full("station-info", e.id, '');
              //element += datatable_button_del("station", e.id);
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
    
    /*站点-启用/禁用*/
    function stationDisable(obj, id, isDisable) {
    	var code = 0
    	var text1 = "";
    	var text2 = "";
    	if(isDisable === "否"){
    		text1 = "确认要停用吗？";
    		text2 = "已停用!";
    		code = 1;
    	} else {
    		text1 = "确认要启用吗？";
    		text2 = "已启用!";
    		code = 0;
    	}
        layer.confirm(text1, function (index) {
            //此处请求后台程序，下方是成功后的前台处理……
        	$.ajax({
                type: 'POST',
                url: 'station/disable?stationId=' + id + "&isDisable=" + code ,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 200) {
                        $("#form-search").submit();
                        layer.msg(result.message, {icon: 1, time: 1000});
                    } else {
                        layer.msg(text2, {icon: 1, time: 1000});
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });

            //$(obj).remove();
            //layer.msg(text2, {icon: 6, time: 1000});
        });
    }
</script>
</body>
</html>