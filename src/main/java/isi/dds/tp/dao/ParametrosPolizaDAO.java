package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.conectar.HibernateUtil;
import isi.dds.tp.modelo.ParametrosPoliza;

public class ParametrosPolizaDAO {
	
	private static ParametrosPolizaDAO instanciaDAO = null;
	 
    private ParametrosPolizaDAO() {

    }

    public static ParametrosPolizaDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ParametrosPolizaDAO();
        }    
        return instanciaDAO;
    }

    public void addParametrosPoliza(ParametrosPoliza p) {
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
    
    public ParametrosPoliza getParametrosPoliza(Integer codigoParametroPoliza) {
    	ParametrosPoliza p = null;
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
        
        try {
            session.beginTransaction();
            p = session.get(ParametrosPoliza.class, codigoParametroPoliza);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return p;
    }
    
    public ParametrosPoliza getUltimoParametrosPoliza(Integer codigoParametroPoliza) {
    	ParametrosPoliza p = null;
    	
    	
    	Session session = HibernateUtil.getSessionFactoryParaUsarBD().openSession();
                  
        try {
            session.beginTransaction();
            p = (ParametrosPoliza) session.createQuery("SELECT p FROM ParametrosPoliza p where codigo_parametro_poliza"
            		+ "="+codigoParametroPoliza+ "and ultimo=true order by nombre");
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        return p;
    }
}
