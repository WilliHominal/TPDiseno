package isi.dds.tp.modelo;

import java.time.LocalDate;

public class ParametroPoliza {
	private BitacoraParametrosPoliza bitacoraParametros;
	
	private Integer codigoParametroPoliza;
	private LocalDate fechaInicioVigencia; 
	private LocalDate fechaFinVigencia;
	private Float porcentajeTuercasAntirobo; 
	private Float porcentajeGuardaEnGarage;
	private Float porcentajeAlarma;
	private Float porcentajeRastreoVehicular; 
	private Float porcentajeAjusteKm;
	private Float porcentajeNingunSiniestro; 
	private Float porcentajeUnSiniestro;
	private Float porcentajeDosSiniestro; 
	private Float porcentajeMayorADosSiniestro; 
	private Float porcentajePorHijoRegistrado;
	private Float descuentoUnidadAdicional;
	private Float valorDerechoEmision;
	
	/**
	 * @param codigoParametroPoliza
	 * @param porcentajeTuercasAntirobo
	 * @param porcentajeGuardaEnGarage
	 * @param porcentajeAlarma
	 * @param porcentajeRastreoVehicular
	 * @param porcentajeAjusteKm
	 * @param porcentajeNingunSiniestro
	 * @param porcentajeUnSiniestro
	 * @param porcentajeDosSiniestro
	 * @param porcentajeMayorADosSiniestro
	 * @param porcentajePorHijoRegistrado
	 * @param descuentoUnidadAdicional
	 * @param valorDerechoEmision
	 */
	public ParametroPoliza(Integer codigoParametroPoliza, Float porcentajeTuercasAntirobo, 
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
	

	public ParametroPoliza(ParametroPoliza p) {
		//CORREGIR
		this.codigoParametroPoliza = codigoParametroPoliza;
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
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
