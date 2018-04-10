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
    <form class="form form-horizontal" id="form-attack-edit">
        <input id="id" name="id" type="hidden" >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>站点编号：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="stationCode" name="stationCode" class="input-text" value="" placeholder="输入站点编号" maxlength="60" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所属平台：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="platform" name="platform" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${platform}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>客户级别：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="level" name="level" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${level}"><option value="${item.code }">${item.name }</option></c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>别名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id=alias name="alias" class="input-text" value="" placeholder="输入别名" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>被攻击时间：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="attackTime" name="attackTime" onfocus="WdatePicker({})" 
                	class="input-text Wdate" style="width:160px;" placeholder="选择被攻击时间" required>
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>域名：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="attackDomain" name="attackDomain" class="input-text" value="" placeholder="输入域名" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">攻击强度：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="attackIntensity" name="attackIntensity" class="input-text" value="" placeholder="输入攻击强度" maxlength="30">
            </div>
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>高防服务器：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="defenseServer" name="defenseServer" class="input-text" value="" placeholder="输入高防服务器" maxlength="30" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>负责人：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <span class="select-box">
                    <select id="director" name="director" class="select" required>
                        <option value="">---请选择---</option>
                        <c:forEach var="item" items="${opUsers}"><option value="${item.account }">${item.account }</option></c:forEach>
                    </select>
                </span>
            </div>
            <label class="form-label col-xs-4 col-sm-2">高防天数：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input id="defenseNum" name="defenseNum" class="input-text" value="" placeholder="输入高防天数" maxlength="30">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">备注：</label>
            <div class="formControls col-xs-20 col-sm-8">
                <textarea id="remark" name="remark" cols="" rows="" class="textarea" placeholder="输入备注"  maxlength="999"></textarea>
            </div>
        </div>
        <div class="row cl">
        	<label class="form-label col-xs-4 col-sm-2"></label>
        	<div class="formControls col-xs-20 col-sm-8">
		        <div class="cl pd-5 bg-1 bk-gray mt-20">
			        <span class="l">
			            <a href="javascript:;" onclick="attackMessageEditorShow()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
			        </span>
			    </div>
		        <table id="attackMessageList" class="table table-border table-bordered table-bg">
			        <thead>
				        <tr><th scope="col" colspan="17">攻击详情</th></tr>
				        <tr class="text-c">
					        <th>序号</th>
					        <th>域名</th>
					        <th>被攻击次数</th>
					        <th>攻击强度</th>
					        <th>负责人</th>
					        <th>攻击时间</th>
					        <th>备注</th>
					        <th>操作</th>
				        </tr>
			        </thead>
			        <tbody></tbody>
			    </table>
		    </div>
		</div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-5">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
    <div id="attackMessagePopup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<form id="popFrom">
		<div class="modal-dialog">
			<div class="modal-content radius" style="width:800px;height:600px">
				<div class="modal-header">
					<h3 class="modal-title">添加攻击详情</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="width:800px;height:450px">
					<div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right"><span class="c-red">*</span>域名：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			                <input id="popDomain" name="popDomain" class="input-text" value="" placeholder="输入域名" maxlength="30" required>
			            </div>
			        </div><br/>
			        <div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right"><span class="c-red">*</span>被攻击次数：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			                <input id="popAttackNum" name="popAttackNum" class="input-text" value="" placeholder="输入被攻击次数" maxlength="30" required>
			            </div>
			        </div><br/>
			        <div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right">攻击强度：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			                <span class="select-box">
			                    <select id="popAttackIntensity" name="popAttackIntensity" class="select" >
			                        <option value="">---请选择---</option>
			                        <c:forEach var="item" items="${level}"><option value="${item.code }">${item.name }</option></c:forEach>
			                    </select>
			                </span>
			            </div>
			        </div><br/>
			        <div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right">负责人：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			                <span class="select-box">
			                    <select id="popDirector" name="popDirector" class="select" >
			                        <option value="">---请选择---</option>
			                        <c:forEach var="item" items="${opUsers}"><option value="${item.account }">${item.account }</option></c:forEach>
			                    </select>
			                </span>
			            </div>
			        </div><br/>
			        <div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right">攻击时间：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			               	<input id="popAttackTime" name="popAttackTime" onfocus="WdatePicker({})" class="input-text Wdate" style="width:160px;" placeholder="选择购置时间" >
			            </div>
			        </div><br/>
			        <div class="row cl">
			            <label class="form-label col-xs-4 col-sm-5"  style="text-align:right">备注：</label>
			            <div class="formControls col-xs-8 col-sm-3">
			               	<textarea id="popRemark" name="popRemark" cols="" rows="" class="textarea" placeholder="请输入备注"  maxlength="999"></textarea>
			            </div>
			        </div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onClick="addAttackMessage();">确定</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
		</form>
	</div>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">

	// 表单中字段的key
	var columns = [
	    {data : 'id'},
	    {data : 'attackMessageDomain'},
	    {data : 'attackMessageNum'},
	    {data : 'attackMessageIntensity'},
	    {data : 'attackMessageDirector'},
	    {data : 'attackMessageTime'},
	    {data : 'attackMessageRemark'},
	    {data : function(e){
	    	return "";
	    }}
	];

    var requestType = "POST";
    
    

    $(function () {
        /* 通过获取机房id判断是否为空来确定是增加还是修改 */
        var id = getUrlParam("id");
        if (id != null && id != "") {
            $.request.get("attack/" + id, {}, function (result) {
                if (result.code == 200) {
                	$("#id").val(result.data.id);
                	$("#stationCode").val(result.data.stationCode);
                	$("#platform").val(result.data.platform.code);
                	$("#level").val(result.data.level.code);
                	$("#alias").val(result.data.alias);
                	$("#attackTime").val(result.data.attackTime);
                	$("#attackDomain").val(result.data.attackDomain);
                	$("#attackIntensity").val(result.data.attackIntensity);
                	$("#defenseServer").val(result.data.defenseServer);
                	$("#director").val(result.data.director);
                	$("#defenseNum").val(result.data.defenseNum);
                	$("#remark").val(result.data.remark);
                	
                    bindJsonTable("attackMessageList", result.data.attackMessages, columns);
                	
                } else {
                    layer.msg(result.message, {icon: 1, time: 1000});
                }
            }, null);
        } else {
            // 没有获取到id参数,将提交方式改为新增
            requestType = "put";
        }

        $("#form-attack-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: "post",
                    url: "attack",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg('操作成功!', {icon: 1, time: 1000});
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
    
    function attackMessageEditorShow() {
    	$("#attackMessagePopup").modal("show");
    }
    
    // attack_message_edit('添加详情','attack-info-detail')
    function attack_message_edit(title, url) {
        //var index = layer.open({
        //    type: 2,
        //    title: title,
        //    content: url
        //});
        
        window_open(title, url, 800, 500);
    }
    
    var index = 0;
    function addAttackMessage(){
    	
    	$("#popFrom").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
            	$("<tr></tr>").append($("<td></td>"))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageDomain\" value=\""+ $("#popDomain").val() +"\"/>" + $("#popDomain").val()))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageNum\" value=\""+ $("#popAttackNum").val() +"\"/>" + $("#popAttackNum").val()))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageIntensity\" value=\""+ $("#popAttackIntensity").val() +"\"/>" + $("#popAttackIntensity").find("option:selected").text()))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageDirector\" value=\""+ $("#popDirector").val() +"\"/>" + $("#popDirector").find("option:selected").text()))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageTime\" value=\""+ $("#popAttackTime").val() +"\"/>" + $("#popAttackTime").val()))
            	.append($("<td></td>").html("<input type=\"hidden\" name=\"attackMessages["+ index +"].attackMessageRemark\" value=\""+ $("#popRemark").val() +"\"/>" + $("#popRemark").val()))
            	.append($("<td></td>").html("<a title=\"删除\" href=\"javascript:;\" class=\"ml-5\" style=\"text-decoration:none\" onclick=\"remove(this);\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>"))
            	.appendTo($("#attackMessageList").find("tbody"));
            	index += 1;
            	$("#attackMessagePopup").modal("hide");
            }
        });
    }
    
    function remove(obj) {
    	$(obj).parent().parent("tr").remove();
    }

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
