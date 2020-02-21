<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

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
			<h2 class="w3-text-light-grey">
				<fmt:message key="home.page.title" />
			</h2>
			<hr style="width: 200px" class="w3-opacity">
			<p>
				<fmt:message key="home.page.text.1" />
			</p>
			<ol>
				<li><fmt:message key="home.page.text.2" /></li>
				<li><fmt:message key="home.page.text.3" /></li>
				<li><fmt:message key="home.page.text.4" /></li>
			</ol>
			<p>
				<fmt:message key="home.page.text.5" />
			</p>
			<security:authorize access="!isAuthenticated()">
				<p>
					<a href="${s:mvcUrl('UC#form').build() }"
						class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
							key="home.page.create" /></a>
				</p>
				<p>
					<a href="${s:mvcUrl('LC#login').build() }"
						class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
							key="home.page.login" /></a>
				</p>
			</security:authorize>
			<!-- End Section -->
		</div>
		<c:import url="/resources/js/footer.jsp"></c:import>
		<!-- END PAGE CONTENT -->
	</div>
</body>
</html>