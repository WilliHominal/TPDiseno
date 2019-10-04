package isi.dds.tp.gestor;

import java.time.LocalDate;

import isi.dds.tp.dao.PolizaDAO;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.SolicitudPoliza;
import isi.dds.tp.modelo.Cuota;

public class GestorPoliza {
	
	private static GestorPoliza instanciaGestor = null;
	 
    private GestorPoliza() {

    }

    public static GestorPoliza get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPoliza();
        }    
        return instanciaGestor;
    }
    
    public void altaPoliza(Poliza p) {
    	
    }
    public void generarNumeroPoliza(Poliza P) {
    	
    }
    public void declararHijo(Poliza P, LocalDate fechaNac, String sexo, String estadoCivil) {
    	
    }
    public Boolean verificarEdadHijo(HijoDeclarado h) {
    	return null;
    }
    
    public Boolean agregarDeclaracionHijo(Poliza p, HijoDeclarado h) {
    	return null;
    }
    
    public void borrarDeclaracionHijo(Poliza p, HijoDeclarado h){
    	
    }
    
    public Boolean validarHijosRegistrados(Poliza P) {
    	return null;
    }
    
    public SolicitudPoliza generarSolicitud(Poliza P){
    	return null;
    }
    
    public void actualizarSolictud(SolicitudPoliza s){
    	
    }
    
    public void actualizarPoliza(Poliza p){
    	
    }
    

}
