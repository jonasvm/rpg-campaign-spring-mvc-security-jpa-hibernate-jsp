<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Icon Bar (Sidebar - hidden on small screens) -->
	<nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
		<!-- Avatar image in top left corner -->
		<a href="${s:mvcUrl('HC#index').build() }"
			class="w3-bar-item w3-button w3-padding-large w3-black"> <i
			class="fa fa-home w3-xxlarge"></i>
			<p><fmt:message key="menu.home" /></p>
		</a> <a href="${s:mvcUrl('UC#users').build() }"
			class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i
			class="fa fa-user w3-xxlarge"></i>
			<p><fmt:message key="menu.users" /></p>
		</a> <a href="${s:mvcUrl('CC#campaigns').build() }"
			class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i
			class="fa fa-eye w3-xxlarge"></i>
			<p><fmt:message key="menu.campaign" /></p>
		</a> <a href="${s:mvcUrl('CC#contact').build() }"
			class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i
			class="fa fa-envelope w3-xxlarge"></i>
			<p><fmt:message key="menu.contact" /></p>
		</a>
	</nav>