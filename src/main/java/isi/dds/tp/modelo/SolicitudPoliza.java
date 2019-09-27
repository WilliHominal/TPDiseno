package isi.dds.tp.modelo;

import java.util.*;
import isi.dds.tp.enums.*;

public class SolicitudPoliza {
	
	private List<HijoDeclarado> hijosDeclarados;
	private List<BitacoraSolicitudPoliza> bitacorasSolicitud;
	
	private Integer numeroSolicitud; /*CLAVE: numeroSolicitud*/
	private String patente; /*CLAVE: patente*/
	private String motor; /*CLAVE: motor*/
	private String chasis; /*CLAVE: chasis*/
	private String kmRealizadosPorAnio; /*CLAVE: kmRealizadosPorAnio*/
	private EnumSiniestros numerosSiniestrosUltimoAnios; /*CLAVE: numerosSiniestrosUltimoAnios*/ 
	private Boolean tieneAlarma; /*CLAVE: tieneAlarma*/
	private Boolean guardaGarage; /*CLAVE: guardaGarage*/
	private Boolean tieneTuercasAntirobo; /*CLAVE: tieneTuercasAntirobo*/ 
	private Boolean tieneRastreoVehicular; /*CLAVE: tieneRastreoVehicular*/
	private EnumTipoCobertura tipoCobertura; /*CLAVE: tipoCobertura*/
		
	public SolicitudPoliza(Poliza p, Integer numeroSolicitud) {
		p.setSolicitudPoliza(this);
		this.hijosDeclarados = p.getHijosDeclarado();
		this.bitacorasSolicitud = new ArrayList<BitacoraSolicitudPoliza>();
		this.numeroSolicitud = numeroSolicitud;
		this.patente = p.getPatente(); 
		this.motor = p.getMotor();
		this.chasis = p.getChasis();
		this.kmRealizadosPorAnio = p.getKmRealizadosPorAnio();
		this.numerosSiniestrosUltimoAnios = p.getNumerosSiniestrosUltimoAnios(); 
		this.tieneAlarma = p.getTieneAlarma();
		this.guardaGarage = p.getGuardaGarage();
		this.tieneTuercasAntirobo = p.getTieneTuercasAntirobo(); 
		this.tieneRastreoVehicular = p.getTieneRastreoVehicular();
		this.tipoCobertura = p.getTipoCobertura().getTipoCobertura();
	}
	
	public List<HijoDeclarado> getHijosDeclarados() {
		return hijosDeclarados;
	}
	public List<BitacoraSolicitudPoliza> getBitacorasSolicitud() {
		return bitacorasSolicitud;
	}
	public Integer getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setHijosDeclarados(List<HijoDeclarado> hijosDeclaradoss) {
		this.hijosDeclarados = hijosDeclaradoss;
	}
	public void setBitacorasSolicitud(List<BitacoraSolicitudPoliza> bitacorasSolicitud) {
		this.bitacorasSolicitud = bitacorasSolicitud;
	}
	public void setNumeroSolicitud(Integer numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public String getPatente() {
		return patente;
	}
	public String getMotor() {
		return motor;
	}
	public String getChasis() {
		return chasis;
	}
	public String getKmRealizadosPorAnio() {
		return kmRealizadosPorAnio;
	}
	public EnumSiniestros getNumerosSiniestrosUltimoAnios() {
		return numerosSiniestrosUltimoAnios;
	}
	public Boolean getTieneAlarma() {
		return tieneAlarma;
	}
	public Boolean getGuardaGarage() {
		return guardaGarage;
	}
	public Boolean getTieneTuercasAntirobo() {
		return tieneTuercasAntirobo;
	}
	public Boolean getTieneRastreoVehicular() {
		return tieneRastreoVehicular;
	}
	public EnumTipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	public void setKmRealizadosPorAnio(String kmRealizadosPorAnio) {
		this.kmRealizadosPorAnio = kmRealizadosPorAnio;
	}
	public void setNumerosSiniestrosUltimoAnios(EnumSiniestros numerosSiniestrosUltimoAnios) {
		this.numerosSiniestrosUltimoAnios = numerosSiniestrosUltimoAnios;
	}
	public void setTieneAlarma(Boolean tieneAlarma) {
		this.tieneAlarma = tieneAlarma;
	}
	public void setGuardaGarage(Boolean guardaGarage) {
		this.guardaGarage = guardaGarage;
	}
	public void setTieneTuercasAntirobo(Boolean tieneTuercasAntirobo) {
		this.tieneTuercasAntirobo = tieneTuercasAntirobo;
	}
	public void setTieneRastreoVehicular(Boolean tieneRastreoVehicular) {
		this.tieneRastreoVehicular = tieneRastreoVehicular;
	}
	public void setTipoCobertura(EnumTipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}
}
