<%@ include file="WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.db.OrderStatus"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf" %>
			<td class="centerText">
				<table id="content-table">
					<tr>
						<th>Number</th>
						<th>Description</th>
						<th>Status</th>
						<th>Change status</th>
					</tr>
					<c:forEach var="order" items="${order_list}">
						<tr class="content">
							<td><c:out value="${order.id}" /></td>
							<td><c:out value="${order.description}" /></td>
							<td><c:out value="${OrderStatus.values()[order.statusId].getName()}" /></td>
							<td>
							<form id="changeOrderStatus" action="controller" method="post">
								<input type="hidden" name="command" value="changeOrderStatus">
								<input type="hidden" name="orderId" value="${order.id}">
								<select name="orderStatus">
									<c:forEach var="status" items="${OrderStatus.values() }">
										<option value="${status.ordinal()}">${status.getName()}</option>
									</c:forEach>
								</select>
								<input type="submit" value="ok">
							</form>
							</td>
 
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>