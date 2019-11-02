package isi.dds.tp.gestor;

import isi.dds.tp.dao.DAOParametrosPoliza;
import isi.dds.tp.modelo.ParametrosPoliza;

public class GestorParametrosPoliza {
	
	private static GestorParametrosPoliza instanciaGestor = null;
	 
    private GestorParametrosPoliza() { }

    public static GestorParametrosPoliza get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametrosPoliza();
        }    
        return instanciaGestor;
    }
	
	public ParametrosPoliza getUltimoParametrosPoliza() {
		return DAOParametrosPoliza.getDAO().getUltimoParametrosPoliza();
	}
}
