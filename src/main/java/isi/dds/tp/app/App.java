package isi.dds.tp.app;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;

public class App {
    
	public static void main(String[] args) {
       Pais pais = new Pais("Argentina");
       Provincia pr = new Provincia(pais,"Santa Fe");
       Ciudad ci = new Ciudad(pr,"Santa Fe", 2.5f);
       Ciudad c2 = new Ciudad(pr,"Esperanza", 1.5f);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = session.getSessionFactory().openSession().getTransaction();
        	// start a transaction
            session.beginTransaction();

            // save the student objects
            session.save(pais);
            session.save(pr);
            session.save(ci);
            session.save(c2);
   
            // commit transaction           
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
		//Transaction transaction = null;
		/*
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	 /*Query query = session.createQuery("SELECT p FROM Pais p");
        	 List <Pais> paises = query.list();
        	 for (Pais pais : paises) {
        		 System.out.println(pais.getProvincias().get(0).getNombre());
        	 }*//*
        	List <Pais> paises = session.createQuery("from Pais", Pais.class).list();
            paises.forEach(s -> System.out.println(s.getNombre()));
            
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/
	
    }
}
