<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/18 0018
  Time: 上午 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-contract-edit">
        <input id="id" name="id" type="hidden"/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select">
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>环境：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="environment" name="environment" class="select">
                        <c:forEach var="item" items="${environment}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>项目名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="project" name="project" class="select">
                        <c:forEach var="item" items="${project}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>工程名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="service" name="service" class="select">
                        <c:forEach var="item" items="${service}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>工程编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="" id="projectName" name="projectName">
            </div>
        </div>
       	<div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">部署IP：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <!-- <textarea id="deployedIp" name="deployedIp" cols="" rows="" class="textarea" dragonfly="true" placeholder="请输入部署ip"></textarea> -->
                <input type="text" class="input-text" placeholder="" id="deploymentIp">
            </div>
            <label class="form-label col-xs-4 col-sm-3">端口：</label>
            <div class="formControls col-xs-8 col-sm-2">
                <input type="text" class="input-text" placeholder="" id="ports">
            </div>
            <div class="formControls col-xs-8 col-sm-1">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'deploymentIp', 'ports', '请填写部署IP和端口!');">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">测试方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="test" name="test" cols="" rows="" class="textarea" dragonfly="true"
                          placeholder="说点什么...100个字符以内"></textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    var requestType = "post";
    $(function () {
        /* 通过获取id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("deployment/" + id, {}, function (result) {
                if (result.code == 200) {
                    var data = result.data;
                    $("#id").val(data.id);
                    $("#platform").val(data.platform.code);
                    $("#environment").val(data.environment.code);
                    $("#project").val(data.project.code);
                    $("#service").val(data.service.code);
                    $("#projectName").val(data.projectName);
                    $("#port").val(data.port);
                    $("#server").val(data.server);
                    $("#test").val(data.test);
                    $("#deployedIp").val(data.deployedIp);
                    $("#serverName").val(result.others.serverName);
                    
                    setIpInfos(result);
                    
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-contract-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "deployment",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg(result.message, {icon: 1, time: 1000});
                            parent.$("#form-search").submit();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
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
    });
    
  //addElement(this, 'role', '选择角色!')
    function addElement(obj, ipName, portName, error) {
    	var ipValue = $("#" + ipName).val();
    	var portValue = $("#" + portName).val();
    	
        if (ipValue == null || ipValue == "" || portValue == null || portValue == "") {
            layer.msg(error, {icon: 5, time: 1000});
            return;
        }
        $("#" + ipName).val("");
        $("#" + portName).val("");

     	// 填充serverName value
        $.ajax({
        	type: "get",
        	url:"ip",
        	data: {ip: ipValue},
        	success: function (result) {
                if (result.code == 200) {
                	var div1 = getElement(ipName, portName, ipValue, portValue, result.data);
                    $(obj).parent().parent().after($(div1));
                } else {
                    layer.msg(result.message, {icon: 5, time: 1000});
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                layer.msg('error!', {icon: 2, time: 1000});
                return;
            }
        });
        
        
    }
    
    function getElement(ipName, portName, ipValue, portValue, serverName){
        var div1 = $(document.createElement('div'));  //创建最外层div节点
        div1.attr("class", "row cl");
        
        var label1 = $(document.createElement('label'));  //创建label节点
        label1.attr("class", "form-label col-xs-4 col-sm-2");
        div1.append(label1);
        
        var div2 = $(document.createElement('div'));  //创建div
        div2.attr("class", "formControls col-xs-8 col-sm-3");
        div1.append(div2);
        
        var div3 = $(document.createElement('div'));  //创建div
        div3.attr("class", "formControls col-xs-8 col-sm-2");
        div1.append(div3);
        
        var div4 = $(document.createElement('div'));  //创建div
        div4.attr("class", "formControls col-xs-8 col-sm-3");
        div1.append(div4);

        var input1 = $(document.createElement('input'));  //创建value显示框
        input1.attr("class", "input-text valid");
        input1.attr("name", ipName);
        input1.attr("value", ipValue);
        input1.attr("readonly", true);
        input1.attr("style", "background-color: #CCCCCC");
        div2.append(input1);
        
        var input2 = $(document.createElement('input'));  //创建value显示框
        input2.attr("class", "input-text valid");
        input2.attr("name", portName);
        input2.attr("value", portValue);
        input2.attr("readonly", true);
        input2.attr("style", "background-color: #CCCCCC");
        div3.append(input2);
        
        var input3 = $(document.createElement('input'));  //创建value显示框
        input3.attr("class", "input-text valid");
        input3.attr("name", "serverName");
        input3.attr("value", serverName);
        input3.attr("readonly", true);
        input3.attr("style", "background-color: #CCCCCC");
        div4.append(input3);

        var div5 = $(document.createElement('div'));
        div5.attr("class", "formControls col-xs-8 col-sm-1");
        div1.append(div5);

        var input4 = $(document.createElement('input'));  //创建删除按钮
        input4.attr("class", "btn btn-primary radius");
        input4.attr("onclick", "delElement(this)");
        input4.attr("value", "删除");
        input4.attr("type", "button");
        div5.append(input4);

        return div1;
    }
    
    function delElement(obj) {
        $(obj).parent().parent().remove();
    }
    
    function setIpInfos(data) {
    	var ips = data.others.ips;
    	
        for(i = 0; i < ips.length; i++){
            var div1 = getElement("deploymentIp", "ports", ips[i].ip, ips[i].port, ips[i].serverName);
            $("#deploymentIp").parent().parent().after($(div1));
        }
    } 
</script>
</body>
</html>
