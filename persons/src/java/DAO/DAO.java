package DAO;

import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import DAO.interfaces.InterfaceDAO;
import DAO.utils.HibernateUtil;
import utils.Utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DAO<T> implements InterfaceDAO<T> {
	/* implemented from interface InterfaceDAO */

	private SessionFactory sessionFactory;
    /* variable for working with class. Pattern Singleton*/
    private static DAO sessionDAO;

    private Transaction transaction = null;

    /* variable for gettig text constants */
    private static Utils Constants;
    
    private static Logger log = Logger.getLogger(DAO.class);    


    public DAO() {
        Constants = Utils.start();
    }

    /* Method for realization of pattern Singleton */
    public static DAO startDAO(){
        if(sessionDAO == null)
            sessionDAO = new DAO();

        if (sessionDAO==null) //ooops...
        	log.error("Can not open the database");

        return sessionDAO;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void saveOrUpdate(T t) throws DAOException{
        try {
            HibernateUtil util = HibernateUtil.getHibernateUtil();
            Session session = util.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
        } catch (Exception e) {
        	System.out.println("-- "+e);
        	System.out.println("-- "+e.getCause());
//        } catch (HibernateException e) {
//            log.error("Error: can't save or update object in Dao" + e);
//            transaction.rollback();
//            throw new DAOException(e);
        }
    }

    public T get(Class objectClass, Serializable id) throws DAOException {
        T t = null;
        try {
            HibernateUtil util = HibernateUtil.getHibernateUtil();
            Session session = util.getSession();
            transaction = session.beginTransaction();
            t = (T) session.get(objectClass, id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error("Error: can't get " + getPersistentClass() + " in Dao" + e);
            throw new DAOException(e);
        }
        return t;
    }

    public T load(Class objectClass, Serializable id) throws DAOException {
        T t = null;
        try {
            HibernateUtil util = HibernateUtil.getHibernateUtil();
            Session session = util.getSession();
            transaction = session.beginTransaction();
            t = (T) session.load(objectClass, id);
            session.isDirty();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in Dao" + e);
            transaction.rollback();
            throw new DAOException(e);
        }
        return t;
    }

    public void delete(T t) throws DAOException {
        try {
            HibernateUtil util = HibernateUtil.getHibernateUtil();
            Session session = util.getSession();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error: can't delete in Dao" + e);
            transaction.rollback();
            throw new DAOException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }




}

