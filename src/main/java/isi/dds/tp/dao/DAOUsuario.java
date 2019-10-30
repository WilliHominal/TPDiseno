package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Usuario;

public class DAOUsuario {
	
	private static DAOUsuario instanciaDAO = null;
	 
    private DAOUsuario() {

    }

    public static DAOUsuario getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOUsuario();
        }    
        return instanciaDAO;
    }


	public void addUsuario(Usuario u) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
	}

}
