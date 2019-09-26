package isi.dds.tp.modelo;

import java.sql.Date;
import isi.dds.tp.enums.EnumEstadoCuota;

public class Cuota {
	
	private Poliza poliza;
	private DatosPago datosPago;
	
	private Integer numeroCuota; 
	private Float monto;
	private Date ultimoDiaPago;
	private EnumEstadoCuota estado; 
	private Boolean fuePagada;
	
	public Poliza getPoliza() {
		return poliza;
	}
	public DatosPago getDatosPago() {
		return datosPago;
	}
	public Integer getNumeroCuota() {
		return numeroCuota;
	}
	public Float getMonto() {
		return monto;
	}
	public Date getUltimoDiaPago() {
		return ultimoDiaPago;
	}
	public EnumEstadoCuota getEstado() {
		return estado;
	}
	public Boolean getFuePagada() {
		return fuePagada;
	}
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
	public void setDatosPago(DatosPago datosPago) {
		this.datosPago = datosPago;
	}
	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public void setUltimoDiaPago(Date ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}
	public void setEstado(EnumEstadoCuota estado) {
		this.estado = estado;
	}
	public void setFuePagada(Boolean fuePagada) {
		this.fuePagada = fuePagada;
	}
}
