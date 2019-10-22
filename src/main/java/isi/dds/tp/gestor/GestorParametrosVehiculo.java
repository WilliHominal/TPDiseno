package isi.dds.tp.gestor;

import java.util.Collections;
import java.util.Comparator;
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
    
    public void addModelo(Modelo m) {
    	ParametrosVehiculosDAO.getDAO().addModelo(m);
    }
    
    public void addMarca(Marca m){
    	ParametrosVehiculosDAO.getDAO().addMarca(m);
    }
    
    public void addAnioModelo(AnioModelo a) {
    	ParametrosVehiculosDAO.getDAO().addAnioModelo(a);
    }
   
	public List<Marca> getMarcas() {
		return ParametrosVehiculosDAO.getDAO().getMarcas();
    }
    /*
	public List<Modelo> getModelos(Integer id_marca) {
		return ParametrosVehiculosDAO.getDAO().getModelos(id_marca);
    }
    
	public List<AnioModelo> getAniosModelo(Integer id_modelo) {
		return ParametrosVehiculosDAO.getDAO().getAniosModelo(id_modelo);
    }
    
	public List<RiesgoModelo> getRiesgosModelo(Integer id_modelo) {
		return ParametrosVehiculosDAO.getDAO().getRiesgosModelo(id_modelo);
    }*/
	
    public RiesgoModelo getUltimoRiesgoModelo(Integer id_modelo) {
    	
    	RiesgoModelo riesgo = ParametrosVehiculosDAO.getDAO().getUltimoRiesgoModelo(id_modelo);

    	return riesgo;
    }
    
    public List<Modelo> sortModelos(List<Modelo> lista){
    	lista.sort(Comparator.comparing(Modelo::getNombre));
	    Collections.sort(lista, new Comparator<Modelo>() {
	    	@Override
	    	public int compare(Modelo m1, Modelo m2) {
	    		return m1.getNombre().compareTo(m2.getNombre());
	    	}
	    });
	    return lista;
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
}
