package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cuota;

public class DAOPago {
	private static DAOPago instanciaDAO = null;
	private static Session session = null;
	 
    private DAOPago() { }

    public static DAOPago getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOPago();
        }
        if(session == null) {
    		session = HibernateUtil.getSessionFactoryValidate().openSession();
        	session.beginTransaction();
        }        
        return instanciaDAO;
    }

    public static void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
    
    public void removeCuota (Cuota cuota) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
        	session.beginTransaction();
            session.delete(cuota);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
    }
    
    public void addCuota (Cuota cuota) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
        	session.beginTransaction();
            session.save(cuota);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
    }
    
    public void cargarCuotas() {
    	ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/cuotas.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		try {
			session.beginTransaction();
			Iterator<String> iteradorqueries = queries.iterator();
			while(iteradorqueries.hasNext()){
				session.createSQLQuery(iteradorqueries.next()).executeUpdate();
			}
		}catch (HibernateException e) {
			e.printStackTrace();
            session.getTransaction().rollback();	
		}
		session.close();
    }
}
