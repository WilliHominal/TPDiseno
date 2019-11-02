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

public class DAOTipoCobertura {
	
	private static DAOTipoCobertura instanciaDAO = null;
	 
    private DAOTipoCobertura() { }

    public static DAOTipoCobertura getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOTipoCobertura();
        }    
        return instanciaDAO;
    }
    
	public void cargarTiposCoberturas() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/tiposCobertura.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		session.beginTransaction();
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){
			session.createSQLQuery(iteradorqueries.next()).executeUpdate();
		}
		session.close();
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
    
    public RiesgoTipoCobertura getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipoCobertura) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.createNativeQuery("SELECT * FROM riesgo_tipo_cobertura where tipo_cobertura='"+tipoCobertura+"'  and fin_vigencia is NULL", RiesgoTipoCobertura.class).getSingleResult();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
    	return null;
    }

	public TipoCobertura getTipoCobertura(EnumTipoCobertura tipo) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
            session.beginTransaction();
            return session.get(TipoCobertura.class, tipo);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }	
    	return null;
	}
}
