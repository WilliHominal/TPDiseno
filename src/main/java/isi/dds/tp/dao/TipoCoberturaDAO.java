package isi.dds.tp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.RiesgoTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;

public class TipoCoberturaDAO {
	
	private static TipoCoberturaDAO instanciaDAO = null;
	 
    private TipoCoberturaDAO() {

    }

    public static TipoCoberturaDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new TipoCoberturaDAO();
        }    
        return instanciaDAO;
    }
    
    public void addTipoCobertura(TipoCobertura t) {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
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
    
    @SuppressWarnings("unchecked")
	public List<TipoCobertura> getTiposCobertura(){
    	
    	List<TipoCobertura> tiposCoberturas = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            tiposCoberturas = session.createQuery("SELECT t FROM TipoCobertura t").list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return tiposCoberturas;
    }
    
    @SuppressWarnings("unchecked")
	public List<RiesgoTipoCobertura> getRiesgosCobertura(EnumTipoCobertura tipo) {
    	List<RiesgoTipoCobertura> riesgos = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            riesgos = session.createQuery("SELECT r FROM RiesgoTipoCobertura r WHERE tipo_cobertura="+tipo).list();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return riesgos;
    }
    
    public RiesgoTipoCobertura getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipo) {
    	RiesgoTipoCobertura riesgo = null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            //TODO HACER SUBCONSULTA PARA OBTENER EL ULTIMO RIESGO
            riesgo = (RiesgoTipoCobertura) session.createQuery("SELECT p FROM RiesgoTipoCobertura p where tipo_cobertura="+tipo).uniqueResult();
            
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	
    	return riesgo;
    }
    
}
