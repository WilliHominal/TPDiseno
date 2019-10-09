package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Poliza;

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

    public void addCiudad(Ciudad c) {

    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
                  
        try {
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
     	   session.close();
        }

		// Transaction transaction = null;
		/*
		 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 * /*Query query = session.createQuery("SELECT p FROM Pais p"); List <Pais>
		 * paises = query.list(); for (Pais pais : paises) {
		 * System.out.println(pais.getProvincias().get(0).getNombre()); }
		 *//*
			 * List <Pais> paises = session.createQuery("from Pais", Pais.class).list();
			 * paises.forEach(s -> System.out.println(s.getNombre()));
			 * 
			 * 
			 * } catch (Exception e) { if (transaction != null) { transaction.rollback(); }
			 * e.printStackTrace(); }
			 */

	
    }
}
