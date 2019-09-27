package isi.dds.tp.gestor;

import isi.dds.tp.dao.UsuarioDAO;
import isi.dds.tp.modelo.Usuario;

public class GestorUsuario {

	private static GestorUsuario instanciaGestor = null;
	 
    private GestorUsuario() {

    }

    public static GestorUsuario getGestorUsuario() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorUsuario();
        }    
        return instanciaGestor;
    }
}
