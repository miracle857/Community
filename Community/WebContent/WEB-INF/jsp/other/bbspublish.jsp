<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>毛新焕的community</title>
<link rel="Shortcut Icon" href="images/home.png" />
<c:if test="${not empty msg}">
	<script>
		var msg1 = "${msg}";
		console.log(msg1);
		alert(msg1);
	</script>
</c:if>
</head>
<body>

	<form action="bbspublish.action" method="post">
		<table>
			<tr>
			
				<td class="c5">标题</td>
				<td><input type="text" name="title" value=""></td>
			</tr>
			<tr>
				<td class="c5">文本</td>
				<td><textarea rows="5" cols="22" name="content"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="发布" class="a5"><input
					type="reset" value="重置" class="a5"></td>

			</tr>
			<TR>
				<TH>
					<DIV align=right>验证码：</DIV>
				</TH>
				<TD><INPUT style="width: 200px" name=authcode /></TD>
				<TH><IMG alt=authCode src="authImg.do" /></TH>
			</TR>
		</table>
	</form>
</body>
</html>