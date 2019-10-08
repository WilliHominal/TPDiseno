package isi.dds.tp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Poliza;

public class PolizaDAO {
	
	private static PolizaDAO instanciaDAO = null;
	 
    private PolizaDAO() {

    }

    public static PolizaDAO getPolizaDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PolizaDAO();
        }    
        return instanciaDAO;
    }

    public void a() {

		Ciudad c = new Ciudad();
		c.setNombre("esperanza");

		Ciudad ci2 = new Ciudad();
		ci2.setNombre("sin servidor activo");

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.getSessionFactory().openSession().getTransaction();
			// start a transaction
			session.beginTransaction();

			// save the student objects
			session.save(ci2);

			// commit transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
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
