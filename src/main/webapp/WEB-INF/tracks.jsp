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

	<h1>Tracks for Album ${ album.getTitle() }</h1>

	<ul>
		<c:forEach items="${ tracks }" var="Track">
			<li id="album-${ Track.getId() }"><c:out value="${ Track.getName() }" />
			</li>
		</c:forEach>
	</ul>

</body>
</html>