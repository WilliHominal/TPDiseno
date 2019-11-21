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

public class DAODomicilio {

	private static DAODomicilio instanciaDAO = null;
	private static Session session = null;
	 
    private DAODomicilio() {}
    
    public static DAODomicilio getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAODomicilio();
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
    
    public void cargarUbicaciones() {
    	ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/domicilio.sql");
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
		session.close();
    }

	public List<Pais> getPaises() {
        try {
            return session.createQuery("SELECT p FROM Pais p order by nombre", Pais.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }    

	public List<Provincia> getProvincias(Integer id_pais) {               
        try {
            return session.createQuery("SELECT p FROM Provincia p where id_pais="+id_pais, Provincia.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }
	
	public Ciudad getCiudad(Integer id_ciudad) {
        try {
            return session.get(Ciudad.class, id_ciudad);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }	
    	return null;
	}

    public RiesgoCiudad getUltimoRiesgoCiudad(Integer id_ciudad) {
        try {
        	return session.createQuery("SELECT p FROM RiesgoCiudad p where id_ciudad="+id_ciudad+" and fin_vigencia is NULL", RiesgoCiudad.class).uniqueResult();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
        }
    	return null;
    }
    
	public void updateCiudad(Ciudad c) {
        try {
            session.update(c);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
	}   
    
}
