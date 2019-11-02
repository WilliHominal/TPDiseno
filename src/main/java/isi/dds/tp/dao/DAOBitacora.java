package isi.dds.tp.dao;

import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;

public class DAOBitacora {
	
	private static DAOBitacora instanciaDAO = null;
	private static Session session = null;
	 
    private DAOBitacora() { }

    public static DAOBitacora getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOBitacora();
        }            
        if(session == null) {
    		session = HibernateUtil.getSessionFactoryValidate().openSession();
        	session.beginTransaction();
        }
        return instanciaDAO;
    }
    
    public void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
}
