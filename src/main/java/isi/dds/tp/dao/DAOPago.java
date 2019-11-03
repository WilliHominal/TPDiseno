package isi.dds.tp.dao;

import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;

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
}
