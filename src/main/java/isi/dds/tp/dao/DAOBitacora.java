package isi.dds.tp.dao;

public class DAOBitacora {
	
	private static DAOBitacora instanciaDAO = null;
	 
    private DAOBitacora() {

    }

    public static DAOBitacora getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOBitacora();
        }    
        return instanciaDAO;
    }
    
    
}
