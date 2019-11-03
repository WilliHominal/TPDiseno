package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Usuario;

public class DAOUsuario {
	private static DAOUsuario instanciaDAO = null;
	private static Session session = null;
	 
    private DAOUsuario() { }

    public static DAOUsuario getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOUsuario();
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

	public void addUsuario(Usuario u) {
        try {
            session.save(u);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
	}
}
