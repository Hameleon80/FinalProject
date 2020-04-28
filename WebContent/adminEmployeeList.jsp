<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.db.Post"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				<h1>Table of employees</h1> <c:if test="${userRole.name == 'admin'}">
					<div class="alignRight marginRight100">
						<a href="adminAddNewEmployee.jsp">Add new employee</a>
					</div>
				</c:if> 
				<ctg:employeeList />
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>