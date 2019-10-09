package isi.dds.tp.dao;

public class BitacoraDAO {
	
	private static BitacoraDAO instanciaDAO = null;
	 
    private BitacoraDAO() {

    }

    public static BitacoraDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new BitacoraDAO();
        }    
        return instanciaDAO;
    }
    
    
}
