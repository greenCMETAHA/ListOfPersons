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
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<div align="center" class="panel panel-default">
	<div class="panel-body">
	<form action="save" method="post">
		<c:set var="currentPerson" value="${requestScope.person}"/>
	
		<table>
			<tr>
				<td align="right">Имя </td>
				<td align="left">
					<input hidden="hidden" name="id" value="${currentPerson.getId()}">
					<input name="name" type="text" class="form-control" value="${currentPerson.getName()}">
				</td>
			</tr>
			<tr>
				<td align="right">Фамилия </td>
				<td align="left">
					<input name="surname" type="text" class="form-control" value="${currentPerson.getSurname()}">
				</td>
			</tr>
			<tr>
				<td align="right">Должность </td>
				<td align="left">
					<select name="positionId" class="form-control">
					    <option disabled>Выберите должность </option>
					    <c:forEach var="current" items="${requestScope.positionList}">
					    	<c:out value="${current.getId()}"/>
					    	<c:if test="${currentPerson.getPosition().getId()==current.getId()}">
					    		<option selected value="${current.getId()}">
					    			<c:out value="${current.getName()}"/> 
					    		</option>
					    	</c:if>	
					    	<c:if test="${currentPerson.getPosition().getId()!=current.getId()}">
					    		<option value="${current.getId()}">
					    			<c:out value="${current.getName()}"/> 
					    		</option>
					    	</c:if>	
					    </c:forEach>
				   </select>				
				</td>
			</tr>
			<tr>
				<td align="right">Подразделение </td>
				<td align="left">
					<select name="departmentId" class="form-control">
					    <option disabled>Выберите подразделение</option>
					    <c:forEach var="current" items="${requestScope.departmentList}">
					    	<c:if test="${currentPerson.getDepartment().getId()==current.getId()}">
					    		<option selected value="${current.getId()}">
					    			<c:out value="${current.getName()}"/> 
					    		</option>
					    	</c:if>	
					    	<c:if test="${currentPerson.getDepartment().getId()!=current.getId()}">
					    		<option value="${current.getId()}">
					    			<c:out value="${current.getName()}"/> 
					    		</option>
					    	</c:if>	
					    </c:forEach>
				   </select>			
			</tr>
			<tr>
			
				<td align="right"><br> <input type="submit" value="Сохранить" name="variant"></td>
				<td align="left"><br><input type="submit" value="Отмена" name="variant2"></td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body>  
</html>