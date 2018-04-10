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
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>机房名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" class="input-text valid" value="" placeholder="输入机房名称">
            </div>
        </div>
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
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>国家/地区：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="area" name="area" class="input-text" value="" placeholder="输入国家或地区" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="remark" name="remark" cols="" rows="" class="textarea" dragonfly="true" placeholder="说点什么...150个字符以内"></textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="javascript/serverroom-info.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
