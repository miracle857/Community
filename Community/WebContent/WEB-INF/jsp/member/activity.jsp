<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>毛新焕的community</title>
<link rel="Shortcut Icon" href="images/home.png" />
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css"
	href="style/style1.css" />
<link rel="stylesheet" type="text/css" id="css"
	href="style/style.css" />
<script src="js/main.js" type="text/javascript"></script>
</head>
<body>
	<div id="btm">
		<div id="main">

			<div id="header">
				<div id="top"></div>
				<div id="logo">
					<h1>跑步社区</h1>
				</div>
				<div id="logout">
					<a href="logout.action">注 销</a> | <a href="loginUI.html">登 录</a>
				</div>
				<div id="mainnav">
					<ul>
						<li class="current"><a href="activity.html">首页</a></li>
						<li><a href="javascript:void(0)">商城(待开发)</a></li>
						<li><a href="other/bbs.html">论坛</a></li>
					</ul>
					<span><table width="100%" border="0">
							<c:choose>
								<c:when test="${not empty member}">
									<tr>
										<td align="right">上一次登录时间：中国标准时间 <fmt:formatDate value="${member.latestdate }" pattern="yyyy/MM/dd HH:mm" /></td>
										<td align="center">欢迎，&nbsp; <a href="isCreateSpace.action"><font
												color="red"> ${member.nickname}</font> </a>&nbsp;
										</td>
										<td align="left">当前您的积米：&nbsp;&nbsp; <font color="red">${member.point}
										</font>&nbsp;&nbsp; 当前等级： <font color="red">${member.graderecord.gradename}</font>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<a href="loginUI.html">请登录</a>
								</c:otherwise>
							</c:choose>
						</table> </span>
				</div>
			</div>


			<div id="content">
				<div align="left">

					<span></span>
				</div>

				<div id="left">

					<div id="tabs0">
						<ul class="menu0" id="menu0">
							<li onmouseover="setTab(0)" class="lisovers">项目介绍</li>
							<li onmouseover="setTab(1)">个人介绍</li>
							<li onmouseover="setTab(2)">我的简历</li>
							<li onmouseover="setTab(3)">这里没东西</li>
							<li onmouseover="setTab(4)">待开发</li>
						</ul>
						<div class="main0" id="main0">
							<div class="block">
								<p style="font-size: medium; color: blue">该项目使用了ssm框架,实现了以下几个模块：包括注册登录,创建个人空间(图片的上传),添加好友，论坛功能实现了最简单的发帖回帖以及删除。</p>
							</div>
							<div>
								<p style="font-size: medium; color: blue">
									男。很帅。
								</p>
							</div>
							<div>
								<a href="me/毛新焕.pdf" style="font-size: large;color: blue; text-align: center;">点我查看</a>
							</div>
							<div>
								<p style="font-size: medium; color: blue">
									已经完善的模块：注册，登录，添加好友，论坛的发帖以及回复功能
								</p>
							</div>
							<div>
								<p style="font-size: medium; color: blue">
									修改个人信息,商城模块
								</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>

					<div id="hots">
						<h2>我的地盘</h2>
						<ul>
							<li>
								<div>
									<img src="images/a.gif" /> <a href="modify.html">基本信息</a>
									<p>《《未完成，勿点！！！》》</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/b.gif" /> <a
										href="inbox.html">我的信箱</a>
									<p>写信息、收件箱、发件箱</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/c.gif" /> <a
										href="friendlist.action">我的好友</a>
									<p>好友管理及黑名单</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/d.gif" /> <a href="isSpace.action">个性空间</a>
									<p>创建自己的个性空间</p>
								</div>
							</li>
						</ul>
					</div>

				</div>

				<div id="right">
					<table width="300">
						<tr>
							<td colspan="2" align="left">用户 ip 所在地：----</td>

						</tr>
						<tr>
							<td align="left">您是本社区的第x位来访者</td>
							<td align="left"><font color="green"><b>在线人数：x</b></font></td>
						</tr>

					</table>
					<div id="demo">
						<div id="indemo">
							<div id="demo1">
								<a href="#"><img src="images/1.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/2.gif"
									border="0" width="90" height="80" /></a> <a href="#"><img
									src="images/3.gif" border="0" width="90" height="80" /></a> <a
									href="#"><img src="images/4.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/1.gif"
									border="0" width="90" height="80" /></a> <a href="#"><img
									src="images/2.gif" border="0" width="90" height="80" /></a> <a
									href="#"><img src="images/3.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/4.gif"
									border="0" width="90" height="80" /></a>
							</div>
							<div id="demo2"></div>
						</div>
					</div>
					<h2>积分排行榜</h2>
					<ul>
						<c:forEach items="${pointRank}" var="p">
							<li><a href="#">${p.nickname}-----${p.point}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="clear"></div>

			</div>

			<div id="footer">
				<div id="copyright">
					<div id="copy">
						<p>CopyRight&copy;2009</p>
						<p>跑步社区(www.irun.com)</p>
					</div>
				</div>
				<div id="bgbottom"></div>
			</div>

		</div>
	</div>
</body>
</html>
