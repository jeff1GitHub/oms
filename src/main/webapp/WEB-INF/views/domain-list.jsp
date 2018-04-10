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
    <span class="c-gray en">&gt;</span> 域名资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="domainmain">
            <span class="select-box" style="width:150px">
                <select id="purpose" name="purpose" class="select">
                    <option value="">域名用途</option>
                    <c:forEach var="item" items="${purpose}">
                        <option value="${item.code }">${item.name }</option>
                    </c:forEach>
                </select>
            </span>
            <span class="select-box" style="width:150px">
                <select id="platform" name="platform" class="select">
                    <option value="">所属平台</option>
                    <c:forEach var="item" items="${platform}">
                        <option value="${item.code }">${item.name }</option>
                    </c:forEach>
                </select>
            </span>
            <input id="domain" name="domain" class="input-text" style="width:250px" placeholder="输入站点主域">
            <button type="submit" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_open('添加域名','domain-info', 800, 800)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加域名
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="12">域名列表</th></tr>
        <tr class="text-c">
            <th width="70">编号</th>
            <th>主域</th>
            <th>平台</th>
            <th>公网HTTP证书</th>
            <th>带隐私保护</th>
            <th>域名用途</th>
            <th>供应商网站(注册)</th>
            <th>供应商帐号(注册)</th>
            <th>供应商网站(解析)</th>
            <th>供应商帐号(解析)</th>
            <th>到期时间</th>
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
        {data: 'domain'},
        {data: function (e) {
            if(e.platform != null){
                return e.platform.name;
            }else {
                return "";
            }
        }},
        {data: 'https'},
        {data: 'privacy'},
        {data: function (e) {
            if(e.purpose != null){
                return e.purpose.name;
            }else {
                return "";
            }
        }},
        {data: 'regeditWeb'},
        {data: 'regeditAccount'},
        {data: 'resolutionWeb'},
        {data: 'resolutionAccount'},
        {data: 'endTime'},
        {
            "data": function (e) {
                //var element = datatable_button_edit(, "domain-info", e.id);
                var element = button_button('编辑域名', "&#xe6df;", "window_open('编辑域名', 'domain-info?id=" + e.id + "', 800, 800)");
                element += datatable_button_del("domainmain", e.id);
                element += button_button("域名资料详情", "&#xe687;", "window_full('域名资料详情|"+e.id+"', 'domain-detail')");
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>