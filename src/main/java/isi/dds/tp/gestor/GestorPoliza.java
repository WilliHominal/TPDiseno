package isi.dds.tp.gestor;

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
    
    public void addSolicitudPoliza(SolicitudPoliza s) {

    	PolizaDAO.getDAO().addSolicitudPoliza(s);
    }
    
    public void generarNumeroPoliza(Poliza P) {
    	
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

}
