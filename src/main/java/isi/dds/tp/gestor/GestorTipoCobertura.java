package isi.dds.tp.gestor;

import isi.dds.tp.modelo.TipoCobertura;

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

    public void addRiesgoCobertura(TipoCobertura t, Float riesgo) {
    	
    }
    
    public Float getRiesgoCobertura(TipoCobertura t) {
    	//retorna el ultimo valor de riesgo de la ciudad
    	return null;
    }
}
