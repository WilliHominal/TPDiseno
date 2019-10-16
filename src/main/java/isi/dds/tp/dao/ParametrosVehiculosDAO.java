package isi.dds.tp.dao;

import java.util.List;
import javax.persistence.NamedNativeQuery;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Marca;

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
    
    public void addModelo(Modelo m) {

    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
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
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
    	
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

    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            
            session.save(a);
            
            //Modelo mdelo = a.getModelo();
            
            
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
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            marcas = session.createQuery("SELECT m FROM Marca m order by nombre").list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return marcas;
    }
    

	@SuppressWarnings("unchecked")
	public List<Modelo> getModelos(Integer id_marca) {
    	
    	List<Modelo> modelos = null;
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
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
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            aniosModelo = session.createQuery("SELECT a FROM AnioModelo a where id_modelo="+id_modelo+" order by anio").list();
            
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
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
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
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            //TODO HACER SUBCONSULTA PARA OBTENER EL ULTIMO RIESGO
            riesgo = (RiesgoModelo) session.createQuery("SELECT p FROM RiesgoModelo p where id_modelo="+id_modelo+" and ultimo=true").uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
            	
    	return riesgo;
    }
    
    public Marca getMarca(Integer id_marca) {
    	Marca marca = null;
    	
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            marca = session.get(Marca.class, id_marca);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return marca;
    }
}
