package isi.dds.tp.app;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.Transaction;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Pais;

public class App {
    
	public static void main(String[] args) {
       Pais pais = new Pais("Argentina");
        pais.setIdPais(1);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = session.getSessionFactory().openSession().getTransaction();
        	// start a transaction
            session.beginTransaction();

            // save the student objects
            session.save(pais);
   
            // commit transaction           
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List <Pais> paises = session.createQuery("from Pais", Pais.class).list();
            paises.forEach(s -> System.out.println(s.getNombre()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	
    }
}
