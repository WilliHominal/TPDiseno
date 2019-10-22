package isi.dds.tp.gestor;

import java.util.Collections;
import java.util.Comparator;
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
    	DomicilioDAO.getDAO().addPais(p);
    }
    
    public void addProvincia(Provincia p) {
    	DomicilioDAO.getDAO().addProvincia(p);
    }
    
    public void addCiudad(Ciudad c) {
    	DomicilioDAO.getDAO().addCiudad(c);
    }
    
    public void addRiesgoCiudad(RiesgoCiudad r) {
    	DomicilioDAO.getDAO().addRiesgoCiudad(r);
    }
    
    public List<Pais> getPaises() {
    	return DomicilioDAO.getDAO().getPaises();
    }
    
    public List<Provincia> getProvincias(Integer id_pais) {
    	
    	return DomicilioDAO.getDAO().getProvincias(id_pais);
    }
    /*
    public List<Ciudad> getCiudades(Integer id_provincia) {
    	return DomicilioDAO.getDAO().getCiudades(id_provincia);
    }
    
    public List<RiesgoCiudad> getRiesgosCiudad(Integer id_ciudad) {
    	return DomicilioDAO.getDAO().getRiesgosCiudad(id_ciudad);
    }*/
    
    public RiesgoCiudad ultimoRiesgoCiudad(Integer id_ciudad) {
    	return DomicilioDAO.getDAO().getUltimoRiesgoCiudad(id_ciudad);
    }
    
    public List<Ciudad> sortCiudades(List<Ciudad> lista){
    	lista.sort(Comparator.comparing(Ciudad::getNombre));
	    Collections.sort(lista, new Comparator<Ciudad>() {
	    	@Override
	    	public int compare(Ciudad c1, Ciudad c2) {
	    		return c1.getNombre().compareTo(c2.getNombre());
	    	}
	    });
	    return lista;
    }
}
