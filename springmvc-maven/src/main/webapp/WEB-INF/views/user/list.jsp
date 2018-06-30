<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<a href="add">添加用户</a><br/>
	<c:forEach items="${ users }" var="u">
		<a href="${ u.value.account }"> ${ u.value.account } -- ${ u.value.name } -- ${ u.value.sex } -- ${ u.value.phone } </a>
		<a href="${ u.value.account }/update">修改</a>
		<a href="${ u.value.account }/delete">删除</a><br />
	</c:forEach>
</body>
</html>