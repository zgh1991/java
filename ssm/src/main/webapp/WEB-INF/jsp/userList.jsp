<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
	<title>用户列表</title>
</head>
<body>
	<h2> 添加成功 </h2>
	<table border="1">
	<tr>
<th>姓名</th>
<th>年龄</th>
<th>创建日期</th>
</tr>
	<c:forEach items="${userList}" var="item" varStatus="status">
	  <tr >    
	  <td class="center"><span class="center">${item.userName}</span></td>    
	  <td>${item.age}</td>    
	  <td><fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></td>  
	  </tr>
	  </c:forEach>
	</table>

</body>
</html>
