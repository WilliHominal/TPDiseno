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

public class DAOParametrosVehiculo {
	private static DAOParametrosVehiculo instanciaDAO = null;
	private static Session session = null;
	 
    private DAOParametrosVehiculo() {}

    public static DAOParametrosVehiculo getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOParametrosVehiculo();
        }
        if(session == null) {
    		session = HibernateUtil.getSessionFactoryValidate().openSession();
        	session.beginTransaction();
        }        
        return instanciaDAO;
    }
    
    public void cargarParametrosVehiculos() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/parametrosVehiculo.sql");
		try {
			Iterator<String> iteradorqueries = queries.iterator();
			while(iteradorqueries.hasNext()){
				session.createSQLQuery(iteradorqueries.next()).executeUpdate();
			}
		}catch (HibernateException e) {
			e.printStackTrace();
            session.getTransaction().rollback();	
		}
	}
    
    public void addRiesgoModelo(RiesgoModelo r) {
        try {
            session.save(r);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
	public List<Marca> getMarcas() {
        try {
            return session.createQuery("SELECT m FROM Marca m order by nombre", Marca.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Marca getMarca(Integer id_marca) {
        try {
            return session.get(Marca.class, id_marca);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public AnioModelo getAnioModelo(Integer id) {
        try {
            return session.get(AnioModelo.class, id);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
	}
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
        try {
            return session.createQuery("SELECT p FROM RiesgoModelo p where id_modelo="+id_modelo+" and fin_vigencia is NULL", RiesgoModelo.class).uniqueResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    public void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
}
