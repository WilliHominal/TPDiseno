package isi.dds.tp.gestor;

import isi.dds.tp.dao.PagoDAO;
import isi.dds.tp.modelo.Pago;

public class GestorPago {
	
	private static GestorPago instanciaGestor = null;
	 
    private GestorPago() {

    }

    public static GestorPago getGestorPago() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPago();
        }    
        return instanciaGestor;
    }

}
