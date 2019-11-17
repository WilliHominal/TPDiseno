package isi.dds.tp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Pago;

public class DAOPago {
	private static DAOPago instanciaDAO = null;
	 
    private DAOPago() { }

    public static DAOPago getDAO() {
    	 if (instanciaDAO == null){
         	instanciaDAO = new DAOPago();
         }
         return instanciaDAO;
    }

	public void addPago(Pago pago) {
		//TODO mirar porque no deja agregar mas de un pago a una poliza
		Session session = HibernateUtil.getSessionFactoryValidate().openSession();
        try {
        	session.beginTransaction();
            session.save(pago);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
	}
}
