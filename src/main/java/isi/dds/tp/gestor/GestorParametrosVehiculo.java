package isi.dds.tp.gestor;

import isi.dds.tp.dao.ParametrosVehiculosDAO;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.RiesgoModelo;

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

}
