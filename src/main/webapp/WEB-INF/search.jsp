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

	<h1>Artists</h1>
	
	<ul>
		<c:forEach items="${ artists }" var="Artist">
			<li id="artist-${ Artist.getId() }"><a href="/albums?ArtistId=${ Artist.getId() }"><c:out value="${ Artist.getName() }" /></a></li>
		</c:forEach>
	</ul>
	
	<h1>Albums</h1>

	<ul>
		<c:forEach items="${ albums }" var="Album">
			<li id="album-${ Album.getId() }"><a href="/albums?ArtistId=${ Album.getArtistId() }"><c:out value="${ Album.getTitle() }" /></a></li>
		</c:forEach>
	</ul>

</body>
</html>