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
    <span class="c-gray en">&gt;</span> 报表中心
    <span class="c-gray en">&gt;</span> 平台问题报表
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-report-search">
            <input type="text"  id="startTime" name="startTime" class="input-text Wdate" style="width:180px;" >-
            <input type="text"  id="endTime" name="endTime" class="input-text Wdate" style="width:180px;" >
            <button type="button" class="btn btn-default" onclick="changeEndTime(1)">今天</button>
            <button type="button" class="btn btn-default" onclick="changeEndTime(2)">近7天</button>
            <button type="button" class="btn btn-default" onclick="changeEndTime(3)">近1个月</button>
            <button type="button" class="btn btn-default" onclick="changeEndTime(4)">近3个月</button>

            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
        </span>
    </div>
    <table id="reportList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="13">报表信息</th></tr>
        <tr class="text-c">
            <th>平台</th>
            <th>站点总数</th>
            <th>问题总数</th>
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
            data : function(e) {
                var ret = "";
                if(e.platform == 1){
                    ret = ret + "BWT1";
                }else if(e.platform == 2){
                    ret = ret + "BWT2";
                }else if(e.platform == 3){
                    ret = ret + "BWT3";
                }else if(e.platform == 4){
                    ret = ret + "OG";
                }else if(e.platform == 5){
                    ret = ret + "ACG";
                }
                return ret;
            }
        },
        {data : 'stationnum'},
        {data : 'tasknum'},
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
    $("#form-report-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: 'report-platform',
                success: function (jsonResult) {
                    if (jsonResult.code == 200) {
                        bindJsonTable("reportList", jsonResult.data, columns);
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

function changeEndTime(type) {
    if(type == 1){
        $("#startTime").val($.myTime.getToDay());
    }else if(type == 2){
        $("#startTime").val($.myTime.get7Day());
    }else if(type == 3){
        $("#startTime").val($.myTime.get1Month());
    }else if(type == 4){
        $("#startTime").val($.myTime.get3Month());
    }
}

$(function(){
    $("#startTime").val($.myTime.get7Day());
    $("#endTime").val($.myTime.getToDay());


    $("#form-report-search").submit();
})
</script>
</body>
</html>