<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子</title>
<link rel="Shortcut Icon" href="images/home.png" />
<!-- <script type="text/javascript" src="jsp/js/jquery.min.js"></script> -->
<style type="text/css">
	table{
		border: 1px solid #ccc;
		margin: 0 auto;
	}
	form{
		
		margin: 0 auto;
		text-align: center;
	}
</style>
<script type="text/javascript">
window.onload = function(){
	var a = document.getElementById("return0");
	a.onclick = function(){
		window.location.href = 'login';
	};
}

</script>
</head>
<body>
	<h2 style="text-align: center;"><td width="352">主题：${subject.title}</td></h1>
	<table border="3px #ccc">
		<tr>
			
			<td>楼主：${subject.author}</td>
		</tr>
		<tr>
			<td width="352" height="200" bordercolor="red"
				style="border: 1px border-color: red">详细：${subject.content}</td>
			<c:if test="${subject.author eq member.nickname}">
				<td><a href="deleteBbs.action?bbsid=${subject.id}">删除</a></td>
			</c:if>
		</tr>
		<c:forEach items="${reply}" var="r" varStatus="i">
			<tr>
				<td>${i.count}楼：${r.content}</td>
				<td>回复人：${r.people}</td>
			</tr>
		</c:forEach>
	</table>

	<form action="other/reply.html" method="post">
		<tr>
			<td><textarea rows="10" cols="60" name="reply"></textarea></td>
			<td><input type="hidden" name="subjectid" value="${subject.id}"></td>
		</tr>
		<TR>
			<TH>
				<DIV></DIV>
			</TH>
			<TD>验证码<INPUT style="width: 100px" name=authcode /></TD>
			<TH><IMG alt=authCode src="authImg.do" /></TH>
		</TR>
		<input type="submit" value="回复">
		<tr>
			<button id="return0">返回</button>
			<a href="other/bbs.html">返回</a>
		</tr>
	</form>
</body>
</html>