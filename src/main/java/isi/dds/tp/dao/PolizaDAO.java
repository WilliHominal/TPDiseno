package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.SolicitudPoliza;

public class PolizaDAO {
	
	private static PolizaDAO instanciaDAO = null;
	 
    private PolizaDAO() { }

    public static PolizaDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PolizaDAO();
        }    
        return instanciaDAO;
    }

    public void addPoliza(Poliza p) {
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
    
    public void addSolicitudPoliza(SolicitudPoliza s) {
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


	public List<Poliza> getPolizas(Long numeroCliente) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("SELECT p FROM Poliza p WHERE numero_cliente="+numeroCliente, Poliza.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public List<Cuota> getCuotas(Long numeroPoliza) {
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("SELECT c FROM Cuota c WHERE numero_poliza="+numeroPoliza, Cuota.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public SolicitudPoliza getSolicitudPoliza(Long numeroPoliza) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("SELECT s FROM SolicitudPoliza s WHERE numero_poliza="+numeroPoliza, SolicitudPoliza.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
	public List<HijoDeclarado> getHijosDeclarados(Long numeroPoliza) {
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("SELECT h FROM HijoDeclarado h WHERE numero_poliza="+numeroPoliza, HijoDeclarado.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

	public void cargarPolizas() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/polizas.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
	}
}
