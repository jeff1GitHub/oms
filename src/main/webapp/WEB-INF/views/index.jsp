<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jeff
  Date: 2017/10/14 0014
  Time: 上午 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="include/head.html" %>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"><a class="logo navbar-logo f-l mr-10 hidden-xs" href="/">BWT运维系统</a>
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">v2.0</span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li><a href="#">${userGroup}</a></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A"><i class="Hui-iconfont">&#xe6d5;</i>&nbsp;${userName}&nbsp;&nbsp;&nbsp;</a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="logout()"><i class="Hui-iconfont">&#xe726;</i>&nbsp;退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"> 
                        <a data-href="work-server-msg" data-title="客服" title="客服" href="javascript:;" onClick="Hui_admin_tab(this);">
							<i class="Hui-iconfont" style="font-size:18px">客&#xe68a;</i>
                            <span id="csCount" class="badge badge-danger"></span>
                        </a>
                    </li>
                    <li id="Hui-msg">
                        <a data-href="work-operations-msg" data-title="运维" title="运维" href="javascript:;" onClick="Hui_admin_tab(this);">
                        	<i class="Hui-iconfont" style="font-size:18px">&#xe68a;运</i>
                            <span id="opCount" class="badge badge-danger"></span>
                        </a>
                    </li>
                    <li id="Hui-audio">
                        <a href="#" title="关闭声音" onClick="stopOrPlaySound()">
                            <i class="Hui-iconfont" style="font-size:24px">&#xe6e5;</i>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <c:forEach var="item" items="${userPermis}">
            <dl id="menu-article">
                <dt><i class="Hui-iconfont">&#xe616;</i> ${item.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <c:forEach var="item1" items="${item.childrenPermis}">
                            <li><a data-href="${item1.url}" data-title="${item1.name}" href="javascript:;">${item1.name}</a></li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
        </c:forEach>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:;" onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="">我的桌面</span>
                    <em></em>
                </li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group">
            <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
            <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome.fdas"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前</li>
        <li id="closeall">关闭全部</li>
    </ul>
</div>

<audio id="tipMsg">
	<source src="lib/msg.wav" />
</audio>
<script type="text/javascript" src="javascript/index.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
</script>
</body>
</html>
