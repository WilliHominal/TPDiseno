package isi.dds.tp.gestor;

import java.time.LocalDate;

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
	
	public ParametrosPoliza getUltimoParametrosPoliza() {
		return DAOParametrosPoliza.getDAO().getUltimoParametrosPoliza();
	}
	
    public void nuevosParametros(ParametrosPoliza pNuevos) {
    	ParametrosPoliza pViejos = getUltimoParametrosPoliza(); 
    	pNuevos.setPorcentajeAjusteKm(pViejos.getPorcentajeAjusteKm());
    	pNuevos.setPorcentajeAlarma(pViejos.getPorcentajeAlarma());
    	pNuevos.setPorcentajeTuercasAntirobo(pViejos.getPorcentajeTuercasAntirobo());
    	pNuevos.setPorcentajeRastreoVehicular(pViejos.getPorcentajeRastreoVehicular());
    	pNuevos.setPorcentajePorHijoRegistrado(pViejos.getPorcentajePorHijoRegistrado());
    	pNuevos.setPorcentajeGuardaEnGarage(pViejos.getPorcentajeGuardaEnGarage());
    	pNuevos.setPorcentajeNingunSiniestro(pViejos.getPorcentajeNingunSiniestro());
		pNuevos.setPorcentajeUnSiniestro(pViejos.getPorcentajeUnSiniestro());
		pNuevos.setPorcentajeDosSiniestros(pViejos.getPorcentajeDosSiniestros());
		pNuevos.setPorcentajeMayorADosSiniestros(pViejos.getPorcentajeMayorADosSiniestros());
		pNuevos.setDescuentoUnidadAdicional(pViejos.getDescuentoUnidadAdicional());
		pNuevos.setValorDerechoEmision(pViejos.getValorDerechoEmision());
		//TODO ver si se persiste el ultimo o hay que hacer un update
		pViejos.setFechaFinVigencia(LocalDate.now());
    }
}
