package DAO.interfaces;

import java.util.List;

import beans.*;

public interface InterfacePersonDAO {
	List<Person> getPersons(String columnSort, String sort, String searchString, int NumPage);
	List<Department> getDepartments();
	List<Position> getPositions();
	
	Person getPersonById(int id);
	boolean deletePersonById(int id);
	
	Position getPositionById(int id);
	Department getDepartmentById(int id);

}
