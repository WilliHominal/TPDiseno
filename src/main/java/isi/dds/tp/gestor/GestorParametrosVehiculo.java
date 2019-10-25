package isi.dds.tp.gestor;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.dao.ParametrosVehiculosDAO;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.RiesgoModelo;

public class GestorParametrosVehiculo {
	
	private static GestorParametrosVehiculo instanciaGestor = null;
	 
    private GestorParametrosVehiculo() {
    }

    public static GestorParametrosVehiculo get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametrosVehiculo();
        }    
        return instanciaGestor;
    }

    public void addRiesgoModelo(RiesgoModelo r) {
    	ParametrosVehiculosDAO.getDAO().addRiesgoModelo(r);
    }
    
    public void addMarca(Marca m){
		Iterator<Modelo> iteratorModelo = sortModelos(m.getModelos()).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next().getAnios());
		}	
    	ParametrosVehiculosDAO.getDAO().addMarca(m);
    }
    
    public void addModelo(Modelo m) {
		Iterator<Modelo> iteratorModelo = sortModelos(m.getMarca().getModelos()).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next().getAnios());
		}	
    	ParametrosVehiculosDAO.getDAO().addModelo(m);
    }
    
    public void addAnioModelo(AnioModelo a) {
		Iterator<Modelo> iteratorModelo = sortModelos(a.getModelo().getMarca().getModelos()).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next().getAnios());
		}
    	ParametrosVehiculosDAO.getDAO().addAnioModelo(a);
    }
   
	public List<Marca> getMarcas() {
		return ParametrosVehiculosDAO.getDAO().getMarcas();
    }
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	
    	RiesgoModelo riesgo = ParametrosVehiculosDAO.getDAO().getUltimoRiesgoModelo(id_modelo);

    	return riesgo;
    }
    
    public List<Marca> sortMarca(List<Marca> lista){
    	lista.sort(Comparator.comparing(Marca::getNombre));
	    Collections.sort(lista, new Comparator<Marca>() {
	    	@Override
	    	public int compare(Marca o1, Marca o2) {
	    		return o1.getNombre().compareTo(o2.getNombre());
	    	}
	    });
	    return lista;
    }
    
    private List<Modelo> sortModelos(List<Modelo> lista){
    	lista.sort(Comparator.comparing(Modelo::getNombre));
	    Collections.sort(lista, new Comparator<Modelo>() {
	    	@Override
	    	public int compare(Modelo m1, Modelo m2) {
	    		return m1.getNombre().compareTo(m2.getNombre());
	    	}
	    });
	    return lista;
    }
    
    public List<AnioModelo> sortAniosModelo(List<AnioModelo> lista){
    	lista.sort(Comparator.comparing(AnioModelo::getAnio));
	    Collections.sort(lista, new Comparator<AnioModelo>() {
	    	@Override
	    	public int compare(AnioModelo o1, AnioModelo o2) {
	    		return o1.getAnio().compareTo(o2.getAnio());
	    	}
	    });
	    return lista;
    }

	public void cargarParametrosVehiculos() {
		ParametrosVehiculosDAO.getDAO().cargarParametrosVehiculos();
	}
}
