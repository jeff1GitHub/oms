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
    <form class="form form-horizontal" id="form-group-add">
        <input id="id" name="id" type="hidden">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>站点名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" class="input-text valid" value="" placeholder="输入站点名称">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>站点编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="code" name="code" class="input-text valid" value="" placeholder="输入站点编号">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>站点类型：</label>
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
            <label class="form-label col-xs-4 col-sm-2">父站点编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="pcode" name="pcode" class="input-text valid" value="" placeholder="输入父站点编号" disabled>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>客户级别：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="level" name="level" class="select">
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${level}">
                            <option value="${item.code }">${item.name }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所属平台：</label>
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
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>站点目录：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="folder" name="folder" class="input-text valid" value="" placeholder="输入站点目录">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>支付别名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="payalias" name="payalias" class="input-text valid" value="" placeholder="输入支付别名">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>payment别名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="payment" name="payment" class="input-text valid" value="" placeholder="输入payment">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>主域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="domain" name="domain" class="input-text valid" value="" placeholder="输入主域名">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>六月查询地址：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="sixSearch" name="sixSearch" class="input-text valid" value="" placeholder="输入六月查询地址">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>后台域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="managedomain" class="input-text valid" value="" placeholder="输入后台域名">
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'managedomain', '输入后台域名!');">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>测试网后台预览域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="testdomain" class="input-text valid" value="" placeholder="输入测试网后台预览域名">
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'testdomain', '输入测试网后台预览域名!');">
            </div>
        </div>
        <div class="row cl">
        	<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>测试网前台预览域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="clientsDomain" class="input-text valid" value="" placeholder="输入测试网前台预览域名">
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'clientsDomain', '输入测试网前台预览域名!');">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>VPN帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="vpnaccount" name="vpnaccount" class="input-text valid" value="" placeholder="输入VPN帐号">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>VPN密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="vpnpassword" name="vpnpassword" class="input-text valid" value="" placeholder="输入VPN密码">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>别名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="alias" class="input-text valid" value="" placeholder="输入别名">
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'alias', '输入别名!');">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>vpnip：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="vpnip" class="input-text valid" value="" placeholder="输入vpnip">
            </div>
            <div class="formControls col-xs-8 col-sm-2">
                <input class="btn btn-primary radius" type="button" value="添加"
                       onclick="addElement(this, 'vpnip', '输入vpnip!');">
            </div>
        </div>
        <br/><br/><br/>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
                <input id="submit111" class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
            $.request.get("station/" + id, {}, function (result) {
                if (result.code = 200) {
                    $("#id").val(result.data.id);
                    $("#name").val(result.data.name);
                    $("#code").val(result.data.code);
                    $("#type").val(result.data.type.code);
                    $("#pcode").val(result.data.pcode);
                    $("#level").val(result.data.level.code);
                    $("#platform").val(result.data.platform.code);
                    $("#folder").val(result.data.folder);
                    $("#sixSearch").val(result.data.sixSearch);
                    $("#vpnaccount").val(result.data.vpnaccount);
                    $("#vpnpassword").val(result.data.vpnpassword);
                    $("#payalias").val(result.data.payalias);
                    $("#payment").val(result.data.payment);
                    $("#domain").val(result.data.domain);

                    getStationInfo(result.data);
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

//        $("#form-group-add").validate({
//            onkeyup: false,
//            focusCleanup: true,
//            success: "valid",
//            submitHandler: function (form) {
//                $(form).ajaxSubmit({
//                    type: requestType,
//                    url: "station",
//                    success: function (result) {
//                        if (result.code == 200) {
//                            layer.msg('添加成功!', {icon: 1, time: 1000});
//                            parent.$("#form-serverroom-search").submit();
//
//                            var index = parent.layer.getFrameIndex(window.name);
//                            parent.layer.close(index);
//                        } else {
//                            layer.msg(result.message, {icon: 1, time: 1000});
//                        }
//                    },
//                    error: function (XmlHttpRequest, textStatus, errorThrown) {
//                        layer.msg('error!', {icon: 1, time: 1000});
//                    }
//                });
//            }
//        });

        $("#submit111").on("click", function () {
            var data = $("#form-group-add").serialize();

            // 别名
            $("[name='alias']").each(function () {
                data += "&alias=" + $(this).val();
            })

            // 测试域名
            $("[name='testdomain']").each(function () {
                data += "&testdomain=" + $(this).val();
            })

            // 后台域名
            $("[name='managedomain']").each(function () {
                data += "&managedomain=" + $(this).val();
            })

            // 客户测试域名
            $("[name='clientsDomain']").each(function () {
                data += "&clientsDomain=" + $(this).val();
            })

            // vpn
            $("[name='vpnip']").each(function () {
                data += "&vpnip=" + $(this).val();
            })

            $.ajax({
                type: requestType,
                url: "station",
                data:data,
                success: function (result) {
                    if(result.code == 200){
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
            })
        })
    });

    function submit111(){
        var data = $("#form-group-add").serialize();
    }

    function addElement(obj, name, error) {
        var val = $("#" + name).val();
        if (val == null || val == "") {
            layer.msg(error, {icon: 5, time: 1000});
            return;
        }
        $("#" + name).val("");

        var div1 = getElement(name, val);

        $(obj).parent().parent().after($(div1));
    }
    
    function getStationInfo(data) {
        // vpn
        for(i = 0; i < data.vpnip.length; i++){
            var div1 = getElement("vpnip", data.vpnip[i]);
            $("#vpnip").parent().parent().after($(div1));
        }

        // 别名
        for(i = 0; i < data.alias.length; i++){
            var div1 = getElement("alias", data.alias[i]);
            $("#alias").parent().parent().after($(div1));
        }

        // 测试域名
        for(i = 0; i < data.testdomain.length; i++){
            var div1 = getElement("testdomain", data.testdomain[i]);
            $("#testdomain").parent().parent().after($(div1));
        }

        // 客户测试域名
        for(i = 0; i < data.clientsDomain.length; i++){
            var div1 = getElement("clientsDomain", data.clientsDomain[i]);
            $("#clientsDomain").parent().parent().after($(div1));
        }

        // 后台域名
        for(i = 0; i < data.managedomain.length; i++){
            var div1 = getElement("managedomain", data.managedomain[i]);
            $("#managedomain").parent().parent().after($(div1));
        }
    }

    function getElement(name, val){
        var div1 = $(document.createElement('div'));  //创建最外层div节点
        div1.attr("class", "row cl");

        var label = $(document.createElement('label'));  //创建label节点
        label.attr("class", "form-label col-xs-4 col-sm-2");
        div1.append(label);

        var div2 = $(document.createElement('div'));
        div2.attr("class", "formControls col-xs-8 col-sm-2");
        div1.append(div2);

        var input = $(document.createElement('input'));  //创建输入框
        input.attr("class", "input-text valid");
        input.attr("name", name);
        input.attr("value", val);
        input.attr("disabled", true);
        div2.append(input);

        var div3 = $(document.createElement('div'));
        div3.attr("class", "formControls col-xs-8 col-sm-1");
        div1.append(div3);

        var input = $(document.createElement('input'));  //创建删除按钮
        input.attr("class", "btn btn-primary radius");
        input.attr("onclick", "delElement(this)");
        input.attr("value", "删除");
        input.attr("type", "button");
        div3.append(input);

        return div1;
    }

    function delElement(obj) {
        $(obj).parent().parent().remove();
    }

    $("#type").on("change", function (obj) {
        if ($(type).val() == 2) {
            $("#pcode").attr("disabled", false);
        } else {
            $("#pcode").attr("disabled", true);
        }
    })
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
