package isi.dds.tp.dao;

public class UsuarioDAO {
	
	private static UsuarioDAO instanciaDAO = null;
	 
    private UsuarioDAO() {

    }

    public static UsuarioDAO getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new UsuarioDAO();
        }    
        return instanciaDAO;
    }

}
