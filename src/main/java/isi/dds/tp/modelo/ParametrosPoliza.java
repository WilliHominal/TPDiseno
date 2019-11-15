package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parametros_poliza")
public class ParametrosPoliza {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigo_bitacora", foreignKey=@ForeignKey(name = "fk_codigo_bitacora"))
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_parametros_poliza")
	@SequenceGenerator(name="codigo_parametros_poliza", sequenceName = "codigo_parametros_poliza_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "codigo_parametros_poliza")
	private Integer codigoParametrosPoliza;
	
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
	private Float porcentajeUnSiniestros;
	
	@Column(nullable = false, name = "porcentaje_dos_siniestro")
	private Float porcentajeDosSiniestros; 
	
	@Column(nullable = false, name = "porcentaje_mayor_a_dos_siniestro")
	private Float porcentajeMayorADosSiniestros;
	
	@Column(nullable = false, name = "porcentaje_por_hijo_registrado")
	private Float porcentajePorHijoRegistrado;
	
	@Column(nullable = false, name = "descuento_unidad_adicional")
	private Float descuentoUnidadAdicional;
	
	@Column(nullable = false, name = "valor_derecho_emision")
	private Float valorDerechoEmision;
	
	public ParametrosPoliza (){ }
		
	public BitacoraParametrosPoliza getBitacoraParametros() {
		return bitacoraParametros;
	}
	
	public Integer getCodigoParametrosPoliza() {
		return codigoParametrosPoliza;
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
		return porcentajeUnSiniestros;
	}
	
	public Float getPorcentajeDosSiniestros() {
		return porcentajeDosSiniestros;
	}
	
	public Float getPorcentajeMayorADosSiniestros() {
		return porcentajeMayorADosSiniestros;
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
	
	public void setCodigoParametrosPoliza(Integer codigoParametroPoliza) {
		this.codigoParametrosPoliza = codigoParametroPoliza;
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
		this.porcentajeUnSiniestros = porcentajeUnSiniestro;
	}
	
	public void setPorcentajeDosSiniestros(Float porcentajeDosSiniestro) {
		this.porcentajeDosSiniestros = porcentajeDosSiniestro;
	}
	
	public void setPorcentajeMayorADosSiniestros(Float porcentajeMayorADosSiniestro) {
		this.porcentajeMayorADosSiniestros = porcentajeMayorADosSiniestro;
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
