<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="common/common.jsp"%>

<!doctype html>
<html>
<head>
	<title>文件列表</title>
</head>
<body>
	<h2> 上传成功 </h2>
<h3>文件列表：</h3>
<ul>
<c:forEach items="${fileList}" var="item" varStatus="status">
<li>
<a href="<%=path %>/downloadFile?filename=${item.filePath}">${item.fileName}</a>
</li>
</c:forEach>
</ul>
</body>
</html>
