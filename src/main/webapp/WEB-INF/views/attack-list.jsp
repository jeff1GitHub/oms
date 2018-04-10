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
    <span class="c-gray en">&gt;</span> 运维部署
    <span class="c-gray en">&gt;</span> 攻击防御
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search">
            <div class="formControls col-xs-6 col-sm-1">
                <span class="select-box">
                    <select id="platform" name="platform" class="select">
                        <option value="">所属平台</option>
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-6 col-sm-1">
                <span class="select-box">
                    <select id="level" name="level" class="select">
                        <option value="">客户级别</option>
                        <c:forEach var="item" items="${level}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-6 col-sm-1">
                <input id="search" name="search" class="input-text" style="width:250px" placeholder="输入站点编号/别名/提交人">
            </div>
            <label>被攻击时间：</label>
            <input onfocus="WdatePicker({ endTime:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="startTime" name="startTime" class="input-text Wdate" style="width:180px;">至
            <input onfocus="WdatePicker({ startTime:'#F{$dp.$D(\'datemin\')}'})" id="endTime" name="endTime" class="input-text Wdate" style="width:180px;">
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="serverromm_edit('添加防御','attack-info')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
        </span>
    </div>
    <table id="attackList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="17">防御列表</th></tr>
        <tr class="text-c">
            <th>序号</th>
             <th>别名</th>
             <th>所属平台</th>
             <th>客户级别</th>
             <th>站点编号</th>
             <th>最新被攻击域</th>
             <th>最新被攻击时间</th>
            <th>攻击总次数</th>
             <th>当前负责人</th>
             <th>高防天数</th>
             <th>高防服务器</th>
            <th>高防服务器IP</th>
             <th>备注</th>
            <th>操作</th>
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
        {data : 'alias'},
        {data : 'platform.name'},
        {data : 'level.name'},
        {data : 'stationCode'},
        {data : 'recentlyAttackedDomain'},
        {data : 'attackTime'},
        {data : 'attackNum'},
        {data : 'director'},
        {data : 'defenseNum'},
        {data : 'defenseServer'},
        {data : 'defenseIp'},
        {data : 'remark'},
        {
            data: function (e) {
                var element = datatable_button_edit_full("attack-info", e.id, '');
                return element;
            }
        }
    ];

    /**
     * 提交查询信息
     */
    $("#form-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: "attack",
                success: function (result) {
                    if (result.code == 200) {
                        bindJsonTable("attackList", result.data, columns);
                    } else {
                        layer.msg(result.message, {icon: 1, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
        }
    });

    $("#form-search").submit();

    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */

    /*机房-编辑*/
    function serverromm_edit(title, url) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

</script>
</body>
</html>