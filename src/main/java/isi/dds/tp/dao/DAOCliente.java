package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cliente;

public class DAOCliente {
	private static DAOCliente instanciaDAO = null;
	private static Session session = null;
	
    private DAOCliente() { }

    public static DAOCliente getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOCliente();
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
    
	public void cargarClientes() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/clientes.sql");
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
    
    public Cliente getCliente(Long numeroCliente) {
    	Cliente c = null;
		try {
            c = session.get(Cliente.class, numeroCliente);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }	
        session.close();
    	return c;
    }
    
	public List<Cliente> getClientes(String consulta) {
    	List<Cliente> c = null;
		try {
        	c = session.createNativeQuery("SELECT * FROM cliente "+consulta, Cliente.class).getResultList();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return c;
    }

	public void updateCliente(Cliente cliente) {
    	try {
            session.update(cliente);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
	} 
}
