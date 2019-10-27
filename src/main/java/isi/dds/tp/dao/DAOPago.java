package isi.dds.tp.dao;

public class DAOPago {
	
	private static DAOPago instanciaDAO = null;
	 
    private DAOPago() {

    }

    public static DAOPago getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOPago();
        }    
        return instanciaDAO;
    }

}
