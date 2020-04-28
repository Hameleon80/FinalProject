<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Advanced search" />
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
			<td id="advancedSearch">
			<div>
				<h2>Advanced search</h2><br>
				<form id="advanced-search" action="controller" onsubmit="return validateForm()" method="post">
					<input type="hidden" name="command" value="advancedSearch"/>
					City from: <input name="cityFrom" /><br>
					City to: <input name="cityTo" /><br>
					Date: <input type="date" name="date" /><br>
					<input type="submit" value="search">	
				</form>
				</div>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>