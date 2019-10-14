package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Cliente;

public class ClienteDAO {
	
	private static ClienteDAO instanciaDAO = null;
	 
    private ClienteDAO() {

    }

    public static ClienteDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ClienteDAO();
        }    
        return instanciaDAO;
    }

    public void addCliente(Cliente c) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
    public Cliente getCliente(Long numeroCliente) {
    	Cliente c = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            c = session.get(Cliente.class, numeroCliente);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	    	
    	return c;
    }
    
}
