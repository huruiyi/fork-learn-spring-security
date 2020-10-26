<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Users : View</title>
</head>
<body>
	<jsp:include page="../layout.jsp" />

	<div class="container">
		<h1>Users : Create</h1>
	</div>

	<div class="container">
		<c:if test="${globalMessage}">
			<div class="alert alert-success">
				<c:out value="globalMessage" />
			</div>
		</c:if>

		<div class="pull-right">
			<a href="${pageContext.request.contextPath}/user"> Users </a>
		</div>
		<table class="table table-bordered">
			<tr>
				<td>ID</td>
				<td id="tid" th:text="${user.id}"></td>
			</tr>
			<tr>
				<td>Date</td>
				<td id="tCreated" th:text="${#calendars.format(user.created)}"></td>
			</tr>
			<tr>
				<td>Username</td>
				<td id="username" th:text="${user.username}"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td id="tEmail" th:text="${user.email}"></td>
			</tr>
		</table>
		<div class="pull-left">
			<a href="${pageContext.request.contextPath}/user/delete/${user.id}"> delete </a>
			<a href="${pageContext.request.contextPath}/user/modify/${user.id}"> modify </a>
		</div>
	</div>
</body>
</html>
