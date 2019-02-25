<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="common/common.jsp"%>
<!doctype html>
<html>
<head>
	<title>addUser</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/addUser" method="post" id="refresh">
	    姓名：<input name="userName" value=""/>
	    密码：<input name="password" value=""/>
	   年龄 ：<input name="age" value="" />
		<input type="submit" value="提交">
	</form>

</body>
</html>
