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
    <span class="c-gray en">&gt;</span> 服务器资料
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-server-search"></form>
    </div>
    <table id="serverList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="11">服务器列表</th></tr>
        <tr class="text-c">
            <th>机房</th>
            <th>ping通打不开</th>
            <th>线路问题</th>
            <th>机房故障</th>
            <th>被攻击</th>
            <th>流量高</th>
            <th>地区问题</th>
            <th>掉包</th>
            <th>ping不通打的开</th>
            <th>其他</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script>
    // 表单中字段的key
    var columns = [
        {
            data: function (e) {

                if(e.server_room_id == 1){
                    ret = "机房名称";
                }else{
                    ret = "其他";
                }
                return ret;
            }},
        {data : 's1'},
        {data : 's2'},
        {data : 's3'},
        {data : 's4'},
        {data : 's5'},
        {data : 's6'},
        {data : 's7'},
        {data : 's8'},
        {data : 's9'},
    ];

    /**
     * 提交查询信息
     */
    $("#form-server-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: 'report-serverroom',
                success: function (jsonResult) {
                    if (jsonResult.code == 200) {
                        bindJsonTable("serverList", jsonResult.data, columns);
                    } else {
                        layer.msg(jsonResult.message, {icon: 1, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
        }
    });

    $("#form-server-search").submit();
</script>
</body>
</html>