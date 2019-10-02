package isi.dds.tp.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;

public class InicioInstalacion {
	/*TODO interfaz para indicar que tiene que tener instalado postgres 11 etc*/
	/*TODO interfaz para decidir si cargar la base de datos en postgres*/
	
	
	public static void main(String[] args) {
/*
		Pais pais1 = new Pais("Argentina1");
		Pais pais2 = new Pais("Argentina2");
		Pais pais3 = new Pais("Argentina3");
		
		Provincia prov1 = new Provincia(pais1, "");
		Provincia prov2 = new Provincia(pais1, "");
		Provincia prov3 = new Provincia(pais1, "");
		
		Provincia prov4 = new Provincia(pais1, "");
		Provincia prov5 = new Provincia(pais2, "");
		Provincia prov6 = new Provincia(pais2, "");
		
		Provincia prov7 = new Provincia(pais2, "");
		Provincia prov8 = new Provincia(pais2, "");
		Provincia prov9 = new Provincia(pais3, "");
		
		Ciudad ciudad1 = new Ciudad(prov, "", 1f);
		Ciudad ciudad2 = new Ciudad(prov, "", 1f);
		Ciudad ciudad3 = new Ciudad(prov, "", 1f);
		Ciudad ciudad4 = new Ciudad(prov, "", 1f);
		Ciudad ciudad5 = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);

		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		Ciudad ciudad = new Ciudad(prov, "", 1f);
		

		
		
		
		Poliza p1 = new Poliza(1l);		
		Poliza p2 = new Poliza(2l);
		
		Cuota c1 = new Cuota();
		c1.setNumeroCuota(1);
		Cuota c2 = new Cuota();
		c2.setNumeroCuota(2);
		Cuota c3 = new Cuota();
		c3.setNumeroCuota(3);
		Cuota c4 = new Cuota();
		c4.setNumeroCuota(1);
		Cuota c5 = new Cuota();
		c5.setNumeroCuota(2);
		Cuota c6 = new Cuota();
		c6.setNumeroCuota(3);
		
		p1.getCuotas().add(c1);
		p1.getCuotas().add(c2);
		p1.getCuotas().add(c3);
		p2.getCuotas().add(c4);
		p2.getCuotas().add(c5);
		p2.getCuotas().add(c6);
		
		Ciudad c = new Ciudad();
		c.setNombre("asdads");
		
		
		Ciudad ci2 = new Ciudad();
		ci2.setNombre("asdads");
      
       
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
		
		//Transaction transaction = null;
		
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
