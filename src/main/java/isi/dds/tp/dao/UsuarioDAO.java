package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.conectar.HibernateUtil;
import isi.dds.tp.modelo.Usuario;

public class UsuarioDAO {
	
	private static UsuarioDAO instanciaDAO = null;
	 
    private UsuarioDAO() {

    }

    public static UsuarioDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new UsuarioDAO();
        }    
        return instanciaDAO;
    }


	public void addUsuario(Usuario u) {
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
        
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
