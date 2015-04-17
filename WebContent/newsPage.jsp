<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cold News</title>
</head>
<body>
	<table style="width: 60%;">
		<tr>
			<td>Title</td>
			<td>Short text</td>
			<td>Date</td>
			<td>Author</td>
		</tr>
		<c:forEach items="${listOfNews}" var="news">
			<tr>

				<td><c:out value="${news.title}" /></td>
				<td><c:out value="${news.shortText}" /></td>
				<td><c:out value="${news.date}" /></td>
				<td><c:out value="${news.author}" /></td>
				<td><input type=button
					onClick="location.href='coldNews.do?action=getNewsById&id=${news.id}'"
					value='Read the full news'></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


