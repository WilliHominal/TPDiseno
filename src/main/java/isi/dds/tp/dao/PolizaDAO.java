package isi.dds.tp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.SolicitudPoliza;



public class PolizaDAO {
	
	private static PolizaDAO instanciaDAO = null;
	 
    private PolizaDAO() {

    }

    public static PolizaDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PolizaDAO();
        }    
        return instanciaDAO;
    }

    public void addPoliza(Poliza p) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
        
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

    	Session session = HibernateUtil.getSessionFactory().openSession();
        
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

    
    @SuppressWarnings("unchecked")
	public List<Poliza> getPolizas(Long numeroCliente) {
    	
    	List<Poliza> polizas = null;

    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            polizas = session.createQuery("SELECT p FROM Poliza p WHERE numero_cliente="+numeroCliente).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return polizas;
    }
    
    @SuppressWarnings("unchecked")
	public List<Cuota> getCuotas(Long numeroPoliza) {

    	List<Cuota> cuotas = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            cuotas = session.createQuery("SELECT c FROM Cuota c WHERE numero_poliza="+numeroPoliza).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return cuotas;
    }
    
    public SolicitudPoliza getSolicitudPoliza(Long numeroPoliza) {

    	SolicitudPoliza solicitud = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            solicitud = (SolicitudPoliza) session.createQuery("SELECT s FROM SolicitudPoliza s WHERE numero_poliza="+numeroPoliza);
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return solicitud;
    }
    
    @SuppressWarnings("unchecked")
	public List<HijoDeclarado> getHijosDeclarados(Long numeroPoliza) {

    	List<HijoDeclarado> hijos = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            hijos = session.createQuery("SELECT h FROM HijoDeclarado h WHERE numero_poliza="+numeroPoliza).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return hijos;
    }
}
