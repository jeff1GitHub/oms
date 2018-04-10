<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/25 0025
  Time: 上午 3:17
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
    <form method="post" class="form form-horizontal" id="form-group-permis-update">
        <input id="groupId" name="groupId" type="hidden" value="${groupId}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">分配权限：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <c:forEach var="item" items="${permis}">
                    <dl class="permission-list">
                        <dt><label><input type="checkbox" id="${item.id}" value="${item.id}" name="permisIds">${item.name}</label></dt>
                        <dd>
                            <c:forEach var="item1" items="${item.childrenPermis}">
                                <dl class="cl permission-list2">
                                    <dt><label class=""><input type="checkbox" id="${item1.id}" value="${item1.id}" name="permisIds">${item1.name}</label></dt>
                                    <dd>
                                        <c:forEach var="item2" items="${item1.childrenPermis}">
                                            <label class=""><input type="checkbox" id="${item2.id}" value="${item2.id}" name="permisIds">${item2.name}</label>
                                        </c:forEach>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </dd>
                    </dl>
                </c:forEach>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save">
                    <i class="icon-ok"></i> 确定
                </button>
            </div>
        </div>
    </form>
</article>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        $.get("", {}, function (result) {
            if (result.code == 200) {

            } else {
                layer.msg('添加成功!', {icon: 5, time: 1000});
            }
        }, "json");
    });


    $(function () {
        $(".permission-list dt input:checkbox").click(function () {
            $(this).closest("dl").find("dd input:checkbox").prop("checked", $(this).prop("checked"));
        });
        $(".permission-list2 dd input:checkbox").click(function () {
            var l = $(this).parent().parent().find("input:checked").length;
            var l2 = $(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
            if ($(this).prop("checked")) {
                $(this).closest("dl").find("dt input:checkbox").prop("checked", true);
                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", true);
            }
            else {
                if (l == 0) {
                    $(this).closest("dl").find("dt input:checkbox").prop("checked", false);
                }
                if (l2 == 0) {
                    $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", false);
                }
            }
        });
    });
    
    $(function () {
    	var groupId = $("#groupId").val();
        $.ajax({
        	method:"GET",
        	url:"group/permis?groupId=" +groupId,
        	success: function (jsonResult) {
                if (jsonResult.code == 200) {
                	var groupPermits = jsonResult.data;
                	for(var i = 0; i < groupPermits.length; i++) {
                		$("#"+groupPermits[i].permisId).attr("checked","checked");
               		}
                } else {
                    layer.msg(jsonResult.message, {icon: 1, time: 1000});
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                layer.msg('error!', {icon: 1, time: 1000});
            }
        })
    });

    $("#form-group-permis-update").validate({
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: "group/permis",
                success: function (jsonResult) {
                    if (jsonResult.code == 200) {
                        layer.msg('修改成功!', {icon: 1, time: 1000});
//                        var index = parent.layer.getFrameIndex(window.name);
//                        parent.layer.close(index);
                    } else {
                        layer.msg(jsonResult.message, {icon: 1, time: 1000});
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
        }
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>