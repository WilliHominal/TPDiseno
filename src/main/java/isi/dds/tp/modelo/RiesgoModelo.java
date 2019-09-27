package isi.dds.tp.modelo;

import java.time.LocalDate;

public class RiesgoModelo {

	private Modelo modelo;
	private BitacoraParametrosPoliza bitacoraParametros;
	
	private LocalDate fechaInicioVigencia;
	private LocalDate fechaFinVigencia;
	private Float valorPorcentual;
	public Modelo getModelo() {
		return modelo;
	}
	public BitacoraParametrosPoliza getBitacoraParametros() {
		return bitacoraParametros;
	}
	public LocalDate getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public LocalDate getFechaFinVigencia() {
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
	public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public void setFechaFinVigencia(LocalDate fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	public void setValorPorcentual(Float valorPorcentual) {
		this.valorPorcentual = valorPorcentual;
	}
	
}
