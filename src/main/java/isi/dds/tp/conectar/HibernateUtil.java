package isi.dds.tp.conectar;

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
    
    public static SessionFactory getSessionFactoryParaUsarBD() {
        if (sessionFactory == null) {
            try {
            	
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
                
            } catch (Exception e) {
            	sessionFactory = HibernateUtil.getSessionFactoryParaCrearBD();
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
    
    public static SessionFactory getSessionFactoryParaCrearBD() {
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
}
