package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.interfaces.InterfacePersonDAO;
import beans.Department;
import beans.Person;
import beans.Position;
import utils.Utils;
import org.hibernate.Transaction;
import DAO.utils.HibernateUtil;

import org.apache.log4j.Logger;

@Repository
public class BaseDAO extends DAO implements InterfacePersonDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
    private static BaseDAO sessionDAO;
    
    private static Logger log = Logger.getLogger(BaseDAO.class);  
    
    /* variable for gettig text constants */
    private static Utils Constants;
    
    private Transaction transaction = null;
    
    
    public BaseDAO() {
        Constants = Utils.start();
    }

    /* Method for realization of pattern Singleton */
    public static BaseDAO startDAO(){
        if(sessionDAO == null)
            sessionDAO = new BaseDAO();

        if (sessionDAO==null) //ooops...
        	log.error("Can not open the BaseDAO class");

        return sessionDAO;
    }
    
    //--------------------------------
    
    
    public List<Person> getPersons(String columnSort, String sort, String searchString, int numPage) {
		ArrayList<Person> result=new ArrayList<Person>();

    	HibernateUtil util = HibernateUtil.getHibernateUtil();
        Session session = util.getSession();

        try {
	        transaction = session.beginTransaction();
	        Query query=session.createQuery("from Person "
	        		+(searchString.length()>0?"where (name like CONCAT('%', :searchStr, '%')) or (surname like CONCAT('%', :searchStr, '%'))":"")
	        		+" order by "+columnSort+" "+sort);
	        if (searchString.length()>0){
	        	query.setParameter("searchStr", searchString);
	        }
	        query.setFirstResult(Utils.PERSONS_ON_PAGE*(numPage-1));
	        query.setMaxResults(Utils.PERSONS_ON_PAGE);
	        result=(ArrayList)query.list();
	        transaction.commit();
        } catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getPersons("+columnSort+","+sort+"): " + e);
	        transaction.rollback();
	        //			throw new DAOException(e);
        }
        return result;	
    }

	public List<Department> getDepartments() {
		ArrayList<Department> result=new ArrayList<Department>();

    	HibernateUtil util = HibernateUtil.getHibernateUtil();
        Session session = util.getSession();

        try {
	        transaction = session.beginTransaction();
	        Query query=session.createQuery("from Department order by name"); 
	        result=(ArrayList)query.list();
	        transaction.commit();
        } catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getDepartments(): " + e);
	        transaction.rollback();
        }
        return result;	
    }

	public List<Position> getPositions() {
		ArrayList<Position> result=new ArrayList<Position>();

    	HibernateUtil util = HibernateUtil.getHibernateUtil();
        Session session = util.getSession();

        try {
	        transaction = session.beginTransaction();
	        Query query=session.createQuery("from Position order by name"); 
	        result=(ArrayList)query.list();
	        transaction.commit();
        } catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getPositions(): " + e);
	        transaction.rollback();
        }
        return result;	
	}

	public Person getPersonById(int id) {
		Person result = new Person();
		
		HibernateUtil util = HibernateUtil.getHibernateUtil();
		Session session = util.getSession();
		try {
			result=(Person) session.get(Person.class, id); //.load(Person.class, id);
		} catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getPersonById("+id+"): " + e);
	        transaction.rollback();
		}
		return result;
	}
	
	public boolean deletePersonById(int id) {
		boolean result = false;
		
		HibernateUtil util = HibernateUtil.getHibernateUtil();
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete((Person) session.get(Person.class, id));
			result=true;
			transaction.commit();
		} catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't deletePersonById("+id+"): " + e);
	        transaction.rollback();
		}
		return result;
	}

	public boolean savePerson(Person currentPerson) {
		boolean result = false;
		
		HibernateUtil util = HibernateUtil.getHibernateUtil();
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(currentPerson);
			result=true;
			transaction.commit();
		} catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't savePerson("+currentPerson.getId()+","+currentPerson.getSurname()+"): " + e);
	        transaction.rollback();
		}
		return result;
	}
	
	

	@Override
	public Position getPositionById(int id) {
		Position result = new Position();
		
		HibernateUtil util = HibernateUtil.getHibernateUtil();
		Session session = util.getSession();
		
		try {
			result=(Position) session.get(Position.class, id);
		} catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getPositionById("+id+"): " + e);
	        transaction.rollback();
		}
		return result;
		}

	@Override
	public Department getDepartmentById(int id) {
		Department result = new Department();
		
		HibernateUtil util = HibernateUtil.getHibernateUtil();
		Session session = util.getSession();
		try {
			result=(Department) session.get(Department.class, id);
		} catch (HibernateException e) {
        	System.out.println(e);
        	System.out.println(e.getCause().toString());
	        log.error("Error: can't getDepartmentById("+id+"): " + e);
	        transaction.rollback();
		}
		return result;
	}
	
	
	
}
