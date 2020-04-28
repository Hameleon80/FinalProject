<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>

<c:set var="title" value="Main" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
		<%@ include file="/WEB-INF/jspf/left_menu.jspf" %>
			<td class="centerText">
				<h1><fmt:message key="welcome"/></h1>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>