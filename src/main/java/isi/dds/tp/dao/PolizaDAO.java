package isi.dds.tp.dao;

public class PolizaDAO {
	
	private static PolizaDAO instanciaDAO = null;
	 
    private PolizaDAO() {

    }

    public static PolizaDAO getPolizaDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new PolizaDAO();
        }    
        return instanciaDAO;
    }

}
