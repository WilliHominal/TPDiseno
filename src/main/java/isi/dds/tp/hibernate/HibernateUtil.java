package isi.dds.tp.hibernate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import isi.dds.tp.enums.*;
import isi.dds.tp.gestor.*;
import isi.dds.tp.modelo.*;

public class HibernateUtil {
	
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
	private static StandardServiceRegistry registryBis;
    private static SessionFactory sessionFactoryBis;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
            	
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
                
            } catch (Exception e) {
            	sessionFactory = HibernateUtil.getSessionFactoryBis();
            	
            	//si es null, significa que la contraseña y/o la database estan mal configuradas
            	if(sessionFactory == null) {
        		  	//TODO BUSCAR EXEPCION POR BASE DE DATOS NO CREADA, Y SE QUIERE VALIDAR
                	JOptionPane.showMessageDialog(null, "Se debe configurar la Bases de datos antes de lanzar la aplicacion.\n"
                            + "Utilizando PostgreSQL, cree una Database en PGADMIN cuyo nombre sea \"grupo5a\" y cuya password sea 12345.\n"
                            + "En caso de tener un password configurada distinta, modificar el archivo \"hibernate.cfg.xml\",\n"
                            + "ubicado en el path /src/main/resources, y sobreescribir el campo \"hibernate.connection.password\"\n"
                            + "con la contraseña que se posea configurada.",
                              "Configurar bases de datos.", JOptionPane.ERROR_MESSAGE);       	
          		                          		
            	}
 
            }
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (registry != null) {
        	sessionFactory = null;
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
    public static SessionFactory getSessionFactoryBis() {
        if (sessionFactoryBis == null) {
            try {
            	
            	registryBis = new StandardServiceRegistryBuilder().configure("/crearBase/hibernate.cfg.xml").build();
            	
                MetadataSources sources = new MetadataSources(registryBis);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactoryBis = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                
                return null;
            }
        }
        return sessionFactoryBis;
    }
    
    public static void shutdownBis() {
        if (registryBis != null) {
            StandardServiceRegistryBuilder.destroy(registryBis);
        }
    }
    
    @Transactional
    @SuppressWarnings("unused")
	public static void cargarBase() {
    	Pais pais1 = new Pais("Argentina");
		
		Provincia prov1 = new Provincia(pais1, "Santa Fe");
		Provincia prov2 = new Provincia(pais1, "Buenos Aires");
		Provincia prov3 = new Provincia(pais1, "Entre Rios");
				
		Ciudad ciudad1 = new Ciudad(prov1, "Esperanza", 1f);
		Ciudad ciudad2 = new Ciudad(prov1, "Santa Fe", 2f);
		Ciudad ciudad3 = new Ciudad(prov2, "La Plata", 3f);
		Ciudad ciudad4 = new Ciudad(prov2, "Mar del Plata", 4f);
		Ciudad ciudad5 = new Ciudad(prov3, "Paraná", 5f);
		Ciudad ciudad6 = new Ciudad(prov3, "Diamante", 6f);

		
		Cliente cliente1 = new Cliente(ciudad1, 123456l, EnumCondicion.NORMAL, "APELLIDO", "NOMBRES", EnumTipoDocumento.DNI, 99999999, 
				2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo@HOTMAIL.COM", EnumEstadoCivil.CASADO, "PROFESOR", 2019);
		
		//recrea la base de vuelta
		HibernateUtil.getSessionFactoryBis();
		
		GestorDomicilio.get().addPais(pais1);
		
		GestorCliente.get().AltaCliente(cliente1);

		//PARA RECREAR LA BASE, BORRA Y LA RECARGA DE NUEVO


		/*
		 TODO 3 MANERAS DE INSERTAR
		 	- MEDIANTE CODIGO JAVA (como ciudad prov y)
		 	- CARGANDO UN FILE SCRIPT
		 	- PONIENDO EL SCRIPT POR CODIGO
		*/
		
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/scriptSQL.sql");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){

			session.createSQLQuery(iteradorqueries.next()).executeUpdate();

		}
		session.close();
		
		/*
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		
		String sql = String.format(
				//marcas
				"INSERT INTO marca VALUES(1,'Renault');"
				+"INSERT INTO marca VALUES(2,'Volskwagen');"
				+"INSERT INTO marca VALUES(3,'Ford');"
				+"INSERT INTO marca VALUES(4,'Porsche');"
				+"INSERT INTO modelo VALUES(1, 'Clio', 1, 0);"
				+"INSERT INTO modelo VALUES(2, 'Kwid', 1, 1);"
				+"INSERT INTO modelo VALUES(3, 'Koleos', 1, 2);"
				+"INSERT INTO modelo VALUES(4, 'Polo', 2, 0);"
				+"INSERT INTO modelo VALUES(5, 'Golf', 2, 1);"
				+"INSERT INTO modelo VALUES(6, 'Passat', 2, 2);"
				+"INSERT INTO modelo VALUES(7, 'Focus', 3, 0);"
				+"INSERT INTO modelo VALUES(8, 'Fiesta', 3, 1);"
				+"INSERT INTO modelo VALUES(9, 'Panamera', 4, 0);"
				+"INSERT INTO modelo VALUES(10, 'Macan', 4, 1);"
				+"insert into anio_modelo values(1, 2017, 90000, 1, 0);"
				+"insert into anio_modelo values(2, 2018, 92000, 1, 1);"
				+"insert into anio_modelo values(3, 2019, 112000, 2, 0);"
				+"insert into anio_modelo values(4, 2019, 150000, 3, 0);"
				+"insert into anio_modelo values(5, 2016, 87000, 4, 0);"
				+"insert into anio_modelo values(6, 2015, 93500, 5, 0);"
				+"insert into anio_modelo values(7, 2017, 230000, 6, 0);"
				+"insert into anio_modelo values(8, 2019, 98900, 7, 0);"
				+"insert into anio_modelo values(9, 2019, 76000, 8, 0);"
				+"insert into anio_modelo values(10, 2019, 105900, 10, 0);"
				+"insert into anio_modelo values(11, 2018, 124230, 10, 1);"
				+"insert into anio_modelo values(12, 2019, 134890, 11, 0);"
				+"insert into anio_modelo values(13, 2018, 158020, 11, 1);"
				);
		session.createSQLQuery(sql).executeUpdate();
		session.close();*/
		
		
		
	
		
    }
}
