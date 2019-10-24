package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.RiesgoCiudad;

public class DomicilioDAO {

	private static DomicilioDAO instanciaDAO = null;
	 
    private DomicilioDAO() {}

    public static DomicilioDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DomicilioDAO();
        }    
        return instanciaDAO;
    }
    
    public void addPais(Pais p) {
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
    
    public void addProvincia(Provincia p) {
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
	
    public void addCiudad(Ciudad c) {
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
    
    public void addRiesgoCiudad(RiesgoCiudad r) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

	public List<Pais> getPaises() {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM Pais p", Pais.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    

	public List<Provincia> getProvincias(Integer id_pais) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM Provincia p where id_pais="+id_pais+" order by nombre", Provincia.class).list();

        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }

    public RiesgoCiudad getUltimoRiesgoCiudad(Integer id_ciudad) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
        	session.beginTransaction();
        	return session.createQuery("SELECT p FROM RiesgoCiudad p where id_ciudad="+id_ciudad+" and ultimo=true", RiesgoCiudad.class).uniqueResult();
            
        }
        catch (HibernateException e) {
        	e.printStackTrace();
        }
    	return null;
    }

    public void cargarUbicaciones() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/domicilio.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		
		session.beginTransaction();
		
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){

			session.createSQLQuery(iteradorqueries.next()).executeUpdate();

		}
		session.close();
    }
    
}
