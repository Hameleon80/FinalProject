<%@ include file="WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>

<!-- =====================
	include head in page 
=======================-->

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<!--==============================
			include top string with menu 
		 ===============================-->
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<tr>
			<!-- =================================
				include main menu in left side
			================================== -->
			<%@ include file="/WEB-INF/jspf/left_menu.jspf" %>
			
			<td class="centerText">
				
				<!--=================== 
				 	Table of flights
				 ===================-->
				 
				<h1>Table of flights</h1>
				<c:if test="${userRole.name == 'admin'}">
					<div class="alignRight marginRight100"><a href="adminAddNewFlight.jsp">Add new flight</a></div>
				</c:if>
				<ctg:flightsList/>
			</td>
			<!--=========================================
				include in right side sort menu 
			 =========================================-->
			<%@ include file="/WEB-INF/jspf/right_menu.jspf"%>
		</tr>
		
		<!--=========== 
			Footer
		 ===========-->
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>