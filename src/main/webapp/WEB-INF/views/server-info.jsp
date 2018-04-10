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
    <form class="form form-horizontal" id="form-server-edit">
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">服务器编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="code" name="code" class="input-text" value="" placeholder="输入服务器编号" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-2">服务器名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="name" name="name" class="input-text" value="" placeholder="输入服务器名称" maxlength="30" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select" >
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${platform}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2">带宽：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="bandwidth" name="bandwidth" class="input-text" value="" placeholder="输入带宽" maxlength="30" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">机房：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="serverroom" name="serverroom" class="select" >
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${serverroom}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2">地区：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="area" name="area" class="input-text" value="" placeholder="输入地区" maxlength="30" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">连接类型：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="linkType" name="linkType" class="input-text" value="" placeholder="输入连接类型" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2">连接地址：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="linkAddr" name="linkAddr" class="input-text" value="" placeholder="输入连接地址" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">连接端口：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="linkPort" name="linkPort" class="input-text" value="" placeholder="输入连接端口" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">宿主机：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="host" name="host" class="input-text" value="" placeholder="输入宿主机" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2">序列号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="series" name="series" class="input-text" value="" placeholder="输入序列号" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">普通用户帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="account" name="account" class="input-text" value="" placeholder="输入普通用户帐号" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-2">普通用户密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="password" name="password" class="input-text" value="" placeholder="输入普通用户密码" maxlength="32" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">超级用户帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="superAccount" name="superAccount" class="input-text" value="" placeholder="输入超级用户帐号" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-2">超级用户密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="superPassword" name="superPassword" class="input-text" value="" placeholder="输入超级用户密码" maxlength="32" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">IDARC地址：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="idarcAddr" name="idarcAddr" class="input-text" value="" placeholder="输入IDARC地址" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2">IDARC帐号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="idarcAccount" name="idarcAccount" class="input-text" value="" placeholder="输入IDARC帐号" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">IDARC密码：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="idarcPassword" name="idarcPassword" class="input-text" value="" placeholder="输入IDARC密码" maxlength="32">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">内网IP：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <textarea id="intraIps" name="intraIps" cols="" rows="" class="textarea" placeholder="输入内网IP，多个以半角“，”分割，例：0.0.0.0，1.1.1.1"  maxlength="999"></textarea>
            </div>
            <label class="form-label col-xs-4 col-sm-2">外网IP：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <textarea id="interIps" name="interIps" cols="" rows="" class="textarea" placeholder="输入外网IP，多个以半角“，”分割，例：0.0.0.0，1.1.1.1"  maxlength="999"></textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">CPU型号：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="cpu" name="cpu" class="input-text" value="" placeholder="输入CPU型号" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">内存：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="memory" name="memory" class="input-text" value="" placeholder="输入内存大小" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">磁盘：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="disk" name="disk" class="input-text" value="" placeholder="输入磁盘大小" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">raid类型：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="raid" name="raid" class="select" >
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${raid}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
            <%--<div class="formControls col-xs-8 col-sm-1">--%>
                <%--<input id="raid" name="raid" class="input-text" value="" placeholder="输入raid类型" maxlength="30" >--%>
            <%--</div>--%>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">系统版本：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="system" name="system" class="input-text" value="" placeholder="输入系统版本" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">双网卡绑定：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="nic" name="nic" class="select" >
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-1">机型：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="serverType" name="serverType" class="input-text" value="" placeholder="输入机型" maxlength="30" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">租凭价格：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="price" name="price" class="input-text" value="" placeholder="输入租凭价格" maxlength="30" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">购置时间：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="buyTime" name="buyTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}' })" class="input-text Wdate" style="width:160px;" placeholder="选择购置时间" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">到期时间：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <input id="endTime" name="endTime" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'buyTime\')||\'%y-%M-%d\'}' })" class="input-text Wdate" style="width:160px;" placeholder="选择到期时间" >
            </div>
            <label class="form-label col-xs-4 col-sm-1">权属：</label>
            <div class="formControls col-xs-8 col-sm-1">
                <span class="select-box">
                    <select id="ownership" name="ownership" class="select" >
                        <option value="">---请选择---</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
                        <c:forEach var="item" items="${ownership}"><option value="${item.code }">${item.name }</option></c:forEach>
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
<script type="text/javascript">
    var requestType = "POST";

    $(function () {
        /* 通过获取机房id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("server/" + id, {}, function (result) {
                if (result.code == 200) {
                    $("#id").val(result.data.id);

                    $("#code").val(result.data.code);
                    $("#name").val(result.data.name);
                    $("#area").val(result.data.area);
                    $("#linkType").val(result.data.linkType);
                    $("#linkAddr").val(result.data.linkAddr);
                    $("#linkPort").val(result.data.linkPort);
                    $("#host").val(result.data.host);
                    $("#series").val(result.data.series);
                    $("#bandwidth").val(result.data.bandwidth);
                    $("#account").val(result.data.account);
                    $("#password").val(result.data.password);
                    $("#superAccount").val(result.data.superAccount);
                    $("#superPassword").val(result.data.superPassword);
                    $("#serverType").val(result.data.serverType);
                    $("#cpu").val(result.data.cpu);
                    $("#memory").val(result.data.memory);
                    $("#disk").val(result.data.disk);
                    $("#system").val(result.data.system);
                    $("#nic").val(result.data.nic);
                    $("#idarcAddr").val(result.data.idarcAddr);
                    $("#idarcAccount").val(result.data.idarcAccount);
                    $("#idarcPassword").val(result.data.idarcPassword);
                    $("#ownership").val(result.data.ownership);
                    $("#buyTime").val(result.data.buyTime);
                    $("#endTime").val(result.data.endTime);
                    $("#price").val(result.data.price);
                    $("#interIps").val(result.others.interIps);
                    $("#intraIps").val(result.others.intraIps);

                    if(result.data.serverroom != null){
                        $("#serverroom").val(result.data.serverroom.code);
                    }

                    if(result.data.platform != null){
                        $("#platform").val(result.data.platform.code);
                    }

                    if(result.data.raid != null){
                        $("#raid").val(result.data.raid.code);
                    }
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

        $("#form-server-edit").validate({
        	rules: {
        		system: {
                    required: true,
                    digits:true,
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: requestType,
                    url: "server",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg('操作成功!', {icon: 1, time: 1000});
                            parent.$("#form-search").submit();

                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.msg(result.message, {icon: 2, time: 1000});
                        }
                    },
                    error: function (XmlHttpRequest, textStatus, errorThrown) {
                        layer.msg('error!', {icon: 2, time: 1000});
                    }
                });
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
