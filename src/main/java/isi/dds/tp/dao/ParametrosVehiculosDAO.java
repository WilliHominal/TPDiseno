package isi.dds.tp.dao;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Marca;

/*
@NamedNativeQueries({
    @NamedNativeQuery(name = "selectAuthorNames", query = "SELECT a.firstname, a.lastname FROM Author a"),
    @NamedNativeQuery(name = "selectAuthorEntities", query = "SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", resultClass = Author.class),
    @NamedNativeQuery(name = "selectAuthorValue", query = "SELECT a.id, a.firstname, a.lastname, count(b.id) as numBooks FROM Author a JOIN BookAuthor ba on a.id = ba.authorid JOIN Book b ON b.id = ba.bookid GROUP BY a.id", resultSetMapping = "AuthorValueMapping")
}*/

@SuppressWarnings({ "deprecation", "unused" })
public class ParametrosVehiculosDAO {
	
	private static ParametrosVehiculosDAO instanciaDAO = null;
	 
    private ParametrosVehiculosDAO() {

    }

    public static ParametrosVehiculosDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ParametrosVehiculosDAO();
        }    
        return instanciaDAO;
    }

    
    public void addRiesgoModelo(RiesgoModelo r) {
    	       
    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
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
    
    public void addModelo(Modelo m) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            session.save(m);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
    public void addMarca(Marca m){
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
        try {
            session.beginTransaction();
            session.save(m);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
    public void addAnioModelo(AnioModelo a) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            session.save(a);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
	@SuppressWarnings("unchecked")
	public List<Marca> getMarcas() {
    	
    	List<Marca> marcas = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            marcas = session.createQuery("SELECT m FROM Marca m").list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return marcas;
    }
    

	@SuppressWarnings("unchecked")
	public List<Modelo> getModelos(Integer id_marca) {
    	
    	List<Modelo> modelos = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            modelos = session.createQuery("SELECT m FROM Modelo m where id_marca="+id_marca+" order by nombre").list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    	return modelos;
    }
    
	@SuppressWarnings("unchecked")
	public List<AnioModelo> getAniosModelo(Integer id_modelo) {
		
    	List<AnioModelo> aniosModelo = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            aniosModelo = session.createQuery("SELECT a FROM AnioModelo a where id_modelo="+id_modelo).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    	return aniosModelo;
    }
    

	@SuppressWarnings("unchecked")
	public List<RiesgoModelo> getRiesgosModelo(Integer id_modelo) {
		
    	List<RiesgoModelo> riesgosModelo = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
                  
        try {
            session.beginTransaction();
            riesgosModelo = session.createQuery("SELECT r FROM RiesgoCiudad r where id_modelo="+id_modelo).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
    	return riesgosModelo;
    }
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	RiesgoModelo riesgo = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            //TODO HACER SUBCONSULTA PARA OBTENER EL ULTIMO RIESGO
            riesgo = (RiesgoModelo) session.createQuery("SELECT p FROM RiesgoModelo p where id_modelo="+id_modelo).uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
            	
    	return riesgo;
    }
}
