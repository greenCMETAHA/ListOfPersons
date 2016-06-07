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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</head>
<body>

<form action="index" method="POST">
	<c:set  var="nameSearch" value="search" />

	<div class="input-group">
	  <span class="input-group-addon">Поиск:</span>
	  <input type="text" class="form-control" placeholder="найти..." name="search" value="${sessionScope[nameSearch]}">
	</div>
</form>
<br>
<div align="center">
	<table class="table table-striped" >
		<tr align="center">
			<td>Имя 
					<a href="index?column=name&sort=DESC"><span class="glyphicon glyphicon-chevron-up"></span></a>
					<a href="index?column=name&sort=ASC"><span class="glyphicon glyphicon-chevron-down"></span></a>
			</td>
			<td>Фамилия 
					<a href="index?column=surname&sort=DESC"><span class="glyphicon glyphicon-chevron-up"></span></a>
					<a href="index?column=surname&sort=ASC"><span class="glyphicon glyphicon-chevron-down"></span></a>
			</td>
			<td>Должность
					<a href="index?column=position.name&sort=DESC"><span class="glyphicon glyphicon-chevron-up"></span></a>
					<a href="index?column=position.name&sort=ASC"><span class="glyphicon glyphicon-chevron-down"></span></a>
			</td>
			<td>Подразделение
					<a href="index?column=department.name&sort=DESC"><span class="glyphicon glyphicon-chevron-up"></span></a>
					<a href="index?column=department.name&sort=ASC"><span class="glyphicon glyphicon-chevron-down"></span></a>
			</td>
			<td>...</td>
		</tr>
		<c:forEach var="current" items="${requestScope.list}">
			<tr>
				<td align="center">
					<a href="Open?id=${current.getId()}"><c:out value="${current.getName()}"/></a>
				</td>
				<td align="center">
					<a href="Open?id=${current.getId()}"><c:out value="${current.getSurname()}"/></a>
				</td>
				<td align="center">
					<a href="Open?id=${current.getId()}"><c:out value="${current.getPosition().getName()}"/></a>
				</td>
				<td align="center">
					<a href="Open?id=${current.getId()}"><c:out value="${current.getDepartment().getName()}"/></a>
				</td>
				<td align="center">
					<a href="Open?id=${current.getId()}&variant=Edit" title="Редактировать"><span class="glyphicon glyphicon-pencil"></span></a> 
					<a href="Delete?id=${current.getId()}" title="Удалить"><span class="glyphicon glyphicon-remove"></a>
					
					<button class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Большая модаль</button>
					
					<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-lg">
					    <div class="modal-content">
					      ...
					    </div>
					  </div>
					</div>	
					
					
<!-- 					Button trigger modal -->
<!-- <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"> -->
<!--         Посмотреть демо -->
<!--       </button> -->

<!-- <!-- Modal --> -->
<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
<!--         <h4 class="modal-title" id="myModalLabel">Название модали</h4> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         ... -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button> -->
<!--         <button type="button" class="btn btn-primary">Сохранить изменения</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div>				 -->
					
				</td>
			
			</tr>
		</c:forEach>
	</table>
</div>
<br><p>

	<form action="Open" method="POST">
		<input hidden="hidden" name="variant" value="Create">
		<div align="center">
			<input type="submit" value="Новый сотрудник">
		</div>
	</form>
</body>  
</html>