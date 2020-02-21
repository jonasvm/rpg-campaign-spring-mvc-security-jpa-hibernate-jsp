<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<c:import url="/resources/js/top.jsp"></c:import>
<body class="w3-black">
	<c:import url="/resources/js/icon-bar.jsp"></c:import>
	<c:import url="/resources/js/nav-bar.jsp"></c:import>
	<!-- Page Content -->
	<div class="w3-padding-large" id="main">
		<c:import url="/resources/js/welcome.jsp"></c:import>
		<c:import url="/resources/js/header.jsp"></c:import>
		<!-- Section -->
		<div class="w3-content w3-justify w3-text-grey w3-padding-64"
			id="about">
			<h2 class="w3-text-light-grey"><fmt:message key="user.detail.title" /></h2>
			<hr style="width: 200px" class="w3-opacity">
			<table class="table table-dark table-striped table-hover">
				<tr>
					<td><fmt:message key="user.detail.table.name" /></td>
					<td>${user.name }</td>
				</tr>
				<tr>
					<td><fmt:message key="user.detail.table.email" /></td>
					<td>${user.email }</td>
				</tr>
				<tr>
					<td><fmt:message key="user.detail.table.password" /></td>
					<td>${user.password }</td>
				</tr>
				<tr>
					<td><fmt:message key="user.detail.table.characters" /></td>
					<td>
						<ul>
							<c:forEach items="${user.characters }" var="npc">
								<li>${npc.name }</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</table>
			<!-- End Section -->
		</div>
		<c:import url="/resources/js/footer.jsp"></c:import>
		<!-- END PAGE CONTENT -->
	</div>
</body>
</html>