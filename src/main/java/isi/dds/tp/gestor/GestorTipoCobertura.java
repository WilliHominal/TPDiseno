package isi.dds.tp.gestor;

import isi.dds.tp.dao.TipoCoberturaDAO;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.modelo.RiesgoTipoCobertura;

public class GestorTipoCobertura {
	
	private static GestorTipoCobertura instanciaGestor = null;
	 
    private GestorTipoCobertura() {

    }

    public static GestorTipoCobertura getGestorTipoCobertura() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTipoCobertura();
        }    
        return instanciaGestor;
    }

}
