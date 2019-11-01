package isi.dds.tp.dao;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Poliza;

public class DAOPoliza {
	
	private static DAOPoliza instanciaDAO = null;
	 
    private DAOPoliza() { }

    public static DAOPoliza getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOPoliza();
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
        	JOptionPane.showConfirmDialog(new Frame(), "No se ha podido dar de alta a la póliza,\nverificar los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
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
