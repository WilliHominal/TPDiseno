package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.*;
import javax.persistence.*;

@Entity
@Table
public class Poliza {
	private Cliente cliente;
	private List<HijoDeclarado> hijosDeclarado;
	private TipoCobertura tipoCobertura;
	private AnioModelo anioModelo;
	private Ciudad ciudad;
	private ParametroPoliza parametroPoliza;
	private SolicitudPoliza solicitudPoliza;
	
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
	private LocalDate inicioVigencia; 
	private LocalDate fechaEmision;
	private LocalDate finVigencia;
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
	
	/**
	 * @param cliente
	 * @param tipoCobertura
	 * @param anioModelo
	 * @param ciudad
	 * @param parametroPoliza
	 * @param solicitudPoliza
	 * @param numeroPoliza
	 * @param sumaAsegurada
	 * @param estado
	 * @param motor
	 * @param chasis
	 * @param patente
	 * @param kmRealizadosPorAnio
	 * @param guardaGarage
	 * @param tieneAlarma
	 * @param tieneRastreoVehicular
	 * @param tieneTuercasAntirobo
	 * @param numerosSiniestrosUltimoAnios
	 */
	public Poliza(Cliente cliente, TipoCobertura tipoCobertura, AnioModelo anioModelo, Ciudad ciudad, ParametroPoliza parametroPoliza, SolicitudPoliza solicitudPoliza,
			Long numeroPoliza, Float sumaAsegurada, EnumEstadoPoliza estado, String motor, String chasis,
			String patente, String kmRealizadosPorAnio, Boolean guardaGarage, Boolean tieneAlarma,
			Boolean tieneRastreoVehicular, Boolean tieneTuercasAntirobo, EnumSiniestros numerosSiniestrosUltimoAnios) {
		this.cliente = cliente;
		this.hijosDeclarado = new ArrayList<HijoDeclarado>();
		this.tipoCobertura = tipoCobertura;
		this.anioModelo = anioModelo;
		this.ciudad = ciudad;
		this.parametroPoliza = parametroPoliza;
		this.solicitudPoliza = solicitudPoliza;
		this.numeroPoliza = numeroPoliza;
		this.sumaAsegurada = sumaAsegurada;
		this.estado = estado;
		this.motor = motor;
		this.chasis = chasis;
		this.patente = patente;
		this.kmRealizadosPorAnio = kmRealizadosPorAnio;
		this.guardaGarage = guardaGarage;
		this.tieneAlarma = tieneAlarma;
		this.tieneRastreoVehicular = tieneRastreoVehicular;
		this.tieneTuercasAntirobo = tieneTuercasAntirobo;
		this.numerosSiniestrosUltimoAnios = numerosSiniestrosUltimoAnios;
		this.inicioVigencia = null;
		this.fechaEmision = null;
		this.finVigencia = null;
		this.formaPago = null;
		this.esPropuesta = null;
		this.estaEmitida = null;
		this.valorDescuentoPorUnidadAdicional = null;
		this.valorPremio = null;
		this.valorPrima = null;
		this.valorDescuento = null;
		this.valorBonificacionPagoSemestral = null;
		this.valorInteresGenero = null;
		this.valorRiesgoCiudad = null;
		this.valorRiesgoModelo = null;
		this.porcentajeValorAsegurado = null;
	}
	
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
	public List<HijoDeclarado> getHijosDeclarado() {
		return hijosDeclarado;
	}
	public void setHijosDeclarado(List<HijoDeclarado> hijosDeclarado) {
		this.hijosDeclarado = hijosDeclarado;
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
	public LocalDate getInicioVigencia() {
		return inicioVigencia;
	}
	public LocalDate getFechaEmision() {
		return fechaEmision;
	}
	public LocalDate getFinVigencia() {
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
	public void setInicioVigencia(LocalDate inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public void setFinVigencia(LocalDate finVigencia) {
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
	public SolicitudPoliza getSolicitudPoliza() {
		return solicitudPoliza;
	}
	public void setSolicitudPoliza(SolicitudPoliza solicitudPoliza) {
		this.solicitudPoliza = solicitudPoliza;
	} 
}
