package isi.dds.tp.app;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import isi.dds.tp.hibernate.HibernateUtil2;
import isi.dds.tp.modelo.Pais;

public class App {
    
	public static void main(String[] args) {
        Pais pais = new Pais(1,"Argentina");
        Pais pais2 = new Pais(2,"Bolivia");
        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(pais);
            session.save(pais2);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
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
