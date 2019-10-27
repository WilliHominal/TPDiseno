package isi.dds.tp.gestor;

import java.util.List;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.SolicitudPoliza;
import isi.dds.tp.modelo.Cuota;

public class GestorPoliza {
	
	private static GestorPoliza instanciaGestor = null;
	 
    private GestorPoliza() { }

    public static GestorPoliza get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPoliza();
        }    
        return instanciaGestor;
    }
    
    public void altaPoliza(Poliza p) {
    	DAOPoliza.getDAO().addPoliza(p);
    }
    
    public void addSolicitudPoliza(SolicitudPoliza s) {
    	DAOPoliza.getDAO().addSolicitudPoliza(s);
    }
    
    public void generarNumeroPoliza(Poliza P) {
    	
    }
    
    public SolicitudPoliza generarSolicitud(Poliza P){
    	return null;
    }
    
    public void actualizarSolictud(SolicitudPoliza s){
    	
    }

	public List<Cuota> getCuotas(Long numeroPoliza) {
		return DAOPoliza.getDAO().getCuotas(numeroPoliza);
    }
    
    public SolicitudPoliza getSolicitudPoliza(Long numeroPoliza) {
    	return DAOPoliza.getDAO().getSolicitudPoliza(numeroPoliza);
    }
    
	public List<HijoDeclarado> getHijosDeclarados(Long numeroPoliza) {
		return DAOPoliza.getDAO().getHijosDeclarados(numeroPoliza);
    }
    
    public void actualizarPoliza(Poliza p){
    	
    }
    
    public Poliza getPoliza(Long numeroPoliza) {
		return null;    	
    }
    
    public SolicitudPoliza getSolicitud(Long numeroPoliza) {
    	return null;
    }

	public void cargarPolizas() {
		DAOPoliza.getDAO().cargarPolizas();
	}

}
