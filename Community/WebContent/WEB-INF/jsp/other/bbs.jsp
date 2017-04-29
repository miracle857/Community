<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>毛新焕的community</title>
<link rel="Shortcut Icon" href="images/home.png" />
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css"
	href="style/style1.css" />
<script src="js/main.js" type="text/javascript"></script>
<script src="js/my.js" type="text/javascript"></script>
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
					<a href="login.html">注 销</a> | 收 藏
				</div>
				<div id="mainnav">
					<ul>
						<li><a href="activity.html">首页</a></li>
						
						<li class="current"><a href="bbs.jsp">论坛</a></li>

					</ul>
					<span></span>
				</div>
			</div>

			<div id="content">

				<div id="sc">
					<p class="weight">跑步论坛</p>
					<a id="isLogin" class="weight" href="javascript:void(0)" onclick="return isLogin()" style="font-size: medium;">发帖</a>
					<!-- <input type="button" value="发帖" onclick="return isLogin()"></input> -->
					<p></p>
					<div id="coms">
						<table id="listofcoms" cellpadding="0" cellspacing="0"
							height="200px">
							<tr>
								<td colspan="3" id="toptitle"></td>
								<tr>
								<c:forEach items="${subject}" var="s">
									<tr>
										<td>
											<a href="detail.html?subjectid=${s.id}">主题：${s.title}</a>
										</td>
										<td>
											作者：${s.author}
										</td>
										<td>
											发布时间：${s.publishtime}
										</td>
									</tr>
								</c:forEach>
									<tr>
										<td></td>
										<td></td>
										<td class="borderright"></td>
									</tr>
						</table>
					</div>
				</div>

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
