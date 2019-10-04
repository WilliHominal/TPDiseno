package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;

import java.util.List;

import isi.dds.tp.dao.ClienteDAO;

public class GestorCliente {
	
	private static GestorCliente instanciaGestor = null;
	 
    private GestorCliente() {

    }

    public static GestorCliente getGestorCliente() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }    
        return instanciaGestor;
    }

    public void AltaCliente(Cliente c) {
    	
    }
    
    public Integer calcularTiempoActivo(Cliente c) {
    	return null;
    }
    
    public Boolean validacionDatosCliente(Cliente c) {
		return null;
    }
    
    public Boolean validacionCUIL(Cliente c) {
    	return null;
    }
    
    public Boolean comprobarEdad(Cliente c) {
    	return null;
    }
    
    public void actualizarCondicion(Cliente c) {

    }
    
    public Boolean validarFechaVigencia(Cliente c) {
    	return null;
    }
    
    public void generarNumeroCliente(Cliente c) {

    }
    
    public Cliente actualizarCliente(Cliente c) {
    	return null;
    }

    public Cliente getCliente(Long numeroCliente) {
    	return null;
    }
    
    public List<Poliza> getPoliza(Long numeroCliente) {
    	return null;
    }
    
}
