package isi.dds.tp.modelo;

import java.util.Date;

import isi.dds.tp.enums.*;

public class Poliza {
	
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
}
