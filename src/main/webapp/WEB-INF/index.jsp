<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>Chinook tietokantatoteutus</title>
	<link rel="stylesheet" href="/styles/demo.css">
</head>

<body>

	<p><a href="/search.html">To search page</a></p>

	<form method="post">
		<input name="name" required type="text"
			placeholder=" type artist name here..." autofocus /> <input type="submit"
			value="Add to list" />
	</form>

	<ul>
		<c:forEach items="${ artists }" var="Artist">
			<li id="artist-${ Artist.getId() }"><a href="/albums?ArtistId=${ Artist.getId() }"><c:out value="${ Artist.getName() }" /></a></li>
		</c:forEach>
	</ul>

</body>
</html>