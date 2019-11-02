package isi.dds.tp.modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import isi.dds.tp.enums.EnumSiniestros;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "solicitud_poliza")
public class SolicitudPoliza {
	
	@JoinColumn(name = "tipo_cobertura")
    @OneToOne
    private TipoCobertura tipoCobertura;

	@JoinColumn(name = "anio_modelo")
    @OneToOne
	private AnioModelo anioModelo;
	
	@JoinColumn
    @OneToOne
	private Ciudad ciudad;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="numero_solicitud")
	@IndexColumn(name="idx")
	private List<HijoDeclarado> hijosDeclarados;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="numero_solicitud")
	@IndexColumn(name="idx")
	private List<BitacoraSolicitudPoliza> bitacorasSolicitud;

	@Id
	@Column(name = "numero_solicitud")
	private Integer numeroSolicitud;
	
	@Column(nullable = false)
	private String patente;
	
	@Column(nullable = false)
	private String motor;
	
	@Column(nullable = false)
	private String chasis;
	
	@Column(nullable = false, name = "km_realizados_por_anio")
	private String kmRealizadosPorAnio;
	
	@Column(nullable = false,name = "numeros_siniestros_ultimo_anios")
	@Enumerated(EnumType.STRING)
	private EnumSiniestros numerosSiniestrosUltimoAnios;
	
	@Column(nullable = false,name = "tiene_alarma")
	private Boolean tieneAlarma;
	
	@Column(nullable = false,name = "guarda_garage")
	private Boolean guardaGarage;
	
	@Column(nullable = false,name = "tiene_tuercas_antirobo")
	private Boolean tieneTuercasAntirobo;
	
	@Column(nullable = false,name = "tiene_rastreo_vehicular")
	private Boolean tieneRastreoVehicular;	
		
	public SolicitudPoliza() { }
	
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
	
	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}
	
	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}
	
	public AnioModelo getAnioModelo() {
		return anioModelo;
	}
	public void setAnioModelo(AnioModelo anioModelo) {
		this.anioModelo = anioModelo;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
}
