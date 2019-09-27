package isi.dds.tp.dao;

public class PagoDAO {
	
	private static PagoDAO instanciaDAO = null;
	 
    private PagoDAO() {

    }

    public static PagoDAO getPagoDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PagoDAO();
        }    
        return instanciaDAO;
    }

}
