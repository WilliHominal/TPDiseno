package isi.dds.tp.gestor;

import isi.dds.tp.dao.SiniestrosDAO;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;

public class GestorSubsistemaSiniestros {

	private static GestorSubsistemaSiniestros instanciaGestor = null;
	 
    private GestorSubsistemaSiniestros() {

    }

    public static GestorSubsistemaSiniestros get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSubsistemaSiniestros();
        }    
        return instanciaGestor;
    }

	public EnumSiniestros getSiniestrosUltimosAnios(EnumTipoDocumento tipoDocumento, String documento, int anio) {
		return SiniestrosDAO.getDAO().getSiniestroUltimoAnio(tipoDocumento, documento, anio).getSiniestros();
	}
	
	
}
