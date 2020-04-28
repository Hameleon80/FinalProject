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
				<h2>Order to Administrator</h2><br>
				<div class="makeOrderForm"><h3>description of the problem</h3>
				<form id="orderToAdmin" action="controller" method="post">
					<input type="hidden" name="command" value="dispatcherOrder"/>
					<textarea cols="50" rows="6" name="text" required></textarea><span></span><br><br>
					<input type="submit" value="Send">	
				</form>
				</div>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>