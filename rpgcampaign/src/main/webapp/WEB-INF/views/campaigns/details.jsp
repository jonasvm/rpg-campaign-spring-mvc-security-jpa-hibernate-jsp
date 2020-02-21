<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			<h2 class="w3-text-light-grey"><fmt:message key="campaign.details.title" /></h2>
			<hr style="width: 200px" class="w3-opacity">
			<div>
				<p>${msg_success }</p>
			</div>
			<table class="table table-dark table-striped table-hover">
				<tr>
					<td><fmt:message key="campaign.details.table.name" /></td>
					<td>${campaign.name }</td>
				</tr>
				<tr>
					<td><fmt:message key="campaign.details.table.master" /></td>
					<td>${campaign.master }</td>
				</tr>
				<tr>
					<td></td>
					<td><form:form
							action="${s:mvcUrl('CC#add').arg(0,campaign.id).build() }"
							method="post">
							<div>
								<label><fmt:message key="campaign.details.table.addplayer" /></label> <select name="user_email">
									<c:forEach items="${users }" var="user">
										<option value="${user.email }">${user.email }</option>
									</c:forEach>
								</select>
								<div>
									<button type="submit"><fmt:message key="campaign.details.table.add" /></button>
								</div>
							</div>
						</form:form></td>
				</tr>
			</table>
			<!-- End Section -->
		</div>
		<c:import url="/resources/js/footer.jsp"></c:import>
		<!-- END PAGE CONTENT -->
	</div>
</body>
</html>