<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<link href="resources/css/styleы.css" rel="stylesheet" type="text/css" media="all" />
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<div align="center" class="tablewidth">
	<table class="table table-condensed" >
		<tr>
			<th align="right" width="20%">Имя </th>
			<td align="left" width="40%"> <c:out  value="${requestScope.person.getName()}" /></td>
		</tr>
		<tr>
			<th align="right">Фамилия </th>
			<td align="left"> <c:out  value="${requestScope.person.getSurname()}" /></td>
		</tr>
		<tr>
			<th align="right">Должность </th>
			<td align="left"> <c:out  value="${requestScope.person.getPosition().getName()}" /></td>
		</tr>
		<tr>
			<th align="right">Подразделение </th>
			<td align="left"> <c:out  value="${requestScope.person.getDepartment().getName()}" /></td>
		</tr>
	</table>
</div>
</body>  
</html>