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
	 
    private DAOParametrosPoliza() { }

    public static DAOParametrosPoliza getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOParametrosPoliza();
        }    
        return instanciaDAO;
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
    }
    
    public ParametrosPoliza getParametrosPoliza(Integer codigoParametroPoliza) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.get(ParametrosPoliza.class, codigoParametroPoliza);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ParametrosPoliza getUltimoParametrosPoliza(Integer codigoParametroPoliza) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();          
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM ParametrosPoliza p where codigo_parametro_poliza"
            		+ "="+codigoParametroPoliza+ "and ultimo=true order by nombre", ParametrosPoliza.class).uniqueResult(); 
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

	public void cargarParametrosPoliza() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/parametrosPoliza.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
	}

	public ParametrosPoliza getUltimoParametrosPoliza() {
	   	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createNativeQuery("SELECT * FROM parametros_poliza where fin_vigencia is NULL", ParametrosPoliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
	}
}
