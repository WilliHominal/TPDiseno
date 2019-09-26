package isi.dds.tp.modelo;

import java.sql.Date;
import java.util.Map;

public class ParametroPoliza {

	private BitacoraParametrosPoliza bitacoraParametros;
	
	private Integer codigoParametroPoliza;
	private Date fechaInicioVigencia; 
	private Date fechaFinVigencia;
	private Map <String, Float> porcentajeTuercasAntirobo; 
	private Map <String, Float> porcentajeGuardaEnGarage;
	private Map <String, Float> porcentajeAlarma;
	private Map <String, Float> porcentajeRastreoVehicular; 
	private Map <String, Float> porcentajeAjusteKm;
	private Map <String, Float> porcentajeNingunSiniestro; 
	private Map <String, Float> porcentajeUnSiniestro;
	private Map <String, Float> porcentajeDosSiniestro; 
	private Map <String, Float> porcentajeMayorADosSiniestro; 
	private Map <String, Float> porcentajePorHijoRegistrado;
	private Map <String, Float> descuentoUnidadAdicional;
	private Map <String, Float> valorDerechoEmision;
	public BitacoraParametrosPoliza getBitacoraParametros() {
		return bitacoraParametros;
	}
	public Integer getCodigoParametroPoliza() {
		return codigoParametroPoliza;
	}
	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public void setBitacoraParametros(BitacoraParametrosPoliza bitacoraParametros) {
		this.bitacoraParametros = bitacoraParametros;
	}
	public void setCodigoParametroPoliza(Integer codigoParametroPoliza) {
		this.codigoParametroPoliza = codigoParametroPoliza;
	}
	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	
	/*mirar get y set para los maps*/
}
