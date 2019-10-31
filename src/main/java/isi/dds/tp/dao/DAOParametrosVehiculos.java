package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Marca;

public class DAOParametrosVehiculos {
	
	private static DAOParametrosVehiculos instanciaDAO = null;
	 
    private DAOParametrosVehiculos() {}

    public static DAOParametrosVehiculos getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOParametrosVehiculos();
        }    
        return instanciaDAO;
    }
    
    public void cargarParametrosVehiculos() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/parametrosVehiculo.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
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

	public AnioModelo getAnioModelo(Integer id) {
	  	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.get(AnioModelo.class, id);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }	
    	return null;
	}
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM RiesgoModelo p where id_modelo="+id_modelo+" and ultimo=true", RiesgoModelo.class).uniqueResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }
}
