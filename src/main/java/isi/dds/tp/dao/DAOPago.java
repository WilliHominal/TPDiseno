package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Pago;

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
            session.getTransaction().rollback();	
		}
        session.close();
    }
    
    public void addPago (Pago pago) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
        	session.beginTransaction();
            session.save(pago);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
    }

}
