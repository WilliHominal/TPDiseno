package isi.dds.tp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import isi.dds.tp.conectar.HibernateUtil;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.RiesgoCiudad;

@SuppressWarnings({ "deprecation", "unused" })
public class DomicilioDAO {

	private static DomicilioDAO instanciaDAO = null;
	 
    private DomicilioDAO() {

    }

    public static DomicilioDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DomicilioDAO();
        }    
        return instanciaDAO;
    }
    
    public void addPais(Pais p) {

    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
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

    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
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

    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
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
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
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
    
        
	@SuppressWarnings("unchecked")
	public List<Pais> getPaises() {
    	
    	List<Pais> paises = null;

    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
        try {
            session.beginTransaction();
            paises = session.createQuery("SELECT p FROM Pais p").list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return paises;
    }
    
	@SuppressWarnings("unchecked")
	public List<Provincia> getProvincias(Integer id_pais) {
    	
    	List<Provincia> provincias = null;
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
        try {
            session.beginTransaction();
            provincias = session.createQuery("SELECT p FROM Provincia p where id_pais="+id_pais+" order by nombre").list();

        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return  provincias;
    }
    
	@SuppressWarnings("unchecked")
	public List<Ciudad> getCiudades(Integer id_provincia) {
    	
    	List<Ciudad> ciudades = null;
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
        try {
        	session.beginTransaction();
            ciudades = session.createQuery("SELECT c FROM Ciudad c where id_provincia="+id_provincia+" order by nombre").list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }

    	return  ciudades;
    }
    
    @SuppressWarnings("unchecked")
	public List<RiesgoCiudad> getRiesgosCiudad(Integer id_ciudad) {
    	
    	List<RiesgoCiudad> riesgosCiudad = null;
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
        try {
            session.beginTransaction();
            riesgosCiudad = session.createQuery("SELECT p FROM RiesgoCiudad p where id_ciudad="+id_ciudad).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }

        return  riesgosCiudad;
    }
    
    public RiesgoCiudad getUltimoRiesgoCiudad(Integer id_ciudad) {
    	RiesgoCiudad riesgo = null;
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
        
        try {
            session.beginTransaction();
            riesgo = (RiesgoCiudad) session.createQuery("SELECT p FROM RiesgoCiudad p where id_ciudad="+id_ciudad+" and ultimo=true").uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return riesgo;
    }

}
