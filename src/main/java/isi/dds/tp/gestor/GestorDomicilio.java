package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import isi.dds.tp.dao.DAODomicilio;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.RiesgoCiudad;

public class GestorDomicilio {
	
	private static GestorDomicilio instanciaGestor = null;
	 
    private GestorDomicilio() { }

    public static GestorDomicilio get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorDomicilio();
        }    
        return instanciaGestor;
    }
    
    public void addRiesgoCiudad(RiesgoCiudad r) {
    	DAODomicilio.getDAO().addRiesgoCiudad(r);
    }
    
    public List<Pais> getPaises() {
    	return DAODomicilio.getDAO().getPaises();
    }
    
    public List<Provincia> getProvincias(Integer id_pais) {
    	return DAODomicilio.getDAO().getProvincias(id_pais);
    }
    
    public Float getUltimoRiesgoCiudad(Integer id_ciudad) {
    	return getUltimoRiesgoCiudadAux(id_ciudad).getValorPorcentual();
    }
    
	private RiesgoCiudad getUltimoRiesgoCiudadAux(Integer id_ciudad) {
		return DAODomicilio.getDAO().getUltimoRiesgoCiudad(id_ciudad);
	}
    
	public Ciudad getCiudad(Integer id_ciudad) {
		return DAODomicilio.getDAO().getCiudad(id_ciudad);
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
    
    public List<Provincia> sortProvincias(Pais pais){
    	pais.getProvincias().sort(Comparator.comparing(Provincia::getNombre));
	    Collections.sort(pais.getProvincias(), new Comparator<Provincia>() {
	    	@Override
	    	public int compare(Provincia p1, Provincia p2) {
	    		return p1.getNombre().compareTo(p2.getNombre());
	    	}
	    });
	    return pais.getProvincias();
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

	public RiesgoCiudad newRiesgoCiudad(Integer idCiudad, Float riesgo) {
		Ciudad ciudad = DAODomicilio.getDAO().getCiudad(idCiudad);
		RiesgoCiudad rviejo = getUltimoRiesgoCiudadAux(ciudad.getIdCiudad());
    	rviejo.setFechaFinVigencia(LocalDate.now());
    	
		RiesgoCiudad rnuevo = new RiesgoCiudad();
    	rnuevo.setFechaInicioVigencia(LocalDate.now());
    	rnuevo.setValorPorcentual(riesgo);    
    	ciudad.getRiesgos().add(rnuevo);
    	DAODomicilio.getDAO().updateCiudad(ciudad);;
    	return rnuevo;		
	}
}
