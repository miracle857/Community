<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>毛新焕的community</title></title>
<link rel="Shortcut Icon" href="images/home.png" />
<link rel="stylesheet" type="text/css" id="css" href="../style/main.css" />
<link rel="stylesheet" type="text/css" id="css"
	href="../style/style1.css" />
<link rel="stylesheet" type="text/css" id="css"
	href="../style/style.css" />

<style type="text/css">
<!--
table {
	border-spacing: 1px;
	border: 1px solid #A2C0DA;
}

td, th {
	padding: 2px 5px;
	border-collapse: collapse;
	text-align: left;
	font-weight: normal;
}

thead tr th {
	height: 50px;
	background: #B0D1FC;
	border: 1px solid white;
}

thead tr th.line1 {
	background: #D3E5FD;
}

thead tr th.line4 {
	background: #C6C6C6;
}

tbody tr td {
	height: 35px;
	background: #CBE2FB;
	border: 1px solid white;
	vertical-align: middle;
}

tbody tr td.line4 {
	background: #D5D6D8;
}

tbody tr th {
	height: 35px;
	background: #DFEDFF;
	border: 1px solid white;
	vertical-align: middle;
}

tfoot tr td {
	height: 35px;
	background: #FFFFFF;
	border: 1px solid white;
	vertical-align: middle;
}
-->
</style>

<script src="../js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript">
	function addFriend() {
		document.forms.buddyListForm.action = "addFriend.action";
		document.all("buddyListForm").submit();
	}
	function delBuddy() {
		cCount = getCheckedCount("nickName");
		if (cCount == 0) {
			alert("请至少一条信息!");
			return;
		}
		if (confirm("确定删除吗？") == false) {
			return false;
		}
		document.forms.buddyListForm.action = "<c:url value='deleteBuddy.action' />";
		document.all("buddyListForm").submit();
	}
</script>
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
					<a href="../login.html">注 销</a> | 收 藏
				</div>
				<div id="mainnav">
					<ul>
						<li class="current"><a href="#">首页</a></li>
						<li><a href="other/bbs.html">论坛</a></li>
					</ul>
					<span></span>
				</div>
			</div>

			<div id="tabs1">
				<ul>
					<li><a href="matchFriend.html" title="好友速配"><span>好友速配</span></a></li>
					<li><a href="buddyList.html" title="好友名单"><span>好友名单</span></a></li>
					<li><a href="blackList.html" title="黑名单"><span>黑名单</span></a></li>
				</ul>
			</div>
			<br />
			<br />

			<div id="content" align="center">
				<div id="center">
					<BR />
					<BR />
					<form method="post" name="buddyListForm">
						<table width="600" align="center" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<td><h4>会员列表</h4></td>
								</tr>
							</thead>

							<tr>
								<td width="100%">
									<table width="100%">
										<thead>
											<tr>
												<th width="20%" align="center" scope="col"><b>姓名</b></th>
												<th width="20%" align="center" scope="col"><b>性别</b></th>
												<th width="20%" align="center" scope="col"><b>年龄</b></th>
												<th width="20%" align="center" scope="col"><b>来自</b></th>
												<th width="10%" align="center"><b>操作</b></th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${friends}" var="f">
											<tr>
												<td width="20%" align="center">${f.nickname}</td>
												<td width="20%" align="center">男</td>
												<td width="20%" align="center">32</td>
												<td width="20%" align="center">其它</td>
												<td width="10%" align="center"><img
													src="../images/button_delete.gif" alt="移动到黑名单"
													onclick="window.location='../pages/messenger/moveToBlackList.do?blackName=briup5'"
													style="cursor: hand" /></td>
											</tr>
											</c:forEach>
										</tbody>

									</table>
								</td>
							</tr>
						</table>

					</form>

					<br />
					<br />
					<div id="hots">
						<h2>我的地盘</h2>
						<ul>
							<li>
								<div>
									<img src="../images/a.gif" /> <a href="../member/modify.html">基本信息</a>
									<p>可修改自己的基本信息</p>
								</div>
							</li>
							<li>
								<div>
									<img src="../images/b.gif" /> <a href="inbox.html">我的信箱</a>
									<p>写信息、收件箱、发件箱</p>
								</div>
							</li>
							<li>
								<div>
									<img src="../images/c.gif" /> <a href="buddyList.html">我的好友</a>
									<p>好友管理及黑名单</p>
								</div>
							</li>
							<li>
								<div>
									<img src="../images/d.gif" /> <a href="../member/noSpace.html">个性空间</a>
									<p>创建自己的个性空间</p>
								</div>
							</li>
						</ul>
					</div>

				</div>
				<div id="right">
					<h2>&nbsp;</h2>
					<ul>
						<li></li>
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
