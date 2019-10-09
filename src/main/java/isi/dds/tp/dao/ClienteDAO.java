package isi.dds.tp.dao;

public class ClienteDAO {
	
	private static ClienteDAO instanciaDAO = null;
	 
    private ClienteDAO() {

    }

    public static ClienteDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new ClienteDAO();
        }    
        return instanciaDAO;
    }

    
}
