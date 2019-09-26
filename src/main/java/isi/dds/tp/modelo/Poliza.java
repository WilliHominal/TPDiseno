package isi.dds.tp.modelo;

import java.sql.Date;
import isi.dds.tp.enums.*;

public class Poliza {
	
	private Cliente cliente;
	private TipoCobertura tipoCobertura;
	private AnioModelo anioModelo;
	private Ciudad ciudad;
	private ParametroPoliza parametroPoliza;
	
	private Long numeroPoliza;
	private Float sumaAsegurada;
	private EnumEstadoPoliza estado;
	private String motor;
	private String chasis;
	private String patente;
	private String kmRealizadosPorAnio;
	private Boolean guardaGarage;
	private Boolean tieneAlarma;
	private Boolean tieneRastreoVehicular;
	private Boolean tieneTuercasAntirobo;
	private EnumSiniestros numerosSiniestrosUltimoAnios; 
	private Date inicioVigencia; 
	private Date fechaEmision;
	private Date finVigencia;
	private EnumFormaPago formaPago; 
	private Boolean esPropuesta;
	private Boolean estaEmitida; 
	private Float valorDescuentoPorUnidadAdicional;
	private Float valorPremio;
	private Float valorPrima;
	private Float valorDescuento; 
	private Float valorBonificacionPagoSemestral; 
	private Float valorInteresGenero; 
	private Float valorRiesgoCiudad; 
	private Float valorRiesgoModelo;
	private Float porcentajeValorAsegurado;
	
	public Cliente getCliente() {
		return cliente;
	}
	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}
	public AnioModelo getAnioModelo() {
		return anioModelo;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public ParametroPoliza getParametroPoliza() {
		return parametroPoliza;
	}
	public Long getNumeroPoliza() {
		return numeroPoliza;
	}
	public Float getSumaAsegurada() {
		return sumaAsegurada;
	}
	public EnumEstadoPoliza getEstado() {
		return estado;
	}
	public String getMotor() {
		return motor;
	}
	public String getChasis() {
		return chasis;
	}
	public String getPatente() {
		return patente;
	}
	public String getKmRealizadosPorAnio() {
		return kmRealizadosPorAnio;
	}
	public Boolean getGuardaGarage() {
		return guardaGarage;
	}
	public Boolean getTieneAlarma() {
		return tieneAlarma;
	}
	public Boolean getTieneRastreoVehicular() {
		return tieneRastreoVehicular;
	}
	public Boolean getTieneTuercasAntirobo() {
		return tieneTuercasAntirobo;
	}
	public EnumSiniestros getNumerosSiniestrosUltimoAnios() {
		return numerosSiniestrosUltimoAnios;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public Date getFinVigencia() {
		return finVigencia;
	}
	public EnumFormaPago getFormaPago() {
		return formaPago;
	}
	public Boolean getEsPropuesta() {
		return esPropuesta;
	}
	public Boolean getEstaEmitida() {
		return estaEmitida;
	}
	public Float getValorDescuentoPorUnidadAdicional() {
		return valorDescuentoPorUnidadAdicional;
	}
	public Float getValorPremio() {
		return valorPremio;
	}
	public Float getValorPrima() {
		return valorPrima;
	}
	public Float getValorDescuento() {
		return valorDescuento;
	}
	public Float getValorBonificacionPagoSemestral() {
		return valorBonificacionPagoSemestral;
	}
	public Float getValorInteresGenero() {
		return valorInteresGenero;
	}
	public Float getValorRiesgoCiudad() {
		return valorRiesgoCiudad;
	}
	public Float getValorRiesgoModelo() {
		return valorRiesgoModelo;
	}
	public Float getPorcentajeValorAsegurado() {
		return porcentajeValorAsegurado;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}
	public void setAnioModelo(AnioModelo anioModelo) {
		this.anioModelo = anioModelo;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public void setParametroPoliza(ParametroPoliza parametroPoliza) {
		this.parametroPoliza = parametroPoliza;
	}
	public void setNumeroPoliza(Long numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	public void setSumaAsegurada(Float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public void setEstado(EnumEstadoPoliza estado) {
		this.estado = estado;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public void setKmRealizadosPorAnio(String kmRealizadosPorAnio) {
		this.kmRealizadosPorAnio = kmRealizadosPorAnio;
	}
	public void setGuardaGarage(Boolean guardaGarage) {
		this.guardaGarage = guardaGarage;
	}
	public void setTieneAlarma(Boolean tieneAlarma) {
		this.tieneAlarma = tieneAlarma;
	}
	public void setTieneRastreoVehicular(Boolean tieneRastreoVehicular) {
		this.tieneRastreoVehicular = tieneRastreoVehicular;
	}
	public void setTieneTuercasAntirobo(Boolean tieneTuercasAntirobo) {
		this.tieneTuercasAntirobo = tieneTuercasAntirobo;
	}
	public void setNumerosSiniestrosUltimoAnios(EnumSiniestros numerosSiniestrosUltimoAnios) {
		this.numerosSiniestrosUltimoAnios = numerosSiniestrosUltimoAnios;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	public void setFormaPago(EnumFormaPago formaPago) {
		this.formaPago = formaPago;
	}
	public void setEsPropuesta(Boolean esPropuesta) {
		this.esPropuesta = esPropuesta;
	}
	public void setEstaEmitida(Boolean estaEmitida) {
		this.estaEmitida = estaEmitida;
	}
	public void setValorDescuentoPorUnidadAdicional(Float valorDescuentoPorUnidadAdicional) {
		this.valorDescuentoPorUnidadAdicional = valorDescuentoPorUnidadAdicional;
	}
	public void setValorPremio(Float valorPremio) {
		this.valorPremio = valorPremio;
	}
	public void setValorPrima(Float valorPrima) {
		this.valorPrima = valorPrima;
	}
	public void setValorDescuento(Float valorDescuento) {
		this.valorDescuento = valorDescuento;
	}
	public void setValorBonificacionPagoSemestral(Float valorBonificacionPagoSemestral) {
		this.valorBonificacionPagoSemestral = valorBonificacionPagoSemestral;
	}
	public void setValorInteresGenero(Float valorInteresGenero) {
		this.valorInteresGenero = valorInteresGenero;
	}
	public void setValorRiesgoCiudad(Float valorRiesgoCiudad) {
		this.valorRiesgoCiudad = valorRiesgoCiudad;
	}
	public void setValorRiesgoModelo(Float valorRiesgoModelo) {
		this.valorRiesgoModelo = valorRiesgoModelo;
	}
	public void setPorcentajeValorAsegurado(Float porcentajeValorAsegurado) {
		this.porcentajeValorAsegurado = porcentajeValorAsegurado;
	} 
}
