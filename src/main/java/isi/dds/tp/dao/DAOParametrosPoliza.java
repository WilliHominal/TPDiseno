package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.ParametrosPoliza;

public class DAOParametrosPoliza {
	private static DAOParametrosPoliza instanciaDAO = null;
	private static Session session = null;
	 
    private DAOParametrosPoliza() { }

    public static DAOParametrosPoliza getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOParametrosPoliza();
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
    
	public void cargarParametrosPoliza() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/parametrosPoliza.sql");
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

	public ParametrosPoliza getUltimoParametrosPoliza() {
        try {
            return session.createNativeQuery("SELECT * FROM parametros_poliza where fin_vigencia is NULL", ParametrosPoliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
	}

	public void updateParametrosPoliza(ParametrosPoliza p) {
		try {
            session.update(p);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
	}

	public void addParametrosPoliza(ParametrosPoliza p) {
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		try {
			session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
		session.close();
	}
}
