package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
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

	public void addPago(Pago pago) {
        try {
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
