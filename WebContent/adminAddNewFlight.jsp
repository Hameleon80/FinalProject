<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.db.FlightStatus" %>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!--=========================  
	Include JS file
==========================-->
<script src="js/valid.js"></script>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				<table id="content-dispatcher-table">
					<tr>
						<td>
							<h3>Add new flight</h3>
							<div class="alignRight width50">
								<form id="adminEditFlight" action="controller" onsubmit="return validateForm()" method="post">
									<input type="hidden" name="command" value="addNewFlight" />
									Number: <input name="number"><br>
									Flight name: <input name="name"><br>
									City from: <input name="cityFrom" ><br>
									City to: <input name="cityTo" ><br>
									Date: <input type="date" name="date" ><br>
									Status: 
									<select name="status">
										<c:forEach var="status" items="${FlightStatus.values()}">
											<option>${status.getName()}</option>
										</c:forEach>
									</select>
									<br> <input type="submit" value="Add"><br>
								</form>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>