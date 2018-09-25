<%@ page contentType="text/html; charset=utf-8"%>
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
