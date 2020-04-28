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
			<td id="advancedSearch">
			<div>
				<h2>Choose a flight to set/change</h2>
				<form id="search_by_number_form" action="controller" method="post">
					<input type="hidden" name="command" value="dispatcherSearch"/>
					City from: <input name="cityFrom" required/><span></span><br>
					City to: <input name="cityTo" required/><span></span><br>
					Date: <input type="date" name="date" required/><span></span><br><br>
					<input type="submit" value="search">	
				</form>
				</div>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>