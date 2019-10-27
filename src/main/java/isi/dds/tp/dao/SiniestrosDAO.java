package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Siniestro;

public class SiniestrosDAO {
	
	private static SiniestrosDAO instanciaDAO = null;
	 
    private SiniestrosDAO() {

    }

    public static SiniestrosDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new SiniestrosDAO();
        }    
        return instanciaDAO;
    }

    public void addSiniestro(Siniestro s) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            session.save(s);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
    public Siniestro getSiniestroUltimoAnio(EnumTipoDocumento tipoDocumento, String documento, Integer anio) {    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            return session.createQuery("SELECT s FROM Siniestro s where tipo_documento='"+tipoDocumento+"' and documento='"+documento+"' and anio="+anio, Siniestro.class).getSingleResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public void cargarSiniestros() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/siniestros.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
	}
}
