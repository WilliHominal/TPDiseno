package isi.dds.tp.hibernate;


import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import isi.dds.tp.dao.*;
import isi.dds.tp.enums.*;
import isi.dds.tp.gestor.*;
import isi.dds.tp.modelo.*;

public class HibernateUtil {
	
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
            	
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
    			            	//TODO BUSCAR EXEPCION POR BASE DE DATOS NO CREADA, Y SE QUIERE VALIDAR
            	JOptionPane.showMessageDialog(null, "Se debe configurar la Bases de datos antes de lanzar la aplicacion.\n"
                        + "Utilizando PostgreSQL, cree una Database en PGADMIN cuyo nombre sea \"grupo5a\" y cuya password sea 12345.\n"
                        + "En caso de tener un password configurada distinta, modificar el archivo \"hibernate.cfg.xml\",\n"
                        + "ubicado en el path /src/main/resources, y sobreescribir el campo \"hibernate.connection.password\"\n"
                        + "con la contraseña que se posea configurada.",
                          "Configurar bases de datos.", JOptionPane.ERROR_MESSAGE);       	
      		                 
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
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
		
		
		Marca marca1 =  new Marca("Renault");
		Marca marca2 =  new Marca("Volskwagen");
		
		Modelo modelo1 =  new Modelo(marca1, "Clio", 1f);
		Modelo modelo2 =  new Modelo(marca1, "10", 0.1f);
		Modelo modelo3 =  new Modelo(marca2, "Gol", 1.5f);
		Modelo modelo4 =  new Modelo(marca2, "Vista", 2f);
		
		AnioModelo anio1 = new AnioModelo(modelo1, 2016, 130000f);
		AnioModelo anio2 = new AnioModelo(modelo1, 2015, 120000f);
		AnioModelo anio3 = new AnioModelo(modelo2, 2011, 95000f);
		AnioModelo anio4 = new AnioModelo(modelo2, 2010, 90000f);
		AnioModelo anio5 = new AnioModelo(modelo3, 2018, 150000f);
		AnioModelo anio6 = new AnioModelo(modelo4, 2018, 250000f);
		AnioModelo anio7 = new AnioModelo(modelo4, 2019, 300000f);
		
		Cliente cliente1 = new Cliente(ciudad1, 123456l, EnumCondicion.NORMAL, "APELLIDO", "NOMBRES", EnumTipoDocumento.DNI, 99999999, 
				2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo@HOTMAIL.COM", EnumEstadoCivil.CASADO, "PROFESOR", 2019);

		
		GestorParametrosVehiculo.get().addMarca(marca1);
		GestorParametrosVehiculo.get().addMarca(marca2);
		
		GestorDomicilio.get().addPais(pais1);
		
		GestorCliente.get().AltaCliente(cliente1);
    }
}
