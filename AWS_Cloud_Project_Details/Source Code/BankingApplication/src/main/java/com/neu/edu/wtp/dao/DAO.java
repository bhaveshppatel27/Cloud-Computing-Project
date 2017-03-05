package com.neu.edu.wtp.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class DAO {

	private static final Logger log = Logger.getAnonymousLogger();

	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	//private static  SessionFactory sessionFactory ;
	protected DAO() {
	}

	public static Session getSession() {
		Session session = (Session) DAO.sessionThread.get();

		if (session == null) {
			session = sessionFactory.openSession();
			//session=getSessionFactory().openSession();
			DAO.sessionThread.set(session);
		}
		return session;
	}

	//-----------------------------------------------------------------------------
//	//Tried to use EhCache with below code:
	//But when used, An error comes up:org.hibernate.hql.internal.ast.QuerySyntaxException: User is not mapped [from User where userName= :username and password= :password]
//	private static SessionFactory buildSessionFactory() {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//            //System.out.println("Hibernate Configuration loaded");
//             
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            //System.out.println("Hibernate serviceRegistry created");
//             
//            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//             
//            return sessionFactory;
//        }
//        catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            ex.printStackTrace();
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//	
//	public static SessionFactory getSessionFactory() {
//        if(sessionFactory == null) sessionFactory = buildSessionFactory();
//        return sessionFactory;
//    }
	
	//-------------------------------------------------------------------------------------
	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot rollback", e);
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot close", e);
		}
		DAO.sessionThread.set(null);
	}

	public static void close() {
		getSession().close();
		DAO.sessionThread.set(null);
	}
}