package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parametros_poliza")
public class ParametrosPoliza {
	
	//optional permita que la relacion pueda ser nual, que seria el caso cuando se crea por primera vez una ciudad
	@ManyToOne (optional = true, fetch = FetchType.LAZY)
	@JoinColumn (name = "codigo_bitacora")
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_parametros")
	@SequenceGenerator(name="codigo_parametros", sequenceName = "codigo_parametros_seq", initialValue = 64, allocationSize = 3)
	@Column(nullable = false, name = "codigo_parametros")
	private Integer codigoParametroPoliza;
	
	@Column(nullable = false, name = "inicio_vigencia")
	private LocalDate fechaInicioVigencia;
	
	@Column(name = "fin_vigencia")
	private LocalDate fechaFinVigencia;
	
	@Column(nullable = false, name = "porcentaje_tuercas_antirobo")
	private Float porcentajeTuercasAntirobo;
	
	@Column(nullable = false, name = "porcentaje_guarda_en_garage")
	private Float porcentajeGuardaEnGarage;
	
	@Column(nullable = false, name = "porcentaje_alarma")
	private Float porcentajeAlarma;
	
	@Column(nullable = false, name = "porcentaje_rastreo_vehicular")
	private Float porcentajeRastreoVehicular;
	
	@Column(nullable = false, name = "porcentaje_ajuste_km")
	private Float porcentajeAjusteKm;
	
	@Column(nullable = false, name = "porcentaje_ningun_siniestro")
	private Float porcentajeNingunSiniestro;
	
	@Column(nullable = false, name = "porcentaje_un_siniestro")
	private Float porcentajeUnSiniestro;
	
	@Column(nullable = false, name = "porcentaje_dos_siniestro")
	private Float porcentajeDosSiniestro; 
	
	@Column(nullable = false, name = "porcentaje_mayor_a_dos_siniestro")
	private Float porcentajeMayorADosSiniestro;
	
	@Column(nullable = false, name = "porcentaje_por_hijo_registrado")
	private Float porcentajePorHijoRegistrado;
	
	@Column(nullable = false, name = "descuento_unidad_adicional")
	private Float descuentoUnidadAdicional;
	
	@Column(nullable = false, name = "valor_derecho_emision")
	private Float valorDerechoEmision;
	
	public ParametrosPoliza (){ }
	
	//TODO quitar
	public ParametrosPoliza(Integer codigoParametroPoliza, Float porcentajeTuercasAntirobo, 
			Float porcentajeGuardaEnGarage, Float porcentajeAlarma, Float porcentajeRastreoVehicular,
			Float porcentajeAjusteKm, Float porcentajeNingunSiniestro, Float porcentajeUnSiniestro,
			Float porcentajeDosSiniestro, Float porcentajeMayorADosSiniestro, Float porcentajePorHijoRegistrado,
			Float descuentoUnidadAdicional, Float valorDerechoEmision) {
		this.bitacoraParametros = null;
		this.codigoParametroPoliza = codigoParametroPoliza;
		this.fechaInicioVigencia = LocalDate.now();
		this.fechaFinVigencia = null;
		this.porcentajeTuercasAntirobo = porcentajeTuercasAntirobo;
		this.porcentajeGuardaEnGarage = porcentajeGuardaEnGarage;
		this.porcentajeAlarma = porcentajeAlarma;
		this.porcentajeRastreoVehicular = porcentajeRastreoVehicular;
		this.porcentajeAjusteKm = porcentajeAjusteKm;
		this.porcentajeNingunSiniestro = porcentajeNingunSiniestro;
		this.porcentajeUnSiniestro = porcentajeUnSiniestro;
		this.porcentajeDosSiniestro = porcentajeDosSiniestro;
		this.porcentajeMayorADosSiniestro = porcentajeMayorADosSiniestro;
		this.porcentajePorHijoRegistrado = porcentajePorHijoRegistrado;
		this.descuentoUnidadAdicional = descuentoUnidadAdicional;
		this.valorDerechoEmision = valorDerechoEmision;
	}
	
