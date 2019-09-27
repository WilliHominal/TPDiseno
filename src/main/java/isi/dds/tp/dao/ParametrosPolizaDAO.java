package isi.dds.tp.dao;

public class ParametrosPolizaDAO {
	
	private static ParametrosPolizaDAO instanciaDAO = null;
	 
    private ParametrosPolizaDAO() {

    }

    public static ParametrosPolizaDAO getParametrosPolizaDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ParametrosPolizaDAO();
        }    
        return instanciaDAO;
    }

}
