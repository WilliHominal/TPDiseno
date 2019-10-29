package isi.dds.tp.gestor;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.dao.DAOParametrosVehiculos;
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
    	DAOParametrosVehiculos.getDAO().addRiesgoModelo(r);
    }
    
    public void addMarca(Marca m){
		Iterator<Modelo> iteratorModelo = sortModelos(m).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next());
		}	
    	DAOParametrosVehiculos.getDAO().addMarca(m);
    }
    
    public void addModelo(Modelo m) {
		Iterator<Modelo> iteratorModelo = sortModelos(m.getMarca()).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next());
		}	
    	DAOParametrosVehiculos.getDAO().addModelo(m);
    }
    
    public void addAnioModelo(AnioModelo a) {
		Iterator<Modelo> iteratorModelo = sortModelos(a.getModelo().getMarca()).iterator();
		while(iteratorModelo.hasNext()){
			sortAniosModelo(iteratorModelo.next());
		}
    	DAOParametrosVehiculos.getDAO().addAnioModelo(a);
    }
   
	public List<Marca> getMarcas() {
		return DAOParametrosVehiculos.getDAO().getMarcas();
    }
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	
    	RiesgoModelo riesgo = DAOParametrosVehiculos.getDAO().getUltimoRiesgoModelo(id_modelo);

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
    
    private List<Modelo> sortModelos(Marca marca){
    	marca.getModelos().sort(Comparator.comparing(Modelo::getNombre));
	    Collections.sort(marca.getModelos(), new Comparator<Modelo>() {
	    	@Override
	    	public int compare(Modelo m1, Modelo m2) {
	    		return m1.getNombre().compareTo(m2.getNombre());
	    	}
	    });
	    return marca.getModelos();
    }
    
    public List<AnioModelo> sortAniosModelo(Modelo modelo){
    	modelo.getAnios().sort(Comparator.comparing(AnioModelo::getAnio));
	    Collections.sort(modelo.getAnios(), new Comparator<AnioModelo>() {
	    	@Override
	    	public int compare(AnioModelo o1, AnioModelo o2) {
	    		return o1.getAnio().compareTo(o2.getAnio());
	    	}
	    });
	    return modelo.getAnios();
    }

	public void cargarParametrosVehiculos() {
		DAOParametrosVehiculos.getDAO().cargarParametrosVehiculos();
	}
}
