package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.dao.DAOParametrosVehiculo;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.RiesgoModelo;

public class GestorParametrosVehiculo {
	
	private static GestorParametrosVehiculo instanciaGestor = null;
	 
    private GestorParametrosVehiculo() { }

    public static GestorParametrosVehiculo get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametrosVehiculo();
        }    
        return instanciaGestor;
    }
    
	public List<Marca> getMarcas() {
		return DAOParametrosVehiculo.getDAO().getMarcas();
    }
	
	public AnioModelo getAnioModelo(Integer id) {
		return DAOParametrosVehiculo.getDAO().getAnioModelo(id);
    }
	
    public Float getUltimoRiesgoModelo(Integer id_modelo) {
    	return getUltimoRiesgoModeloAux(id_modelo).getValorPorcentual();
    }
    
	private RiesgoModelo getUltimoRiesgoModeloAux(Integer id_modelo) {
		return DAOParametrosVehiculo.getDAO().getUltimoRiesgoModelo(id_modelo);
	}
    
    public List<Modelo> sortModelos(Marca marca){
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

	public RiesgoModelo newRiesgoModelo(Integer idModelo, Float riesgo) {
		Modelo modelo = DAOParametrosVehiculo.getDAO().getModelo(idModelo);
		RiesgoModelo rviejo = getUltimoRiesgoModeloAux(modelo.getIdModelo());
    	rviejo.setFechaFinVigencia(LocalDate.now());
		
		RiesgoModelo rnuevo = new RiesgoModelo();
    	rnuevo.setFechaInicioVigencia(LocalDate.now());
    	rnuevo.setValorPorcentual(riesgo);
    	modelo.getRiesgos().add(rnuevo);
    	DAOParametrosVehiculo.getDAO().updateModelo(modelo);
    	return rnuevo;
	}
}
