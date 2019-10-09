package isi.dds.tp.gestor;

import java.util.List;

import isi.dds.tp.dao.DomicilioDAO;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.RiesgoCiudad;

public class GestorDomicilio {
	
	private static GestorDomicilio instanciaGestor = null;
	 
    private GestorDomicilio() {

    }

    public static GestorDomicilio get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorDomicilio();
        }    
        return instanciaGestor;
    }

    public Boolean validarAjustePorcentual(RiesgoCiudad r){
		return null;    	
    }
    
    public void addPais(Pais p) {
    	
    }
    
    public void addProvincia(Pais pais, Provincia provincia) {
    	
    }
    
    public void addCiudad(Provincia provincia, Ciudad ciudad) {
    	
    }
    
    public void addRiesgoCiudad(Ciudad c, Float riesgo) {
    	
    }
    
    public List<Pais> getPaises() {
    	List<Pais> paises = null;
    	
    	return paises;
    }
    
    public List<Provincia> getProvincias(Pais c) {
    	
    	//TODO modificar pais
    	List<Provincia> provincias = DomicilioDAO.getDAO().getProvincias(100);
    	
    	return provincias;
    }
    
    public List<Ciudad> getCiudades(Provincia p) {

    	List<Ciudad> ciudades = null;
    	
    	return ciudades;
    }
    
    public List<RiesgoCiudad> getRiesgosCiudad(Ciudad c) {
    	//retorna el ultimo valor de riesgo de la ciudad

    	List<RiesgoCiudad> riesgosCiudad = null;
    	
    	return riesgosCiudad;
    }
    
    public RiesgoCiudad ultimoRiesgoCiudad(Ciudad c) {
    	return null;
    }
    

    
    
}
