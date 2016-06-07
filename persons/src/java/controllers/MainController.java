package controllers;

import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import DAO.BaseDAO;
import beans.Person;

@Controller
@SessionAttributes({"columnSort", "sort"})
public class MainController {
	@Autowired
	BaseDAO baseDAO;
	
	@RequestMapping(value = { "/", "/index", "/Index"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String index(
			@RequestParam(value = "variant", defaultValue="", required=false) String variant,
			@RequestParam(value = "search", defaultValue="", required=false) String searchString,
			@RequestParam(value = "column", defaultValue="", required=false) String column,
			@RequestParam(value = "sort", defaultValue="", required=false) String sort,
			@RequestParam(value = "numPage", defaultValue="1", required=false) int numPage,
			HttpServletRequest request,Locale locale, Model model) {
		
		HttpSession session=request.getSession();
		
		if (column.length()==0){
			column =  (String) session.getAttribute("columnSort");
			if (column==null){
				column=getMainColumn();
			}		
		}
		if (sort.length()==0){
			sort =  (String) session.getAttribute("sort");
			if (sort==null){
				sort=getSortVariant();
			}		
		}

		model.addAttribute("list", baseDAO.getPersons(column,sort, searchString, numPage));
		model.addAttribute("mainText", "Список сотрудников");
		session.setAttribute("columnSort", column);
		session.setAttribute("sort", sort);
		session.setAttribute("search", searchString);
		
		return "Index";
	}		

	@RequestMapping(value = { "/Open"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String open(
			@RequestParam(value = "id", defaultValue="0", required=false) int id,
			@RequestParam(value = "variant", defaultValue="Open", required=false) String variant, // Open/Edit/Create
			HttpServletRequest request,Locale locale, Model model) {
		
		HttpSession session=request.getSession();
		
		if ("Open".equals(variant)){
			model.addAttribute("person", baseDAO.getPersonById(id));
			model.addAttribute("mainText", "Данные о сотруднике:");
		}else{
			model.addAttribute("departmentList", baseDAO.getDepartments());
			model.addAttribute("positionList", baseDAO.getPositions()); 
			
			if ("Create".equals(variant)){
				model.addAttribute("person", new Person());
				variant="Edit";							//одна страница для 2-х режимов
				model.addAttribute("mainText", "Новый сотрудник:");
			}else if ("Edit".equals(variant)){
				model.addAttribute("person", baseDAO.getPersonById(id));
				model.addAttribute("mainText", "Изменить сотрудника:");
			}
		}
		
		return variant;
	}
	
	@RequestMapping(value = { "/save"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String save(
			@RequestParam(value = "id", defaultValue="", required=false) int id,
			@RequestParam(value = "name", defaultValue="", required=false) String name,
			@RequestParam(value = "surname", defaultValue="", required=false) String surname,
			@RequestParam(value = "positionId", defaultValue="0", required=false) int positionId,
			@RequestParam(value = "departmentId", defaultValue="0", required=false) int departmentId,
			@RequestParam(value = "variant", defaultValue="", required=false) String variant,
			HttpServletRequest request,Locale locale, Model model) {
		
		if ("Сохранить".equals(variant)){
			Person currentPerson=baseDAO.getPersonById(id);
			if (currentPerson==null){
				currentPerson=new Person();
			}
			currentPerson.setName(name);
			currentPerson.setSurname(surname);
			currentPerson.setPosition(baseDAO.getPositionById(positionId));
			currentPerson.setDepartment(baseDAO.getDepartmentById(departmentId));
			baseDAO.savePerson(currentPerson);
		}
		
		return "redirect:index";
	}	
	
	@RequestMapping(value = { "/Delete"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String delete(
			@RequestParam(value = "id", defaultValue="", required=false) int id,
			HttpServletRequest request,Locale locale, Model model) {
		
		baseDAO.deletePersonById(id);
		
		return "redirect:index";
	}
	
	//------------------------------------------------------------

	@ModelAttribute("columnSort")
	public String getMainColumn(){
		
		return "surname";
	}	
	
	
	@ModelAttribute("sort")
	public String getSortVariant(){
		
		return "DESC";
	}

}
