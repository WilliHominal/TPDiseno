package isi.dds.tp.gestor;

import isi.dds.tp.dao.BitacoraDAO;
import isi.dds.tp.modelo.BitacoraSolicitudPoliza;
import isi.dds.tp.modelo.BitacoraParametrosPoliza;

public class GestorParametroPoliza {
	
	private static GestorParametroPoliza instanciaGestor = null;
	 
    private GestorParametroPoliza() {

    }

    public static GestorParametroPoliza getGestorParametroPoliza() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorParametroPoliza();
        }    
        return instanciaGestor;
    }
    
    public Float calcularPrima(Float porcentajeCobertura, Float porcentajeAjusteModelo, Float porcentajeAjusteDomicilio){
    	return null;
    }
    
    public Float calcularPremio(float prima, float derechoEmision) {
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
    
    public Float calcularDescuento(Boolean descuentoMasDeUnaUnidad, Boolean descuentoSemestral) {
    	return null;
    }

	public Float getDerechoEmision() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
