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

	<h1>Albums for artist ${ artist.getName() }</h1>

	<ul>
		<c:forEach items="${ albums }" var="Album">
			<li id="album-${ Album.getId() }"><a href="/tracks?AlbumId=${ Album.getId() }"><c:out value="${ Album.getTitle() }" /></a></li>
		</c:forEach>
	</ul>

</body>
</html>