package isi.dds.tp.gestor;

import java.util.ArrayList;
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
	       
 
    }
    
    public void addModelo(Modelo m) {

    	
    }
    
    public void addMarca(Marca m){
    	

    }
    
    public void addAnioModelo(AnioModelo a) {


    }
   
	public List<Marca> getMarcas() {
    	ArrayList<Marca> marcas = (ArrayList<Marca>) ParametrosVehiculosDAO.getDAO().getMarcas(); 
        return marcas;
    }
    

	public List<Modelo> getModelos(Integer id_marca) {
    	
    	List<Modelo> modelos = null;
    	
        
    	return modelos;
    }
    
	public List<AnioModelo> getAniosModelo(Integer id_modelo) {
		
    	List<AnioModelo> aniosModelo = null;
    	
    	
    	return aniosModelo;
    }
    

	public List<RiesgoModelo> getRiesgosModelo(Integer id_modelo) {
		
    	List<RiesgoModelo> riesgosModelo = null;

    	return riesgosModelo;
    }
}
