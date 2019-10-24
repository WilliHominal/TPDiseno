package isi.dds.tp.gestor;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
		Iterator<Provincia> iteratorModelo = sortProvincias(p.getProvincias()).iterator();
		while(iteratorModelo.hasNext()){
			sortCiudades(iteratorModelo.next().getCiudades());
		}	
    	DomicilioDAO.getDAO().addPais(p);
    }
    
    public void addProvincia(Provincia p) {
		Iterator<Provincia> iteratorModelo = sortProvincias(p.getPais().getProvincias()).iterator();
		while(iteratorModelo.hasNext()){
			sortCiudades(iteratorModelo.next().getCiudades());
		}	
    	DomicilioDAO.getDAO().addProvincia(p);
    }
    
    public void addCiudad(Ciudad c) {
		Iterator<Provincia> iteratorModelo = sortProvincias(c.getProvincia().getPais().getProvincias()).iterator();
		while(iteratorModelo.hasNext()){
			sortCiudades(iteratorModelo.next().getCiudades());
		}	
    	DomicilioDAO.getDAO().addCiudad(c);
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
    
    private List<Provincia> sortProvincias(List<Provincia> lista){
    	lista.sort(Comparator.comparing(Provincia::getNombre));
	    Collections.sort(lista, new Comparator<Provincia>() {
	    	@Override
	    	public int compare(Provincia p1, Provincia p2) {
	    		return p1.getNombre().compareTo(p2.getNombre());
	    	}
	    });
	    return lista;
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
    
    public void cargarUbicaciones() {
    	DomicilioDAO.getDAO().cargarUbicaciones();
    }
}
