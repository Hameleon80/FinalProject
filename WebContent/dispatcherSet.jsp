<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				<!--**************************
					Table for show flight
				***************************-->
				<table id="content-table2">
					<caption>Flight</caption>
					<tr>
						<th>Number</th>
						<th>Flight name</th>
						<th>From</th>
						<th>To</th>
						<th>Date</th>
					</tr>
					<tr class="content">
						<td><c:out value="${flight.number}" /></td>
						<td><c:out value="${flight.name}" /></td>
						<td><c:out value="${flight.cityFrom}" /></td>
						<td><c:out value="${flight.cityTo}" /></td>
						<td><c:out value="${flight.flightDate}" /></td>
					</tr>
				</table> 
				<c:choose>
					<c:when test="${crew==null}">
						<h2>Crew is not set</h2>
					</c:when>
					<c:when test="${crew!=null}">
						<table id="content-table2">
							<caption>Crew</caption>
							<tr>
								<th>First pilot</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getFirstPilot_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
							<tr>
								<th>Second pilot</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getSecondPilot_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
							<tr>
								<th>Navigator</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getNavigator_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
							<tr>
								<th>Radioman</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getRadioman_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
							<tr>
								<th>Stewardess #1</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getStewardess1_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
							<tr>
								<th>Stewardess #2</th>
								<c:forEach var="employee" items="${employee_list}">
									<c:if test="${employee.getId()==crew.getStewardess2_id()}">
										<td><c:out value="${employee}" /></td>
									</c:if>
								</c:forEach>
							</tr>
						</table>
					</c:when>
				</c:choose>
				<form id="crewSet" action="controller" method="post">
					<input type="hidden" name="command" value="getEmployeeList"/>
					<input type="submit" value="Change crew"/>
				</form>
				<c:choose>
				<c:when test="${status==null}">
					<h2>Status not set</h2>
				</c:when>
				<c:when test="${status!=null}">
					<table id="content-table2">
						<caption>Status</caption>
						<tr>
							<td><c:out value="${status}" /></td>
						</tr>
					</table>
				</c:when>
				</c:choose>
				<form id="statusSet" action="controller" method="post">
					<input type="hidden" name="command" value="getStatusList"/>
					<input type="submit" value="Change status"/>
				</form>
		</tr>
		<tr>
			<td class="centerText" colspan="3">
			<a href="dispatcherIndex.jsp">Choose next flight to change</a></td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>