<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="common/common.jsp"%>
<!doctype html>
<html>
<head>
	<title>addUser</title>
</head>
<body>
	<form action="<%=path %>/fileUpload" method="post" enctype="multipart/form-data" >
	   <input type="file" name="file">
	   <input type="file" name="file2">
	<input type="submit" value="upload"/>
	</form>

</body>
</html>
