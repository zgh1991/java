<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="common/common.jsp"%>
<!doctype html>
<html>
<body>
你好,${userName}
<h2> <a href="<%=path %>/gotoAddUser">添加用户</a></h2>
<h2> <a href="<%=path %>/userList">查看用户列表</a></h2>
<h2> <a href="<%=path %>/gotoFileUpload">文件上传 </a></h2>
</body>
</html>
