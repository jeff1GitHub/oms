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
    <span class="c-gray en">&gt;</span> 线路管理
    <span class="c-gray en">&gt;</span> 客服任务
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form id="form-search">
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="workStatus" name="workStatus" class="select">
                        <option value="">状态</option>
                        <c:forEach var="item" items="${workStatusSelect}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="platform" name="platform" class="select">
                        <option value="">所属平台</option>
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="faultType" name="faultType" class="select">
                        <option value="">故障标签</option>
                        <c:forEach var="item" items="${faultType}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="priority" name="priority" class="select">
                        <option value="">优先级</option>
                        <c:forEach var="item" items="${priority}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="level" name="level" class="select">
                        <option value="">客户级别</option>
                        <c:forEach var="item" items="${level}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="search" name="search" class="input-text" style="width:250px" placeholder="输入站点编号/别名/提交人">
            </div>
            <input onfocus="WdatePicker({ endTime:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="startTime" name="startTime" class="input-text Wdate" style="width:180px;">-
            <input onfocus="WdatePicker({ startTime:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="endTime" name="endTime" class="input-text Wdate" style="width:180px;">
            <button type="button" class="btn btn-success" id="searchBtn" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <c:if test="${role==1}">
            <a id="addWorkBtn" href="javascript:;" onclick="serverromm_edit('添加工单','work-server-info')" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加工单</a>
            </c:if>
        </span>
    </div>
    <table id="workList" class="table table-border table-bordered table-bg">
        <thead>
        <tr><th scope="col" colspan="17">工单列表</th></tr>
        <tr class="text-c">
            <th></th>
            <th>序号</th>
            <th>问题单号</th>
            <th>故障标签</th>
            <th>优先级</th>
            <th>客户级别</th>
            <th>状态</th>
            <th>所属平台</th>
            <th>站点编号</th>
            <th>站点别名</th>
            <th>提交人</th>
            <th>提交时间</th>
            <th>接收人</th>
            <th>接收时间</th>
            <th>修改人</th>
            <th>完成时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    // 表单中字段的key
    var role = ${role};
    var workStatus = ${workStatus};
    
    if(workStatus == 1){
    	$("#workStatus").val(1);
    } else if(workStatus == 3){
    	$("#workStatus").val(3);
    }
    
    var columns = [
        {data : function(e) {return "<input name='divertCheckbox' type='checkbox' value='"+e.id+"' />";}},
        {data : 'id'},
        {data : function (e) {return "YWXT-" + e.id}},
        {data : function (e) {
            if(e.faultType != null){
                return e.faultType.name;
            }else {
                return "";
            }
        }},
        {data : function (e) {
            if(e.priority != null){
                return e.priority.name;
            }else{
                return "";
            }
        }},
        {data : function (e) {
            if(e.level != null){
                return e.level.name;
            }else{
                return "";
            }
        }},
        {data : function (e) {
            if(e.status != null){
                return e.status.name;
            }else{
                return "";
            }
        }},
        {data : function (e) {
            if(e.platform != null){
                return e.platform.name;
            }else{
                return "";
            }
        }},
        {data : 'stationName'},
        {data : 'aliasName'},
        {data : 'createUser'},
        {data : 'createTime'},
        {data : 'receiveUser'},
        {data : 'receiveTime'},
        {data : 'updateUser'},
        {data : 'finishTime'},
        {
            data: function (e) {
                var element = datatable_button_edit_full("work-server-info", e.id, "&role=" + role);
                return element;
            }
        }
    ];

    /**
     * 提交查询信息
     
    $("#form-search").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: "work",
                success: function (result) {
                    if (result.code == 200) {
                    	bindJsonTable("workList", result.data, columns);
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
    
    $("#form-search").submit();*/

    /*机房-编辑*/
    function serverromm_edit(title, url) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*机房-删除*/
    function serverromm_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'DELETE',
                url: 'serverroom/' + id,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        $("#form-serverroom-search").submit();
                    } else {
                        layer.msg(result.message, {icon: 1, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('error!', {icon: 1, time: 1000});
                },
            });
        });
    }
    
    $(function () {
        //提示信息  初始化设置 一般不需要改
		var language = {// 语言设置
		    "sProcessing": "处理中...",
		    "sLengthMenu": "显示 _MENU_ 项结果",
		    "sZeroRecords": "没有匹配结果",
		    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
		    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
		    "sInfoPostFix": "",
		    "sSearch": "搜索:",
		    "sUrl": "",
		    "sEmptyTable": "表中数据为空",
		    "sLoadingRecords": "载入中...",
		    "sInfoThousands": ",",
		    "oPaginate": {
		        "sFirst": "首页",
		        "sPrevious": "上页",
		        "sNext": "下页",
		        "sLast": "末页"
		    }
		};
        
        function doSearch(){
        	$("#workList").DataTable().destroy();
        	//重要修改 表格内容的自定义，需要根据业务定制
            $("#workList").dataTable({
                language: language,  //提示信息
                autoWidth: true,  //禁用自动调整列宽
                stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
                processing: false,  //隐藏加载提示,自行处理
                serverSide: true,  //启用服务器端分页
                searching: false,  //禁用原生搜索
                orderMulti: false,  //启用多列排序
                order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
                renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
                pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
                pageLength: 20,
                lengthChange: false,
                ordering: false,
                columnDefs: [{
                    "targets": 'nosort',  //列的样式名
                    "orderable": false    //包含上样式名‘nosort’的禁止排序
                }],
                ajax: function (data, callback, settings) {

                    //封装请求参数
                    var param = {};
                    param.pageSize= data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                    param.startIndex = data.start;//开始的记录序号
                    param.pageIndex = data.start/data.length;//当前页码
                    
                    param.workStatus = $("#workStatus").val();
                    param.platform = $("#platform").val();
                    param.faultType = $("#faultType").val();
                    param.priority = $("#priority").val();
                    param.level = $("#level").val();
                    param.startTime = $("#startTime").val();
                    param.endTime = $("#endTime").val();

                    //ajax请求数据方法
                    $.ajax({
                        type: "GET",
                        url: "work",//url请求的地址
                        cache: false,  //禁用缓存
                        data: param,  //传入组装的参数
                        dataType: "json",
                        success: function (result) {
                            //封装返回数据重要
                            var returnData = {};
                            //这里直接自行返回了draw计数器,应该由后台返回
                            returnData.draw = data.draw;
                            //返回数据全部记录
                            returnData.recordsTotal = result.others.totalCount;
                            //后台不实现过滤功能，每次查询均视作全部结果
                            returnData.recordsFiltered = result.others.totalCount;
                            //返回的数据列表
                            returnData.data = result.data;

                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                            callback(returnData);
                        }
                    });
                },
                //列表表头字段
                columns : columns,
                //新建列的 数据内容
                "createdRow": function ( row, data, index ) {
                    //行渲染回调,在这里可以对该行dom元素进行任何操作
                }
            }).api();
            //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        }
        
        doSearch();
        
        $("#searchBtn").click(function(){
        	doSearch();
        });
    });
    
</script>
</body>
</html>