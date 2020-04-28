<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<html>
<c:set var="title" value="Search by number" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<%@ include file="/WEB-INF/jspf/left_menu.jspf"%>
			<td class="centerText">
				<h2>Search by number</h2><br>
				<form id="search_by_number_form" action="controller" method="post">
					<input type="hidden" name="command" value="searchByNumber"/>
					<input name="number" required/><span></span><br><br>
					<input type="submit" value="search">	
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>