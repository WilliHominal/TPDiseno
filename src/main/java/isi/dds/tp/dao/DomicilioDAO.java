package isi.dds.tp.dao;

public class DomicilioDAO {

	private static DomicilioDAO instanciaDAO = null;
	 
    private DomicilioDAO() {

    }

    public static DomicilioDAO getDomicilioDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DomicilioDAO();
        }    
        return instanciaDAO;
    }
	
}
