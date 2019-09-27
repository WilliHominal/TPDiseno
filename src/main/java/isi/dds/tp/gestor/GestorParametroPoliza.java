package isi.dds.tp.gestor;

import isi.dds.tp.dao.BitacoraDAO;
import isi.dds.tp.modelo.BitacoraSolicitudPoliza;
import isi.dds.tp.modelo.BitacoraParametrosPoliza;

public class GestorParametroPoliza {
	
	private static GestorParametroPoliza instanciaGestor = null;
	 
    private GestorParametroPoliza() {

    }

    public static GestorParametroPoliza getGestorParametroPoliza() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametroPoliza();
        }    
        return instanciaGestor;
    }
	
}
