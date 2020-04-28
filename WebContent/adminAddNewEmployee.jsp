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
								<h3>Add new employee</h3>
								<form id="addEmployee" action="controller" onsubmit="return validateForm()" method="post">
									<input type="hidden" name="command" value="addNewEmployee">
									First name: <input id="val1" type="text" name="firstName"><br> 
									Last name: <input id="val2" type="text" name="lastName"><br>
									Post:
									<select name="post">
										<c:forEach items="${Post.values()}" var="post"> 
											<option value="${post}">${post.getName()}</option>
										</c:forEach>
									</select><br><br>
									<input type="submit" value="Add"><br>
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