package isi.dds.tp.gestor;

import isi.dds.tp.dao.DomicilioDAO;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.RiesgoCiudad;

public class GestorDomicilio {
	
	private static GestorDomicilio instanciaGestor = null;
	 
    private GestorDomicilio() {

    }

    public static GestorDomicilio getGestorDomicilio() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorDomicilio();
        }    
        return instanciaGestor;
    }

    public Boolean validarAjustePorcentual(RiesgoCiudad r){
		return null;    	
    }
    
    public void addCiudad(Ciudad c) {
    	
    }
    
    public void addProvincia(Pais c) {
    	
    }
    
    public void addPais(Pais p) {
    	
    }
    
    public Float getRiesgoCiudad(Ciudad c) {
    	//retorna el ultimo valor de riesgo de la ciudad
    	return null;
    }
    
    public void addValorRiesgoCiudad(Ciudad c, Float valor) {
    	
    }
    
    
}
