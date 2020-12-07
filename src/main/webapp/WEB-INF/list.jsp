<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Shopping List</title>
	<link rel="stylesheet" href="/styles/demo.css">
</head>
<body>
<form method="post">
    <input name="title" type="text" required placeholder="type item here..." autofocus /> 
    <input type="submit" value="Add to list" />
</form>

<table>
    <thead>
        <tr><th>Product</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${ list }" var="item">
        	<tr><td> <c:out value="${ item }"></c:out> </td></tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>