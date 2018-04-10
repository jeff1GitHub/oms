<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/18 0018
  Time: 上午 1:13
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
<article class="page-container">
    <form class="form form-horizontal" id="form-group-add">
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">主域：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="domain" name="domain" class="input-text valid" value="" placeholder="输入主域">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">用途：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="purpose" name="purpose" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${purpose}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">带公网HTTPS证书：</label>
            <div class="radio-box">
                <input type="radio" id="https-0" name="https" value="0">
                <label for="https-0">是</label>
            </div>
            <div class="radio-box">
                <input type="radio" id="https-1" name="https" value="1" checked>
                <label for="https-1">否</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">带隐身保护：</label>
            <div class="radio-box">
                <input type="radio" id="privacy-0" name="privacy" value="0">
                <label for="privacy-0">是</label>
            </div>
            <div class="radio-box">
                <input type="radio" id="privacy-1" name="privacy" value="1" checked>
                <label for="privacy-1">否</label>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商选择(注册)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="regeditSelect" name="regeditSelect" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${producer}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商网站(注册)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="regeditWeb" name="regeditWeb" class="input-text" value="" placeholder="选择下拉填入" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商帐号(注册)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="regeditAccount" name="regeditAccount" class="input-text" value="" placeholder="选择下拉填入" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商选择(解析)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="resolutionSelect" name="resolutionSelect" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${producer}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商网站(解析)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="resolutionWeb" name="resolutionWeb" class="input-text" value="" placeholder="选择下拉填入" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">供应商帐号(解析)：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="resolutionAccount" name="resolutionAccount" class="input-text" value="" placeholder="选择下拉填入" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-5">过期时间：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="endTime" name="endTime" onfocus="WdatePicker()" class="input-text Wdate" value="" placeholder="输入过期时间">
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-5">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    var requestType = "POST";

    $(function () {
        /* 通过获取机房id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("domainmain/" + id, {}, function (result) {
                if (result.code == 200) {
                	$("#id").val(result.data.id);
                	$("#purpose").val(result.data.purpose.code);
                	$("#domain").val(result.data.domain);
                	$("#platform").val(result.data.platform.code);
                    $("#https-"+result.data.https).click();
                    $("#privacy-"+result.data.privacy).click();
                    $("#regeditWeb").val(result.data.regeditWeb);
                    $("#regeditAccount").val(result.data.regeditAccount);
                    $("#resolutionWeb").val(result.data.resolutionWeb);
                    $("#resolutionAccount").val(result.data.resolutionAccount);
                    $("#endTime").val(result.data.endTime);
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-group-add").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "domainmain",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg('添加成功!', {icon: 1, time: 1000});
                            parent.$("#form-search").submit();

                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.msg(result.message, {icon: 5, time: 1000});
                        }
                    },
                    error: function (XmlHttpRequest, textStatus, errorThrown) {
                        layer.msg('error!', {icon: 5, time: 1000});
                    }
                });
            }
        });
        
        $("#regeditSelect").change(function(){
        	var producerId = $("#regeditSelect").val();
        	if(null != producerId && producerId>0){
        		$.ajax({
        			type: "get",
        			url:"producer/"+producerId,
        			success: function(result){
        				$("#regeditWeb").val(result.data.loginDomain);
        				$("#regeditAccount").val(result.data.loginAccount);
        			},
        			error: function (XmlHttpRequest, textStatus, errorThrown) {
                        layer.msg('error!', {icon: 5, time: 1000});
                    }
        		});
        	}
        	
        });
        
        $("#resolutionSelect").change(function(){
        	var producerId = $("#resolutionSelect").val();
        	if(null != producerId && producerId>0){
        		$.ajax({
        			type: "get",
        			url:"producer/"+producerId,
        			success: function(result){
        				$("#resolutionWeb").val(result.data.loginDomain);
        				$("#resolutionAccount").val(result.data.loginAccount);
        			},
        			error: function (XmlHttpRequest, textStatus, errorThrown) {
                        layer.msg('error!', {icon: 5, time: 1000});
                    }
        		});
        	}
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
