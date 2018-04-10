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
    <span class="c-gray en">&gt;</span> 供应商资料

    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search" action="producer">

        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="window_open('添加供应商','producer-info', 800 , 500)" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加供应商
            </a>
        </span>
    </div>
    <table id="datatable" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="15">供应商列表</th></tr>
        <tr class="text-c">
            <th width="70">供应商ID</th>
            <th>名称</th>
            <th>角色</th>
            <th>公司全称</th>
            <th>签章人</th>
            <th>支付宝帐号</th>
            <th>银行类型</th>
            <th>银行卡帐号</th>
            <th>联系人</th>
            <th>联系方式</th>
            <th>联系地址</th>
            <th>登录域名</th>
            <th>登录帐号</th>
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
        {data : 'id'},
        {data : 'name'},
        {data : 'producerTypeInfos'},
        {data : 'companyname'},
        {data : 'signatureperson'},
        {data : 'alipay'},
        {data : function (e) {
            if(e.banktype != null){
                return e.banktype;
            }else {
                return "";
            }
        }},
        {data : 'bankaccount'},
        {data : 'linkmanname'},
        {data : function (e) {
            if(e.message == null||e.message == "" || e.type == null || e.type == ""){
                return "";
            }
            return e.type + ":" + e.message;
        }},
        {data : 'addr'},
        {data : 'logindomain'},
        {data : 'loginaccount'},
        {
            "data": function (e) {
                var element = datatable_button_edit("编辑供应商", "producer-info", e.id);
//                element += datatable_button_add("增加联系人", "linkman-list", e.id);
                element += button_button("设置联系人", "&#xe62d;", "window_full('设置联系人|"+e.id+"', 'linkman-list')");
                element += datatable_button_del("producer", e.id);
                return element;
            }
        }
    ];

    form_search_validate();

    $("#form-search").submit();
</script>
</body>
</html>