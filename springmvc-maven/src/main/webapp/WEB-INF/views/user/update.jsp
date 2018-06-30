<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
	<sf:form method="post" modelAttribute="user">
		Name: <sf:input path="name" /><sf:errors path="name" /><br/>
		Sex: <sf:input path="sex" /><br/>
		Phone: <sf:input path="phone" /><sf:errors path="phone" /><br/>
		<input type="submit" value="修改用户" />
	</sf:form>
</body>
</html>