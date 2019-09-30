package isi.dds.tp.modelo;

import java.util.*;
import isi.dds.tp.enums.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "solicitud_poliza")
public class SolicitudPoliza {
	
	@OneToMany()
	@JoinColumn(name="numero_solicitud")
	@IndexColumn(name="idx")
	private List<HijoDeclarado> hijosDeclarados;
	
	@OneToMany()
	@JoinColumn(name="numero_solicitud")
	@IndexColumn(name="idx")
	private List<BitacoraSolicitudPoliza> bitacorasSolicitud;

	@Id
	/*TODO generar numero solicitud, CAMBIAR en el new*/
	private Integer numeroSolicitud;
	
	@Column(nullable = false)
	private String patente;
	
	@Column(nullable = false)
	private String motor;
	
	@Column(nullable = false)
	private String chasis;
	
	@Column(nullable = false, name = "km_realizados_por_anio")
	private String kmRealizadosPorAnio;
	
	@Column(nullable = false, name = "numeros_siniestros_ultimo_anios")
	@Enumerated(EnumType.STRING)
	private EnumSiniestros numerosSiniestrosUltimoAnios;
	
	@Column(nullable = false, name = "tiene_alarma")
	private Boolean tieneAlarma;
	
	@Column(nullable = false, name = "guarda_garage")
	private Boolean guardaGarage;
	
	@Column(nullable = false, name = "tiene_tuercas_antirobo")
	private Boolean tieneTuercasAntirobo;
	
	@Column(nullable = false, name = "tiene_rastreo_vehicular")
	private Boolean tieneRastreoVehicular;	
	
	@Column(nullable = false, name = "tipo_cobertura")
	@Enumerated(EnumType.STRING)
	private EnumTipoCobertura tipoCobertura; 
		
	public SolicitudPoliza() {
		
	}
	
	public SolicitudPoliza(Poliza p, Integer numeroSolicitud) {
		p.setSolicitudPoliza(this);
		this.hijosDeclarados = p.getHijosDeclarado();
		this.bitacorasSolicitud = new ArrayList<BitacoraSolicitudPoliza>();
		
		
		this.numeroSolicitud = numeroSolicitud;
		
		//this.numeroSolicitud = p.getNumeroPoliza();
		
		
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
