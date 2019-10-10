package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.List;
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
    	
    	PolizaDAO.getDAO().addPoliza(p);
    }
    
    public void addCuota(Cuota c) {

    	PolizaDAO.getDAO().addCuota(c);

    }
    
    public void addSolicitudPoliza(SolicitudPoliza s) {

    	PolizaDAO.getDAO().addSolicitudPoliza(s);
    }
    
    public void addHijoDeclarado(HijoDeclarado h) {

    	PolizaDAO.getDAO().addHijoDeclarado(h);
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
       
	public List<Poliza> getPolizas(Long numeroCliente) {
    	
    	List<Poliza> polizas = PolizaDAO.getDAO().getPolizas(numeroCliente);
        
        return polizas;
    }


	public List<Cuota> getCuotas(Long numeroPoliza) {

    	List<Cuota> cuotas = PolizaDAO.getDAO().getCuotas(numeroPoliza);
        
        return cuotas;
    }
    
    public SolicitudPoliza getSolicitudPoliza(Long numeroPoliza) {

    	SolicitudPoliza solicitud = PolizaDAO.getDAO().getSolicitudPoliza(numeroPoliza);
    	
        return solicitud;
    }
    
    
	public List<HijoDeclarado> getHijosDeclarados(Long numeroPoliza) {

    	List<HijoDeclarado> hijos = PolizaDAO.getDAO().getHijosDeclarados(numeroPoliza);
        
        return hijos;
    }
    
    public void actualizarPoliza(Poliza p){
    	
    }
    
    public Poliza getPoliza(Long numeroPoliza) {
		return null;    	
    }
    
    public SolicitudPoliza getSolicitud(Long numeroPoliza) {
    	return null;
    }
    
    //AGREGAR LOS DE CUOTA

}
