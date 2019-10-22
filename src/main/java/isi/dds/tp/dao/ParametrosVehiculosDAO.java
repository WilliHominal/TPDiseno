package isi.dds.tp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Marca;

public class ParametrosVehiculosDAO {
	
	private static ParametrosVehiculosDAO instanciaDAO = null;
	 
    private ParametrosVehiculosDAO() {}

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
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

	public List<Marca> getMarcas() {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT m FROM Marca m order by nombre", Marca.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
	public List<Modelo> getModelos(Integer id_marca) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT m FROM Modelo m where id_marca="+id_marca+" order by nombre", Modelo.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    	return null;
    }
    
	public List<AnioModelo> getAniosModelo(Integer id_modelo) {
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT a FROM AnioModelo a where id_modelo="+id_modelo+" order by anio", AnioModelo.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    	return null;
    }
    
	public List<RiesgoModelo> getRiesgosModelo(Integer id_modelo) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
                  
        try {
            session.beginTransaction();
            return session.createQuery("SELECT r FROM RiesgoCiudad r where id_modelo="+id_modelo, RiesgoModelo.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }*/
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            //TODO HACER SUBCONSULTA PARA OBTENER EL ULTIMO RIESGO
            return session.createQuery("SELECT p FROM RiesgoModelo p where id_modelo="+id_modelo+" and ultimo=true", RiesgoModelo.class).uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    public Marca getMarca(Integer id_marca) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            return session.get(Marca.class, id_marca);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
