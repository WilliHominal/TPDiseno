package isi.dds.tp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.RiesgoTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;

public class TipoCoberturaDAO {
	
	private static TipoCoberturaDAO instanciaDAO = null;
	 
    private TipoCoberturaDAO() { }

    public static TipoCoberturaDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new TipoCoberturaDAO();
        }    
        return instanciaDAO;
    }
    
    public void addTipoCobertura(TipoCobertura t) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
    public void addRiesgoCobertura(RiesgoTipoCobertura r) {
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
    
	public List<TipoCobertura> getTiposCobertura(){
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        
        try {
            session.beginTransaction();
            return session.createQuery("SELECT t FROM TipoCobertura t order by nombre", TipoCobertura.class).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return null;
	}
    
    public RiesgoTipoCobertura getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipo) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();

        try {
            session.beginTransaction();
            //TODO HACER SUBCONSULTA PARA OBTENER EL ULTIMO RIESGO
            return session.createQuery("SELECT p FROM RiesgoTipoCobertura p where tipo_cobertura="+tipo, RiesgoTipoCobertura.class).uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return null;
    }

	public void cargarTiposCoberturas() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/tiposCoberturas.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
	}
    
}
