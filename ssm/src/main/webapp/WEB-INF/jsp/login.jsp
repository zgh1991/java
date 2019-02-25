<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="common/common.jsp"%>
<!doctype html>
<html>
<head>
<title>登录</title>
</head>
<body>
	<form action="<%=path%>/login" method="post" id="refresh">
		姓名：<input name="userName" value="" /> 密码：<input name="password"
			value="" />  <input type="submit"
			value="登录">
	</form>

</body>
</html>