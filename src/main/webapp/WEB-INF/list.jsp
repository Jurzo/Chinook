<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Shopping List</title>
<script src="/js/app.js"></script>
<link rel="stylesheet" href="/styles/demo.css">
</head>
<body>
	<h1>Shopping list</h1>

	<form method="post">
		<input name="title" required type="text"
			placeholder=" type item here..." autofocus /> <input type="submit"
			value="Add to list" />
	</form>

	<ul>
		<c:forEach items="${ items }" var="shoppingListItem">
			<li id="product-${ shoppingListItem.getId() }"><c:out value="${ shoppingListItem.getTitle() }" />
				<button onclick="removeProduct(${ shoppingListItem.getId() })">Remove</button>
			</li>
		</c:forEach>
	</ul>

</body>
</html>