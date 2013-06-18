<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<!DOCTYPE html>
<html>
	<head>
    	<title>Task Note</title>
    	<link rel="stylesheet" type="text/css" href="css/main.css"/>
    	<meta charset="utf-8"> 
  	</head>
  	<body>
  		<div class="container">
			<div style="width: 100%;">
				<div class="line"></div>
				<div class="topLine">
					<div style="float: left;">
						<img src="images/task.png" style="width: 85px;"/>
					</div>
					<div style="float: left;" class="headline">Tasks</div>
					<div style="float: right;">
						${userNickname}
						<a class="headline" href="${url}">${urlLinktext}</a>
					</div>
				</div>
			</div>

		<div style="clear: both;"></div>  
		You have a total number of ${fn:length(tasks)} tasks.

		<table>
			<tr>
				<th class="medium-field">Summary</th>
				<th class="large-field">Description</th>
				<th class="url-field">URL</th>
				<th class="small-field text-center">Created</th>
				<th class="small-field text-center">Due Date</th>
				<th class="small-field text-center">Options</th>
			</tr>
			<c:forEach var="task" items="${tasks}">
				<tr>
					<td>${task.summary}</td>
					<td>${task.description}</td>
					<td><a href="${task.url}">${task.url}</a></td>
					<td class="text-center"><fmt:formatDate value="${task.created}" pattern="dd/MM/yy, HH:mm" /></td>
					<td class="text-center"><fmt:formatDate value="${task.dueDate}" pattern="dd/MM/yy" /></td>
					<td class="text-center">
						<a class="done" href="/done?id=${task.id}">Done</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<hr/>

		<div class="main">
	
			<div class="headline">New Task</div>
	
			<c:choose>
				<c:when test="${logged}">
				    <form action="/new" method="post" accept-charset="utf-8">
						<table>
							<tr>
								<td><label for="summary">Summary</label></td>
								<td><input type="text" name="summary" id="summary" size="65" /></td>
							</tr>
							<tr>
								<td valign="description"><label for="description">Description</label></td>
								<td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
							</tr>
							<tr>
								<td valign="top"><label for="url">URL</label></td>
								<td><input type="url" name="url" id="url" size="65" /></td>
							</tr>
							<tr>
								<td valign="top"><label for="url">Due Date</label></td>
								<td><input type="date" name="date" id="date" size="35" /></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="submit" value="Create" /></td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
					Please login with your Google account
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</body>
</html> 