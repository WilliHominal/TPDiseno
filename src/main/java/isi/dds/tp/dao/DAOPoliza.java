package isi.dds.tp.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.hibernate.SQLReader;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Poliza;

public class DAOPoliza {
	private static DAOPoliza instanciaDAO = null;
	private static Session session = null;
	 
    private DAOPoliza() {}

    public static DAOPoliza getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOPoliza();
        }
        if(session == null) {
    		session = HibernateUtil.getSessionFactoryValidate().openSession();
        	session.beginTransaction();
        }       
        
        return instanciaDAO;
    }
    
	public void cargarPolizas() {
		ArrayList<String> queries = SQLReader.getQueries("src/main/resources/database/polizas.sql");
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
		try {
			session.beginTransaction();
			Iterator<String> iteradorqueries = queries.iterator();
			while(iteradorqueries.hasNext()){
				session.createSQLQuery(iteradorqueries.next()).executeUpdate();
			}
		}catch (HibernateException e) {
			e.printStackTrace();
            session.getTransaction().rollback();	
		}
		session.close();
	}

    public Boolean addPoliza(Poliza p) {
    	Session session = HibernateUtil.getSessionFactoryValidate().openSession();
    	Boolean altaPoliza = false;
        try {
        	session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            altaPoliza = true;
        }
        catch (HibernateException e) {
        	altaPoliza = false;
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
        return altaPoliza;
    }

	public List<Poliza> getPolizas(Long numeroCliente) {
        try {
            return session.createQuery("SELECT p FROM Poliza p WHERE numero_cliente="+numeroCliente, Poliza.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Long getCantPolizaPorMotor(String motor) {
        try {
            return (Long) session.createQuery("select count(*) from Poliza where motor='"+motor+"'").getSingleResult();
        }
        catch (HibernateException e) { }
        return null;
    }
	
	public Long getCantPolizaPorChasis(String chasis) {
        try {
        	return (Long) session.createQuery("select count(*) from Poliza where chasis='"+chasis+"'").getSingleResult();
        }
        catch (HibernateException e) { }
        return null;
    }
	
	public Long getCantPolizaPorPatente(String patente) {
        try {
        	return (Long) session.createQuery("select count(*) from Poliza where patente='"+patente+"'").getSingleResult();
        }
        catch (HibernateException e) { }
        return null;
    }

	public List<Cuota> getCuotas(Long numeroPoliza) {
        try {
            return session.createQuery("SELECT c FROM Cuota c WHERE numero_poliza="+numeroPoliza, Cuota.class).list();
        }
        catch (HibernateException e) { }
        return null;
    }

	public Long generateNumeroRelacionClientePoliza() {
		// TODO implementar generateNumeroRelacionClientePoliza
		return 0l;
	}
	
    public void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }

	public BigInteger getCantCuotasImpagas(Long numeroCliente) {
        try {
        	return (BigInteger) session.createSQLQuery("select count(c) from poliza p, cuota c"
        			+ " where	p.numero_cliente="+numeroCliente 
        			+	  " and p.numero_poliza=c.numero_poliza"
        			+	  " and c.estado='"+EnumEstadoCuota.IMPAGO+"'"
        			+	  " and c.ultimo_dia_pago<'"+LocalDate.now()+"';").getSingleResult();
        }
        catch (HibernateException e) { }
        return null;
	}
}
