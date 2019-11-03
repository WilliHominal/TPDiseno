package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Siniestro;

public class DAOSiniestros {
	private static DAOSiniestros instanciaDAO = null;
	private static Session session = null;
	 
    private DAOSiniestros() { }

    public static DAOSiniestros getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOSiniestros();
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
    
	public void cargarSiniestros() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/siniestros.sql");
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
    
    public Siniestro getSiniestroUltimoAnio(EnumTipoDocumento tipoDocumento, String documento, Integer anio) {
        try {
            return session.createQuery("SELECT s FROM Siniestro s where tipo_documento='"+tipoDocumento+"' and documento='"+documento+"' and anio="+anio, Siniestro.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
