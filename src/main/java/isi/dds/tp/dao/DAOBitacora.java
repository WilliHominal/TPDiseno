package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.BitacoraParametrosPoliza;

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
    
    public static void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
    
    public void addBitacora(BitacoraParametrosPoliza bitacora) {
        try {
            session.save(bitacora);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
    }

	public void update(BitacoraParametrosPoliza bitacora) {
        try {
            session.update(bitacora);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
	}
}
