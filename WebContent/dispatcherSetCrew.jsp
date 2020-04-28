<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.entity.Employee"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				
<!--******************************************
	Form for set crew of flight
*******************************************-->
				<table id="content-dispatcher-table">
					<tr>
						<td class="dispatcher-set-form">
							<h2>Crew form</h2>
							<form id="setCrew" action="controller" method="post">
								<input type="hidden" name="command" value="setCrew" /> 
								First pilot: 
								<select name="firstPilot">
									<c:forEach items="${employee_list}" var="employee" >
										<c:if test="${employee.getPostId() == 0}">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br>
								Second pilot: 
								<select name="secondPilot">
									<c:forEach items="${employee_list}" var="employee" >
										<c:if test="${employee.getPostId() == 0}">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br>
								Navigator: 
								<select name="navigator">
									<c:forEach items="${employee_list}" var="employee">
										<c:if test="${employee.getPostId() == 1 }">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br>
								Radioman: 
								<select name="radioman">
									<c:forEach items="${employee_list}" var="employee">
										<c:if test="${employee.getPostId() == 2}">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br>
								Stewardess #1: 
								<select name="stewardess1">
									<c:forEach items="${employee_list}" var="employee">
										<c:if test="${employee.getPostId() == 3}">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br>
								Stewardess #2: 
								<select name="stewardess2">
									<c:forEach items="${employee_list}" var="employee">
										<c:if test="${employee.getPostId()== 3}">
											<option value="${employee.getId()}">${employee}</option>
										</c:if>
									</c:forEach>
								</select><br><br>
								<input type="submit" value="Set crew"/>
								
							</form>
						</td>
					</tr>
				</table>
			</td>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>