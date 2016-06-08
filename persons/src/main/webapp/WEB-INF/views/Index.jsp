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
			<td><span class="glyphicon glyphicon-eye-open"></td>
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
					<button class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg-${current.getId()}">
						<span class="glyphicon glyphicon-eye-open">
					</button>
					
					<div class="modal fade bs-example-modal-lg-${current.getId()}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-lg">
					    <div class="modal-content">
	

								<div align="center">
									<table >
										<tr>
											<th align="right" width="20%">Имя </th>
											<td align="left" width="40%"> <c:out  value="${current.getName()}" /></td>
										</tr>
										<tr>
											<th align="right">Фамилия </th>
											<td align="left"> <c:out  value="${current.getSurname()}" /></td>
										</tr>
										<tr>
											<th align="right">Должность </th>
											<td align="left"> <c:out  value="${current.getPosition().getName()}" /></td>
										</tr>
										<tr>
											<th align="right">Подразделение </th>
											<td align="left"> <c:out  value="${current.getDepartment().getName()}" /></td>
										</tr>
									</table>
								</div>
									
					     
					    </div>
					  </div>
					</div>	
				</td>
				<td align="center"><c:out value="${current.getName()}"/>
				</td>
				<td align="center"><c:out value="${current.getSurname()}"/>
				</td>
				<td align="center">
					<c:out value="${current.getPosition().getName()}"/>
				</td>
				<td align="center">
					<c:out value="${current.getDepartment().getName()}"/>
				</td>
				<td align="center">
					<button class="btn btn-primary" data-toggle="modal" data-target="#myModal-${current.getId()}">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
					<form action="Delete" method="POST">
						<input name="id" value="${current.getId()}" hidden>
						<button class="btn btn-primary" >
							<span class="glyphicon glyphicon-remove">
						</button>
					</form>
					
					<form action="save" method="post">
					<!-- Modal -->
					<div class="modal fade" id="myModal-${current.getId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						        <h4 class="modal-title" id="myModalLabel">Изменить сотрудника:</h4>
						      </div>
						      <div class="modal-body">
						        
	
								<table>
									<tr>
										<td align="right">Имя </td>
										<td align="left">
											<input hidden="hidden" name="id" value="${current.getId()}">
											<input name="name" type="text" class="form-control" value="${current.getName()}">
										</td>
									</tr>
									<tr>
										<td align="right">Фамилия </td>
										<td align="left">
											<input name="surname" type="text" class="form-control" value="${current.getSurname()}">
										</td>
									</tr>
									<tr>
										<td align="right">Должность </td>
										<td align="left">
											<select name="positionId" class="form-control">
											    <option disabled>Выберите должность </option>
											    <c:forEach var="currentPosition" items="${requestScope.positionList}">
											    	<c:if test="${current.getPosition().getId()==currentPosition.getId()}">
											    		<option selected value="${currentPosition.getId()}">
											    			<c:out value="${currentPosition.getName()}"/> 
											    		</option>
											    	</c:if>	
											    	<c:if test="${current.getPosition().getId()!=currentPosition.getId()}">
											    		<option value="${currentPosition.getId()}">
											    			<c:out value="${currentPosition.getName()}"/> 
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
											    <c:forEach var="currentDepartment" items="${requestScope.departmentList}">
											    	<c:if test="${current.getDepartment().getId()==currentDepartment.getId()}">
											    		<option selected value="${currentDepartment.getId()}">
											    			<c:out value="${currentDepartment.getName()}"/> 
											    		</option>
											    	</c:if>	
											    	<c:if test="${current.getDepartment().getId()!=currentDepartment.getId()}">
											    		<option value="${currentDepartment.getId()}">
											    			<c:out value="${currentDepartment.getName()}"/> 
											    		</option>
											    	</c:if>	
											    </c:forEach>
										   </select>			
									</tr>
							</table>
	
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" >Закрыть</button>
						        <button type="submit" class="btn btn-primary" >Сохранить изменения</button>
						      </div>
						    </div>
						  </div>
						 
					</div>						
					</form>	
				</td>
			
			</tr>
		</c:forEach>
	</table>
</div>
<br><p>

	<button class="btn btn-primary" data-toggle="modal" data-target="#myModal-A">
		<span class="glyphicon glyphicon-plus"></span>Создать нового сотрудника
	</button>

	<div class="modal fade" id="myModal-A" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">Изменить сотрудника:</h4>
		      </div>
		      <form action="save" method="POST">
		      <div class="modal-body">
		        

				<table>
					<tr>
						<td align="right">Имя </td>
						<td align="left">
							<input hidden="hidden" name="id" value="">
							<input name="name" type="text" class="form-control" value="">
						</td>
					</tr>
					<tr>
						<td align="right">Фамилия </td>
						<td align="left">
							<input name="surname" type="text" class="form-control" value="">
						</td>
					</tr>
					<tr>
						<td align="right">Должность </td>
						<td align="left">
							<select name="positionId" class="form-control">
							    <option disabled>Выберите должность </option>
							    <c:forEach var="currentPosition" items="${requestScope.positionList}">
						    		<option value="${currentPosition.getId()}">
						    			<c:out value="${currentPosition.getName()}"/> 
						    		</option>
						    </c:forEach>
					   </select>				
					</td>
				</tr>
				<tr>
					<td align="right">Подразделение </td>
					<td align="left">
						<select name="departmentId" class="form-control">
						    <option disabled>Выберите подразделение</option>
						    <c:forEach var="currentDepartment" items="${requestScope.departmentList}">
					    		<option value="${currentDepartment.getId()}">
					    			<c:out value="${currentDepartment.getName()}"/> 
					    		</option>
						    </c:forEach>
					   </select>			
				</tr>
			</table>

	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal" >Закрыть</button>
	        <button type="submit" class="btn btn-primary" >Сохранить изменения</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>						
</body>  
</html>