	public BitacoraParametrosPoliza getBitacoraParametros() {
		return bitacoraParametros;
	}
	public Integer getCodigoParametroPoliza() {
		return codigoParametroPoliza;
	}
	public LocalDate getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public LocalDate getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public Float getPorcentajeTuercasAntirobo() {
		return porcentajeTuercasAntirobo;
	}
	public Float getPorcentajeGuardaEnGarage() {
		return porcentajeGuardaEnGarage;
	}
	public Float getPorcentajeAlarma() {
		return porcentajeAlarma;
	}
	public Float getPorcentajeRastreoVehicular() {
		return porcentajeRastreoVehicular;
	}
	public Float getPorcentajeAjusteKm() {
		return porcentajeAjusteKm;
	}
	public Float getPorcentajeNingunSiniestro() {
		return porcentajeNingunSiniestro;
	}
	public Float getPorcentajeUnSiniestro() {
		return porcentajeUnSiniestro;
	}
	public Float getPorcentajeDosSiniestro() {
		return porcentajeDosSiniestro;
	}
	public Float getPorcentajeMayorADosSiniestro() {
		return porcentajeMayorADosSiniestro;
	}
	public Float getPorcentajePorHijoRegistrado() {
		return porcentajePorHijoRegistrado;
	}
	public Float getDescuentoUnidadAdicional() {
		return descuentoUnidadAdicional;
	}
	public Float getValorDerechoEmision() {
		return valorDerechoEmision;
	}
	public void setBitacoraParametros(BitacoraParametrosPoliza bitacoraParametros) {
		this.bitacoraParametros = bitacoraParametros;
	}
	public void setCodigoParametroPoliza(Integer codigoParametroPoliza) {
		this.codigoParametroPoliza = codigoParametroPoliza;
	}
	public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public void setFechaFinVigencia(LocalDate fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	public void setPorcentajeTuercasAntirobo(Float porcentajeTuercasAntirobo) {
		this.porcentajeTuercasAntirobo = porcentajeTuercasAntirobo;
	}
	public void setPorcentajeGuardaEnGarage(Float porcentajeGuardaEnGarage) {
		this.porcentajeGuardaEnGarage = porcentajeGuardaEnGarage;
	}
	public void setPorcentajeAlarma(Float porcentajeAlarma) {
		this.porcentajeAlarma = porcentajeAlarma;
	}
	public void setPorcentajeRastreoVehicular(Float porcentajeRastreoVehicular) {
		this.porcentajeRastreoVehicular = porcentajeRastreoVehicular;
	}
	public void setPorcentajeAjusteKm(Float porcentajeAjusteKm) {
		this.porcentajeAjusteKm = porcentajeAjusteKm;
	}
	public void setPorcentajeNingunSiniestro(Float porcentajeNingunSiniestro) {
		this.porcentajeNingunSiniestro = porcentajeNingunSiniestro;
	}
	public void setPorcentajeUnSiniestro(Float porcentajeUnSiniestro) {
		this.porcentajeUnSiniestro = porcentajeUnSiniestro;
	}
	public void setPorcentajeDosSiniestro(Float porcentajeDosSiniestro) {
		this.porcentajeDosSiniestro = porcentajeDosSiniestro;
	}
	public void setPorcentajeMayorADosSiniestro(Float porcentajeMayorADosSiniestro) {
		this.porcentajeMayorADosSiniestro = porcentajeMayorADosSiniestro;
	}
	public void setPorcentajePorHijoRegistrado(Float porcentajePorHijoRegistrado) {
		this.porcentajePorHijoRegistrado = porcentajePorHijoRegistrado;
	}
	public void setDescuentoUnidadAdicional(Float descuentoUnidadAdicional) {
		this.descuentoUnidadAdicional = descuentoUnidadAdicional;
	}
	public void setValorDerechoEmision(Float valorDerechoEmision) {
		this.valorDerechoEmision = valorDerechoEmision;
	}
}
