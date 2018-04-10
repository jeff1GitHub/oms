<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/31 0031
  Time: 上午 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css"/>
    <link href="lib/tab/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="page-container">
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="role" name="role"/>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-1"><span class="c-red">*</span>故障标签：</label>
        <div class="formControls col-xs-8 col-sm-4">
            <span class="select-box">
                <select id="faultType" name="faultType" class="select" required>
                    <option value="">---请选择---</option>
                    <c:forEach var="item" items="${faultType}">
                        <option value="${item.code }">${item.name }</option>
                    </c:forEach>
                </select>
            </span>
        </div>
        <label class="form-label col-xs-4 col-sm-1"><span class="c-red">*</span>优先级：</label>
        <div class="formControls col-xs-8 col-sm-4">
            <span class="select-box">
                <select id="priority" name="priority" class="select" required>
                    <option value="">---请选择---</option>
                    <c:forEach var="item" items="${priority}">
                        <option value="${item.code }">${item.name }</option>
                    </c:forEach>
                </select>
            </span>
        </div>
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-1">别名：</label>
        <div class="formControls col-xs-8 col-sm-4">
            <input id="aliasName" name="aliasName" type="text" class="input-text" value="" placeholder="">
        </div>
        <label class="form-label col-xs-4 col-sm-1">域名：</label>
        <div class="formControls col-xs-8 col-sm-4">
            <input id="domain" name="domain" type="text" class="input-text" value="" placeholder="">
        </div>
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-1">详细内容：</label>
        <div class="formControls col-xs-8 col-sm-9">
        	<input type="hidden" id="describe" name="describe" value=""/>
            <div id="editor" type="text/plain" style="width:100%;height:300px;"></div>
        </div>
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-1">图片上传：</label>
        <div id="fileValues" style="display:none;"></div>
        <div class="formControls col-xs-8 col-sm-9">
            <div class="uploader-list-container">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker-2"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress"><span class="text">0%</span> <span class="percentage"></span></div>
                    <div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div>
                        <div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row cl">
        <div id="radioDiv" class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
            <label style="font-size: 15px;">
                <input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked="checked" />
                <span id="optionsRadios1Text"></span>
            </label>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label style="font-size: 15px;">
                <input type="radio" name="optionsRadios" id="optionsRadios2" value="0" />
                <span id="optionsRadios2Text"></span>
            </label>
        </div>
    </div>
    <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
            <button id="submitBtn" onClick="submit();" class="btn btn-primary radius" type="button" disabled>提交</button>
            <button id="cancelBtn" onClick="layer_close();" class="btn btn-default radius" type="button">关闭</button>
        </div>
    </div>
    <div class="row cl">
		<div class="clearfix" style="padding-left:152px">
			<ul class="title title1 left">
				<li class="cur"><a href="javascrpt:void(0);" name="tab1" onClick="showTab(this)">备注</a></li>
				<li ><a href="javascrpt:void(0);" name="tab2" onClick="showTab(this)">改动记录</a></li>
			</ul>
		</div>
	</div>
	<div class="row cl" style="height:200px;">
		<div id="tab1" class="tabCon" style="padding-left:153px;display: block;">
			<div id="taskRemarks"></div>
			<textarea id="taskRemark" cols="" rows="" class="textarea" placeholder="请输入备注"  maxlength="999"></textarea>
			<button id="taskRemarkAdd" onClick="addTaskRemark();" class="btn btn-primary radius" type="button">增加备注</button>
		</div>
		<div id="tab2" class="tabCon" style="padding-left:153px">
		</div>
	</div>
</div>
<script type="text/javascript" src="javascript/work-info.js"></script>
<script>
</script>
</body>
</html>