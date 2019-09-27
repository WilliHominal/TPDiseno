package isi.dds.tp.dao;

public class UsuarioDAO {
	
	private static UsuarioDAO instanciaDAO = null;
	 
    private UsuarioDAO() {

    }

    public static UsuarioDAO getUsuarioDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new UsuarioDAO();
        }    
        return instanciaDAO;
    }

}
