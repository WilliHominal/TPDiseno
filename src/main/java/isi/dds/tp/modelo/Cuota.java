package isi.dds.tp.modelo;


import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCuota;
import javax.persistence.*;

@Entity
@Table
public class Cuota {
	
	private Poliza poliza;
	private DatosPago datosPago;
	
	private Integer numeroCuota; 
	private Float monto;
	private LocalDate ultimoDiaPago;
	private EnumEstadoCuota estado; 
	private Boolean fuePagada;

	public Cuota() {
		
	}
	
	public Cuota(Poliza poliza, DatosPago datosPago, Integer numeroCuota, Float monto, LocalDate ultimoDiaPago,
			EnumEstadoCuota estado, Boolean fuePagada) {
		this.poliza = poliza;
		this.datosPago = datosPago;
		this.numeroCuota = numeroCuota;
		this.monto = monto;
		this.ultimoDiaPago = ultimoDiaPago;
		this.estado = estado;
		this.fuePagada = fuePagada;
	}
	
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
	public LocalDate getUltimoDiaPago() {
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
	public void setUltimoDiaPago(LocalDate ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}
	public void setEstado(EnumEstadoCuota estado) {
		this.estado = estado;
	}
	public void setFuePagada(Boolean fuePagada) {
		this.fuePagada = fuePagada;
	}
}
