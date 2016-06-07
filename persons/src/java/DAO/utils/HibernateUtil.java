/*
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 *
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 *
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 */

package DAO.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static HibernateUtil util = null;

    private static Logger log = Logger.getLogger(HibernateUtil.class);

    private SessionFactory sessionFactory = null;

  	private final ThreadLocal sessions = new ThreadLocal();

    private HibernateUtil() {
        try {
            sessionFactory = new Configuration().
                    configure().buildSessionFactory();

		} catch (Throwable ex) {
		    log.error("Initial SessionFactory creation failed." + ex);
            System.out.println(ex);
            System.exit(0);
        }
    }

    public Session getSession () {
        Session session = (Session) sessions.get();
        try {
            if (session == null) {
                session = sessionFactory.openSession();
                sessions.set(session);
            }
        } catch (Exception ex){
            log.error("Initial SessionFactory creation failed." + ex);
            System.out.println(ex);
        }

        return session;
    }

    public static synchronized HibernateUtil getHibernateUtil(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }

    public static HibernateUtil getUtil() {
  		return util;
  	}

  	public static void setUtil(HibernateUtil util) {
  		HibernateUtil.util = util;
  	}

  	public static Logger getLog() {
  		return log;
  	}

  	public static void setLog(Logger log) {
  		HibernateUtil.log = log;
  	}

  	public SessionFactory getSessionFactory() {
  		return sessionFactory;
  	}

  	public void setSessionFactory(SessionFactory sessionFactory) {
  		this.sessionFactory = sessionFactory;
  	}

  	public ThreadLocal getSessions() {
  		return sessions;
  	}    
}