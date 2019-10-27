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
    
    public void addRiesgoCiudad(RiesgoCiudad r) {
    	DomicilioDAO.getDAO().addRiesgoCiudad(r);
    }
    
    public List<Pais> getPaises() {
    	return DomicilioDAO.getDAO().getPaises();
    }
    
    public List<Provincia> getProvincias(Integer id_pais) {
    	return sortProvincias(DomicilioDAO.getDAO().getProvincias(id_pais));
    }
    
    public RiesgoCiudad ultimoRiesgoCiudad(Integer id_ciudad) {
    	return DomicilioDAO.getDAO().getUltimoRiesgoCiudad(id_ciudad);
    }
    
    public List<Pais> sortPaises(List<Pais> lista){
    	lista.sort(Comparator.comparing(Pais::getNombre));
	    Collections.sort(lista, new Comparator<Pais>() {
	    	@Override
	    	public int compare(Pais p1, Pais p2) {
	    		return p1.getNombre().compareTo(p2.getNombre());
	    	}
	    });
	    return lista;
    }
    
	private List<Provincia> sortProvincias(List<Provincia> provincias){
    	provincias.sort(Comparator.comparing(Provincia::getNombre));
	    Collections.sort(provincias, new Comparator<Provincia>() {
	    	@Override
	    	public int compare(Provincia p1, Provincia p2) {
	    		return p1.getNombre().compareTo(p2.getNombre());
	    	}
	    });
	    return provincias;
    }
    
    public List<Ciudad> sortCiudades(Provincia provincia){
    	provincia.getCiudades().sort(Comparator.comparing(Ciudad::getNombre));
	    Collections.sort(provincia.getCiudades(), new Comparator<Ciudad>() {
	    	@Override
	    	public int compare(Ciudad c1, Ciudad c2) {
	    		return c1.getNombre().compareTo(c2.getNombre());
	    	}
	    });
	    return provincia.getCiudades();
    }
    
    public void cargarUbicaciones() {
    	DomicilioDAO.getDAO().cargarUbicaciones();
    }
}
