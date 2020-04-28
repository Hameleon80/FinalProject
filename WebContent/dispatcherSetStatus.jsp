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
				
<!--******************************************
	Form for set status of flight
*******************************************-->
				<table id="content-dispatcher-table">
					<tr>
						<td class="dispatcher-set-form">
							<h2>Status form</h2>
							<form id="setStatus" action="controller" method="post">
								<input type="hidden" name="command" value="setStatus" /> 
								First pilot: 
								<select name="flightStatus">
									<c:forEach items="${status_list}" var="status" >
										<option value="${status}">${status}</option>
									</c:forEach>
								</select><br>
								<input type="submit" value="Set status"/>
							</form>
						</td>
					</tr>
				</table>
			</td>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>