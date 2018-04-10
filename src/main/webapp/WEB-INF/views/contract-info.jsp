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
    <form class="form form-horizontal" id="form-contract-edit">
        <input id="id" name="id" type="hidden"  />
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>合同类型：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="type" name="type" class="select" >
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${contractSelect}">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="producer" name="producer" class="select" >
                    	<option value="">---请选择---</option>
                        <c:forEach var="item" items="${producerSelect}">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>合同签署日期：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="startTime" name="startTime" class="input-text Wdate"
                       onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}' })" >
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>合同到期日期：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="endTime" name="endTime" class="input-text Wdate"
                       onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'startTime\')}'})" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>合同保存路径：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="" id="path" name="path" >
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>签章公司：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" placeholder="" name="signatureCompany" id="signatureCompany" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">签章名称：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" placeholder="" name="signaturePerson" id="signaturePerson">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="remark" name="remark" cols="" rows="" class="textarea" dragonfly="true"
                          placeholder="说点什么...100个字符以内" ></textarea>
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
<script type="text/javascript" src="javascript/contract-info.js"></script>
<script type="text/javascript">
var requestType = "post";
$(function () {
    /* 通过获取id判断是否为空来确定是增加还是修改 */
    var id = getUrlParam("id");
    if (id != null && id != "") {
        $.request.get("contract/" + id, {}, function (result) {
            if (result.code == 200) {
                var data = result.data;
                $("#id").val(data.id);
                $("#type").val(data.type.code);
                $("#producer").val(data.producer.code);
                $("#startTime").val(data.startTime);
                $("#endTime").val(data.endTime);
                $("#path").val(data.path);
                $("#signatureCompany").val(data.signatureCompany);
                $("#signaturePerson").val(data.signaturePerson);
                $("#remark").val(data.remark);
            } else {
                layer.msg(result.message, {icon: 1, time: 1000});
            }
        }, null);
    } else {
        // 没有获取到id参数,将提交方式改为新增
        requestType = "put";
    }

    $("#form-contract-edit").validate({
        rules: {
            type: {// 合同类型
                required: true,
            },
            producer: {// 供应商
                required: true,
            },
            startTime: {// 合同开始时间
                required: true,
                date:true,
            },
            endTime: {// 合同结束时间
                required: true,
                date:true,
            },
            path: {// 合同保存路径
                required: true,
                maxlength: 150,
            },
            signatureCompany: {// 签章公司
                required: true,
                maxlength: 30,
            },
            signaturePerson: {// 签章人
                required: false,
                maxlength: 30,
            },
            remark: {// 备注
                maxlength: 150,
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: requestType,
                url: "contract",
                success: function (result) {
                    if(result.code == 200){
                        layer.msg(result.message, {icon: 1, time: 1000});
                        parent.$("#form-search").submit();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }else {
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
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
