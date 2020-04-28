<%@ include file="WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				<table id="content-table">
					<thead>
					<tr>
						<th>Number</th>
						<th>Flight name</th>
						<th>From</th>
						<th>To</th>
						<th>Date</th>
					</tr>
					</thead>
						<tr class="content">
							<td><c:out value="${flight.number}" /></td>
							<td><c:out value="${flight.name}" /></td>
							<td><c:out value="${flight.cityFrom}" /></td>
							<td><c:out value="${flight.cityTo}" /></td>
							<td><c:out value="${flight.flightDate}" /></td>
						</tr>
				</table>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>