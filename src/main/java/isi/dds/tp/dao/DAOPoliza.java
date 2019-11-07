package isi.dds.tp.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumEstadoPoliza;
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
    
    public static void shutdown() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
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

	public Integer getNumeroClientePoliza() {
		BigInteger numeroSequence = null;
		try {
			numeroSequence = (BigInteger) session.createSQLQuery("select * from nextval('poliza_cliente_seq')").uniqueResult();
		}catch (HibernateException e) { }
		return numeroSequence.intValue();
	}

	public Integer getCantCuotasImpagas(Long numeroCliente) {
        try {
        	return ((BigInteger) session.createSQLQuery("select count(c) from poliza p, cuota c"
        			+ " where	p.numero_cliente="+numeroCliente 
        			+	  " and p.numero_poliza=c.numero_poliza"
        			+	  " and c.estado='"+EnumEstadoCuota.IMPAGO+"'"
        			+	  " and c.ultimo_dia_pago<'"+LocalDate.now()+"';").getSingleResult()).intValue();
        }
        catch (HibernateException e) { }
        return null;
	}

	public Integer getCantidadPolizasVigente(Long numeroCliente) {
		BigInteger big = BigInteger.ZERO;
        try {
        	big = (BigInteger) session.createSQLQuery("select count(*)"
        			+ " from Poliza p, Cliente c"
        			+ " where p.estado='VIGENTE' and c.numero_cliente=p.numero_cliente and c.numero_cliente="+numeroCliente
        			+ " group by c;").getSingleResult();
        }
        catch (NoResultException e) {
        	big = BigInteger.ZERO;
        }
        return big.intValue();
	}
	
	public List<Poliza> getPolizasActivas(Long numeroCliente) {
        try {
            return session.createQuery("SELECT p FROM Poliza p WHERE numero_cliente="+numeroCliente
            		+" and estado='"+EnumEstadoPoliza.VIGENTE+"'", Poliza.class).list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
