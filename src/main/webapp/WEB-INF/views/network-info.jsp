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
    <form class="form form-horizontal" id="form-edit">
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${platform}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>线路类型：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="type" name="type" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${type}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>线路品牌：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="brand" name="brand" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${brand}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>带宽大小：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="bandwidth" name="bandwidth" class="input-text" value="" placeholder="输入带宽大小" maxlength="15" number="true" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>ip段：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="ip" name="ip" class="input-text" value="" placeholder="输入ip段" maxlength="30" ip="true" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">线路作用范围：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="range" name="range" class="input-text" value="" placeholder="输入线路作用范围" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>合同编码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="contract" name="contract" class="input-text" value="" placeholder="输入合同编码" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="producer" name="producer" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${producer}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>落地机房：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="serverroom" name="serverroom" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${serverroom}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>线路购置时间：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="startTime" name="startTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}' })" class="input-text Wdate" value="" placeholder="输入线路购置时间" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2">线路到期时间：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="endTime" name="endTime" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'startTime\')}'})" class="input-text Wdate" value="" placeholder="输入线路到期时间" required>
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
        /* 通过获取网设id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("network/" + id, {}, function (result) {
                if (result.code = 200) {
                    $("#id").val(result.data.id);
                    $("#platform").val(result.data.platform.code);
                    $("#brand").val(result.data.brand.code);
                    $("#type").val(result.data.type.code);
                    $("#bandwidth").val(result.data.bandwidth);
                    $("#ip").val(result.data.ip);
                    $("#range").val(result.data.range);
                    $("#contract").val(result.data.contract);
                    $("#producer").val(result.data.producer.code);
                    $("#serverroom").val(result.data.serverroom.code);
                    $("#startTime").val(result.data.startTime);
                    $("#endTime").val(result.data.endTime);
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "network",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg('成功!', {icon: 1, time: 1000});
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
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
