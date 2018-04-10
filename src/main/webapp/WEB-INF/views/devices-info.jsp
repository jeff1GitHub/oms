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
    <form class="form form-horizontal" id="form-devices-edit">
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="producer" name="producer" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${producer}">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>品牌：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="brand" name="brand" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${brand}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="type" name="type" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${type}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>型号：</label>
            <div class="formControls col-xs-8 col-sm-3">
            	<input id="code" name="code" class="input-text" value="" placeholder="输入型号" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>平台：</label>
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
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>落地机房：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="serverroom" name="serverroom" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${serverroom}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理ip：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="ip" name="ip" class="input-text" value="" placeholder="输入ip" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>管理端口：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="port" name="port" class="input-text" value="" placeholder="输入端口" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理账户：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="account" name="account" class="input-text" value="" placeholder="输入账户" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>管理密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="password" name="password" class="input-text" value="" placeholder="输入密码" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>序列号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="number" name="number" class="input-text" value="" placeholder="输入序列号" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2">详情设定：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="setting" name="setting" class="input-text" value="" placeholder="输入设定" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商联系人：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="linkman" name="linkman" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${linkman}">
                            <option value="${item.id }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
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
<script type="text/javascript" src="javascript/devices-info.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
