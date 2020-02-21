<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- Welcome bar With Spring Security-->
<security:authorize access="isAuthenticated()">
	<div align="right">
		<a href="?locale=en_US"><fmt:message key="navigation.en" /></a> - <a
			href="?locale=pt"><fmt:message key="navigation.pt" /></a> -
		<fmt:message key="navigation.welcome" />
		<security:authentication property="principal.name" />
		! - <a href="${s:mvcUrl('LC#logout').build() }"><fmt:message
				key="navigation.logout" /></a>
	</div>
</security:authorize>