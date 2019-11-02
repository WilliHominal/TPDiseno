package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Poliza;

public class DAOPoliza {
	private static DAOPoliza instanciaDAO = null;
	private static Session session = null;
	 
    private DAOPoliza() {}

    public static DAOPoliza getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOPoliza();
        }
        if(session == null) {
    		session = HibernateUtil.getSessionFactoryValidate().openSession();
        	session.beginTransaction();
        }        
        return instanciaDAO;
    }
    
	public void cargarPolizas() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/polizas.sql");
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

    public void addPoliza(Poliza p) {
        try {
            session.save(p);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
    }

	public List<Poliza> getPolizas(Long numeroCliente) {
        try {
            return session.createQuery("SELECT p FROM Poliza p WHERE numero_cliente="+numeroCliente, Poliza.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Poliza getPolizaPorMotor(String motor) {
        try {
            return session.createQuery("SELECT p FROM Poliza p WHERE motor="+motor, Poliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Poliza getPolizaPorChasis(String chasis) {
		//TODO que retorne cantidad
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM Poliza p WHERE chasis="+chasis, Poliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Poliza getPolizaPorPatente(String patente) {
        try {
            return session.createQuery("SELECT p FROM Poliza p WHERE patente="+patente, Poliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public List<Cuota> getCuotas(Long numeroPoliza) {
        try {
            return session.createQuery("SELECT c FROM Cuota c WHERE numero_poliza="+numeroPoliza, Cuota.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public Long generateNumeroRelacionClientePoliza() {
		// TODO implementar generateNumeroRelacionClientePoliza
		return 0l;
	}
	
    public void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
}
