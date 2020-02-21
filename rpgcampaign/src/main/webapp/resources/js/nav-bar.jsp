<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- Navbar on small screens (Hidden on medium and large screens) -->
<div class="w3-top w3-hide-large w3-hide-medium" id="myNavbar">
	<div
		class="w3-bar w3-black w3-opacity w3-hover-opacity-off w3-center w3-small">
		<a href="${s:mvcUrl('HC#index').build() }"
			class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
				key="menu.home" /></a> <a href="${s:mvcUrl('UC#users').build() }"
			class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
				key="menu.users" /></a> <a href="${s:mvcUrl('CC#campaigns').build() }"
			class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
				key="menu.campaign" /></a> <a href="${s:mvcUrl('CC#contact').build() }"
			class="w3-bar-item w3-button" style="width: 25% !important"><fmt:message
				key="menu.contact" /></a>
	</div>
</div>