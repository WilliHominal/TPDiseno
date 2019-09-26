package isi.dds.tp.modelo;

import java.sql.Date;

public class RiesgoModelo {

	private Modelo modelo;
	private BitacoraParametrosPoliza bitacoraParametros;
	
	private Date fechaInicioVigencia;
	private Date fechaFinVigencia;
	private Float valorPorcentual;
	public Modelo getModelo() {
		return modelo;
	}
	public BitacoraParametrosPoliza getBitacoraParametros() {
		return bitacoraParametros;
	}
	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public Float getValorPorcentual() {
		return valorPorcentual;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public void setBitacoraParametros(BitacoraParametrosPoliza bitacoraParametros) {
		this.bitacoraParametros = bitacoraParametros;
	}
	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	public void setValorPorcentual(Float valorPorcentual) {
		this.valorPorcentual = valorPorcentual;
	}
	
	
}
