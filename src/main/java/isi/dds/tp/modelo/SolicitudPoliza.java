package isi.dds.tp.modelo;

import java.util.List;
import java.util.Map;
import isi.dds.tp.enums.*;

public class SolicitudPoliza {
	
	private List<HijoDeclarado> hijosDeclaradoss;
	private List<BitacoraSolicitudPoliza> bitacorasSolicitud;
	
	private Integer numeroSolicitud; 
	private Map<Integer, String> patente; 
	private Map<String, String> motor;
	private Map<String, String> chasis;
	private Map<Integer, String> kmRealizadosPorAnio;
	private Map<String, EnumSiniestros> numerosSiniestrosUltimoAnios; 
	private Map<String, Boolean> tieneAlarma;
	private Map<Integer, Boolean> guardaGarage;
	private Map<String, Boolean> tieneTuercasAntirobo; 
	private Map<String, Boolean> tieneRastreoVehicular;
	private Map<String, EnumTipoCobertura> tipoCobertura;
	
	
	public List<HijoDeclarado> getHijosDeclaradoss() {
		return hijosDeclaradoss;
	}
	public List<BitacoraSolicitudPoliza> getBitacorasSolicitud() {
		return bitacorasSolicitud;
	}
	public Integer getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setHijosDeclaradoss(List<HijoDeclarado> hijosDeclaradoss) {
		this.hijosDeclaradoss = hijosDeclaradoss;
	}
	public void setBitacorasSolicitud(List<BitacoraSolicitudPoliza> bitacorasSolicitud) {
		this.bitacorasSolicitud = bitacorasSolicitud;
	}
	public void setNumeroSolicitud(Integer numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	} 
		
}
