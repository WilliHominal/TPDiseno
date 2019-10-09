package isi.dds.tp.dao;

public class PagoDAO {
	
	private static PagoDAO instanciaDAO = null;
	 
    private PagoDAO() {

    }

    public static PagoDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PagoDAO();
        }    
        return instanciaDAO;
    }

}
