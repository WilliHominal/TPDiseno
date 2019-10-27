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
	 
    private DAOCliente() { }

    public static DAOCliente getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOCliente();
        }    
        return instanciaDAO;
    }

    public void addCliente(Cliente c) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
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
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.get(Cliente.class, numeroCliente);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }	
    	return null;
    }
    
	public List<Cliente> getClientes(String consulta) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            return session.createNativeQuery("SELECT * FROM cliente where (condicion='ACTIVO' or condicion='PLATA') "+consulta, Cliente.class).getResultList();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }
	
	public void cargarClientes() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/clientes.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		
		session.beginTransaction();
		
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){

			session.createSQLQuery(iteradorqueries.next()).executeUpdate();

		}
		session.close();
	}
    
}
