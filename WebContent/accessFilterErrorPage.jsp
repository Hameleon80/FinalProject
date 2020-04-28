<%@ page isErrorPage="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<!--==================== 
	include head
 ====================-->
<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		
		<!--====================================== 
			include top string with common menu
		 ======================================-->
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<tr>
			<%@include file="/WEB-INF/jspf/left_menu.jspf" %>
			<td id="error_page">
				
				<!-- custom tag file -->
				<h:my_custom errorMessage="${requestScope.errorMessage}"> </h:my_custom>
			
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>