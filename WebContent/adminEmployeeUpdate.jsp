<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.ahtirskiy.finalProject.db.Post"%>

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
								<h3>Edit employee</h3>
								<form id="updateEmployee" action="controller" onsubmit="return validateForm()" method="post">
									<input type="hidden" name="command" value="updateEmployee">
									<input type="hidden" name="id" value="${employee.getId()}"/>
									First name: <input name="firstName" value="${employee.getFirstName()}" ><br> 
									Last name: <input name="lastName" value="${employee.getLastName()}" ><br>
									Post:
									<select name="post">
										<c:forEach items="${Post.values()}" var="post"> 
											<option value="${post}">${post.getName()}</option>
										</c:forEach>
									</select><br><br>
									<input type="submit" value="Edit"><br>
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