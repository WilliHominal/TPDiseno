package isi.dds.tp.hibernate;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
	private static StandardServiceRegistry registryBis;
    private static SessionFactory sessionFactoryBis;
    
    //TODO si se da una excepcion por no tener datos, cargar la base
    public static SessionFactory getSessionFactoryValidate() {
		//desactiva que se abra la consola cada vez que se inicia
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF); 
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure("hibernateValidate.cfg.xml").build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
                
            } catch (Exception e) {
            	sessionFactory = getSessionFactoryCreate();
            }
        }
        return sessionFactory;
    }
    
    public static SessionFactory getSessionFactoryCreate() {
		//desactiva que se abra la consola cada vez que se inicia
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF); 
        if (sessionFactoryBis == null) {
            try {
            	registryBis = new StandardServiceRegistryBuilder().configure("hibernateCreate.cfg.xml").build();
            	
                MetadataSources sources = new MetadataSources(registryBis);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactoryBis = metadata.getSessionFactoryBuilder().build();
                sessionFactory = sessionFactoryBis;
            } catch (Exception e) {
                return null;
            }
        }
        return sessionFactoryBis;
    }
    
    public static void shutdown() {
        if (registry != null) {
        	if(sessionFactory != null) {
        		sessionFactory.close();
        	}        	
        	sessionFactory = null;        	
            StandardServiceRegistryBuilder.destroy(registry);
        }
        if (registryBis != null) {
        	if(sessionFactoryBis != null) {
        		sessionFactoryBis.close();
        	}   
        	sessionFactoryBis = null;
            StandardServiceRegistryBuilder.destroy(registryBis);
        }
    }
    
	public static Boolean conectarBaseDatos() {
		if(getSessionFactoryValidate() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void recargarBaseDatos() {
		shutdown();
		CargarBase.load();
	}
}
