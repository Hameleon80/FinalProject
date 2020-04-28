<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>

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
							<div class="alignRight width50">
								Edit flight № ${flight.number} "${flight.name}"<br><br>
								<form id="adminEditFlight" action="controller" onsubmit="return validateForm()" method="post">
									<input type="hidden" name="command" value="updateFlight" />
									<input type="hidden" name="id" value="${flight.id}" />
									Number: <input name="number" value="${flight.number}" ><br>
									Flight name: <input name="name" value="${flight.name}" ><br>
									City from: <input name="cityFrom" value="${flight.cityFrom}" ><br>
									City to: <input name="cityTo" value="${flight.cityTo}" ><br>
									Date: <input name="date" value="${flight.flightDate}" ><br>
									<br> <input type="submit" value="Edit"><br>
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