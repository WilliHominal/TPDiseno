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
    
    public Boolean verificarValorPorcentual() {
    	//TODO implementar
    	return null;
    }
    
    public Boolean verificarDerechoEmision() {
    	//TODO implementar
    	return null;
    }
    
    public Boolean verificarDescuentoPorUnidad() {
    	//TODO implementar
    	return null;
    }	
    
    public Float calcularDescuento() {
    	//TODO implementar
    	return null;
    }
	
    public void addParametrosPoliza(ParametrosPoliza p) {
    	DAOParametrosPoliza.getDAO().addParametrosPoliza(p);
    }

	public void cargarParametrosPoliza() {
		DAOParametrosPoliza.getDAO().cargarParametrosPoliza();
	}
	
	public ParametrosPoliza getUltimoParametrosPoliza() {
		return DAOParametrosPoliza.getDAO().getUltimoParametrosPoliza();
	}
}
