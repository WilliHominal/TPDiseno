package isi.dds.tp.hibernate;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import isi.dds.tp.dao.DAOBitacora;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.dao.DAODomicilio;
import isi.dds.tp.dao.DAOPago;
import isi.dds.tp.dao.DAOParametrosPoliza;
import isi.dds.tp.dao.DAOParametrosVehiculo;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.dao.DAOSiniestros;
import isi.dds.tp.dao.DAOTipoCobertura;
import isi.dds.tp.dao.DAOUsuario;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
	private static StandardServiceRegistry registryBis;
    private static SessionFactory sessionFactoryBis;
    
    private static SessionFactory getSessionFactoryCreate() {
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF); 
        if (sessionFactoryBis == null) {
            try {
            	registryBis = new StandardServiceRegistryBuilder().configure("hibernateCreate.cfg.xml").build();
                MetadataSources sources = new MetadataSources(registryBis);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactoryBis = metadata.getSessionFactoryBuilder().build();
                sessionFactoryBis.getCurrentSession().beginTransaction();
                sessionFactory = sessionFactoryBis;
            } catch (Exception e) {
                return null;
            }
        }
        return sessionFactoryBis;
    }
    
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
            sessionFactory.getCurrentSession().beginTransaction();
        }
        return sessionFactory;
    }
  
	public static Boolean conectarBaseDatos() {
		if(getSessionFactoryValidate() != null) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public static void shutdown() {
		DAOParametrosPoliza.getDAO().shutdown();
		DAODomicilio.getDAO().shutdown();
		DAOSiniestros.getDAO().shutdown();
		DAOTipoCobertura.getDAO().shutdown();
		DAOParametrosVehiculo.getDAO().shutdown();
		DAOCliente.getDAO().shutdown();
		DAOPoliza.getDAO().shutdown();
		DAOPago.getDAO().shutdown();
		DAOBitacora.getDAO().shutdown();
		DAOUsuario.getDAO().shutdown();
    	
        if (registry != null && sessionFactory != null) {
        	sessionFactory.getCurrentSession().close();
        	sessionFactory = null;        	
            StandardServiceRegistryBuilder.destroy(registry);
        }
        if (registryBis != null && sessionFactoryBis != null) {
        	sessionFactoryBis.getCurrentSession().close();
        	sessionFactoryBis = null;
            StandardServiceRegistryBuilder.destroy(registryBis);
        }
    }
    
    public static void cerrarSessionesUsadas() {
    	shutdown();
    	getSessionFactoryValidate();
    }
	
	public static void recargarBaseDatos() {
		shutdown();
		getSessionFactoryCreate();	
		DAOParametrosPoliza.getDAO().cargarParametrosPoliza();
		DAODomicilio.getDAO().cargarUbicaciones();
		DAOSiniestros.getDAO().cargarSiniestros();
		DAOTipoCobertura.getDAO().cargarTiposCoberturas();
		DAOParametrosVehiculo.getDAO().cargarParametrosVehiculos();
		DAOCliente.getDAO().cargarClientes();
		//DAOPoliza.getDAO().cargarPolizas();
	}	
}
