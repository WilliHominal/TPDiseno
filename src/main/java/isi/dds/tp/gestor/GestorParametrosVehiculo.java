package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Modelo;

public class GestorParametrosVehiculo {
	
	private static GestorParametrosVehiculo instanciaGestor = null;
	 
    private GestorParametrosVehiculo() {
    }

    public static GestorParametrosVehiculo getGestorParametroPoliza() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametrosVehiculo();
        }    
        return instanciaGestor;
    }

    
    public Float getRiesgoModelo(Modelo m) {
    	//retorna el ultimo valor de riesgo de la ciudad
    	return null;
    }
}
