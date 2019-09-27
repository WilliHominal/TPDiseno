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
