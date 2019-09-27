package isi.dds.tp.dao;

public class ParametrosVehiculosDAO {
	
	private static ParametrosVehiculosDAO instanciaDAO = null;
	 
    private ParametrosVehiculosDAO() {

    }

    public static ParametrosVehiculosDAO getParametrosVehiculosDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ParametrosVehiculosDAO();
        }    
        return instanciaDAO;
    }

}
