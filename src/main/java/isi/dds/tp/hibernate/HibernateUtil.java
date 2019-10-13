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
    	
		/*
		 TODO 2 MANERAS DE INSERTAR
		 	- MEDIANTE CODIGO JAVA (como ciudad prov y)
		 	- CARGANDO UN FILE SCRIPT
		*/
    	
    	
    	/* CARGAR POR SCRIPT
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/scriptSQL.sql");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Iterator<String> iteradorqueries = queries.iterator();
		while(iteradorqueries.hasNext()){

			session.createSQLQuery(iteradorqueries.next()).executeUpdate();

		}
		session.close();
		*/
    	
    	//las prov capaz conviene ingresarlas por sql con algun script que haya en la web
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

		Marca marca = new Marca("Volskwagen");
			Modelo modelo = new Modelo(marca, "Passat", 0.6f);
				new AnioModelo(modelo, 2018, 158231f);
				new AnioModelo(modelo, 2019, 170200f);
			Modelo modelo1 = new Modelo(marca, "Golf", 0.3f);
				new AnioModelo(modelo1, 2017, 54120f);
				new AnioModelo(modelo1, 2019, 67210f);
			Modelo modelo2 = new Modelo(marca, "Scirocco", 0.8f);
				new AnioModelo(modelo2, 2018, 187800f);
				new AnioModelo(modelo2, 2019, 210630f);
				
		Marca marca1 = new Marca("Fiat");
			Modelo modelo3 = new Modelo(marca1, "Argo", 0.21f);
				new AnioModelo(modelo3, 2014, 45800f);
				new AnioModelo(modelo3, 2015, 51222f);
				new AnioModelo(modelo3, 2016, 58120f);
			Modelo modelo4 = new Modelo(marca1, "Cronos", 0.73f);
				new AnioModelo(modelo4, 2017, 184010f);
				new AnioModelo(modelo4, 2018, 199800f);
				new AnioModelo(modelo4, 2015, 149800f);
			Modelo modelo5 = new Modelo(marca1, "500X", 0.52f);
				new AnioModelo(modelo5, 2017, 101928f);
				new AnioModelo(modelo5, 2019, 142000f);
			Modelo modelo6 = new Modelo(marca1, "Uno",  0.11f);
				new AnioModelo(modelo6, 2010, 28200f);
				new AnioModelo(modelo6, 2012, 34500f);
				new AnioModelo(modelo6, 2013, 35712f);
					
		Marca marca2 = new Marca("Ford");
			Modelo modelo7 = new Modelo(marca2, "Focus", 0.24f);
				new AnioModelo(modelo7, 2016, 58213f);
				new AnioModelo(modelo7, 2019, 69123f);
			Modelo modelo8 = new Modelo(marca2, "Mondeo", 0.65f);
				new AnioModelo(modelo8, 2015, 167521f);
				new AnioModelo(modelo8, 2016, 172921f);
				new AnioModelo(modelo8, 2017, 173552f);
				new AnioModelo(modelo8, 2018, 182083f);
			Modelo modelo9 = new Modelo(marca2, "Ka", 0.08f);
				new AnioModelo(modelo9, 2014, 29100f);
				new AnioModelo(modelo9, 2015, 23182f);
			Modelo modelo14 = new Modelo(marca2, "Ecosport", 0.571f);
				new AnioModelo(modelo14, 2018, 134020f);
				new AnioModelo(modelo14, 2019, 146818f);					

		Marca marca3 = new Marca("Reanult");
			Modelo modelo10 = new Modelo(marca3, "Kwid", 0.52f);
				new AnioModelo(modelo10, 2017, 89267f);
				new AnioModelo(modelo10, 2018, 94566f);
				new AnioModelo(modelo10, 2019, 99350f);
			Modelo modelo11 = new Modelo(marca3, "Duster", 0.87f);
				new AnioModelo(modelo11, 2017, 267991f);
				new AnioModelo(modelo11, 2019, 289312f);
			Modelo modelo12 = new Modelo(marca3, "Koleos", 0.81f);
				new AnioModelo(modelo12, 2018, 216737f);
				new AnioModelo(modelo12, 2019, 230093f);
			Modelo modelo13 = new Modelo(marca3, "Kangoo II", 0.47f);
				new AnioModelo(modelo13, 2019, 92630f);
						
		TipoCobertura cobertura = new TipoCobertura(EnumTipoCobertura.RESPONSABILIDAD_CIVIL, "Responsabilidad civil", "Descripcion uno", 0.23f);
		TipoCobertura cobertura1 = new TipoCobertura(EnumTipoCobertura.RESP_CIVIL_ROBO_O_INCENDIO_TOTAL, "Responsabilidad civil, robo o incendio total", "Descripcion dos", 0.39f);
		TipoCobertura cobertura2 = new TipoCobertura(EnumTipoCobertura.TODO_TOTAL, "Todo total", "Descripcion tres", 0.65f);
		TipoCobertura cobertura3 = new TipoCobertura(EnumTipoCobertura.TERCEROS_COMPLETOS, "Terceros completos", "Descripcion seis", 0.51f);
		TipoCobertura cobertura4 = new TipoCobertura(EnumTipoCobertura.TODO_RIESGO_CON_FRANQUICIA, "Todo riesgo con franquiia", "Descripcion cinco", 0.48f);
		
		Cliente cliente1 = new Cliente(ciudad1, 123456l, EnumCondicion.NORMAL, "APELLIDO", "NOMBRES", EnumTipoDocumento.DNI, 99999999, 
				2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo@HOTMAIL.COM", EnumEstadoCivil.CASADO, "PROFESOR", 2019);
		  
		System.out.println("no todabbia");
		//PARA RECREAR LA BASE, BORRA Y LA RECARGA DE NUEVO
		HibernateUtil.getSessionFactoryBis();
		System.out.println("dasd sasdada");
		GestorDomicilio.get().addPais(pais1);
			
		GestorParametrosVehiculo.get().addMarca(marca);
		GestorParametrosVehiculo.get().addMarca(marca1);
		GestorParametrosVehiculo.get().addMarca(marca2);
		GestorParametrosVehiculo.get().addMarca(marca3);

		GestorTipoCobertura.get().addTipoCobertura(cobertura);
		GestorTipoCobertura.get().addTipoCobertura(cobertura1);
		GestorTipoCobertura.get().addTipoCobertura(cobertura2);
		GestorTipoCobertura.get().addTipoCobertura(cobertura3);
		GestorTipoCobertura.get().addTipoCobertura(cobertura4);
		
		GestorCliente.get().AltaCliente(cliente1);
    }
}
