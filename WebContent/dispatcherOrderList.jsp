<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.db.OrderStatus"%>

<html>

<c:set var="title" value="Flights list" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText"><span><a
					href="dispatcherMakeOrder.jsp">Add new Order</a></span> 
			 <!--===============================
				Show table with Orders list
			 =================================-->
				<c:if test="${not empty order_list}">
				<table id="content-table">
					<tr>
						<th>Number</th>
						<th>Description</th>
						<th>Status</th>
					</tr>
					
						<c:forEach var="order" items="${order_list}">
							<tr class="content">
								<td><c:out value="${order.id}" /></td>
								<td><c:out value="${order.description}" /></td>
								<td><c:out value="${OrderStatus.values()[order.statusId].getName()}" /></td>
							</tr>
						</c:forEach>
					
				</table>
				</c:if>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>