<%@ include file="WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>

<c:set var="title" value="Main" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="m-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
		<%@ include file="/WEB-INF/jspf/left_menu.jspf" %>
			<td class="centerText">
				
				<form id="settings_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateSettings" />
					<b><fmt:message key="settings_jsp.label.set_locale"/>:</b><br><br>
					<div>
						
						<select name="locale">
							<c:forEach var="locale" items="${applicationScope.locales}">
								<c:set var="selected" value="${locale.key==currentLocale ? 'selected' : ''}" />
								<option value="${locale.key}" ${selected}>${locale.value}</option>
							</c:forEach>
						</select>
					</div><br>
					<input type="submit" value="<fmt:message key="settings_jsp.form.submit_save_locale"/>"><br/>
				</form> 
				<a href="index.jsp"><fmt:message key="settings_jsp.link.back_to_main_page"></fmt:message></a>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>