package isi.dds.tp.gestor;

import isi.dds.tp.dao.DAOParametrosPoliza;
import isi.dds.tp.modelo.ParametrosPoliza;

public class GestorParametrosPoliza {
	
	private static GestorParametrosPoliza instanciaGestor = null;
	 
    private GestorParametrosPoliza() { }

    public static GestorParametrosPoliza get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametrosPoliza();
        }    
        return instanciaGestor;
    }
    
    public Float calcularPrima(Float porcentajeCobertura, Float porcentajeAjusteModelo, Float porcentajeAjusteDomicilio){
    	return null;
    }
    
    public Float calcularPremio() {
    	return null;
    }
    
    public Boolean verificarValorPorcentual() {
    	return null;
    }
    
    public Boolean verificarDerechoEmision() {
    	return null;
    }
    
    public Boolean verificarDescuentoPorUnidad() {
    	return null;
    }	
    
    public Float calcularDescuento() {
    	return null;
    }
	
    public void addParametrosPoliza(ParametrosPoliza p) {
    	DAOParametrosPoliza.getDAO().addParametrosPoliza(p);
    }

	public void cargarParametrosPoliza() {
		DAOParametrosPoliza.getDAO().cargarParametrosPoliza();
	}
}
