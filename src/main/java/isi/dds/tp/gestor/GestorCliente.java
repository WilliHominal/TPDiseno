package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Usuario;
import isi.dds.tp.dao.UsuarioDAO;

public class GestorCliente {
	
	private static GestorCliente instanciaGestor = null;
	 
    private GestorCliente() {

    }

    public static GestorCliente getGestorCliente() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }    
        return instanciaGestor;
    }

}
