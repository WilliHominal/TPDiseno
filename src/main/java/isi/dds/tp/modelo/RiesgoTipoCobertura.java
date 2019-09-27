package isi.dds.tp.modelo;

import java.time.LocalDate;

public class RiesgoTipoCobertura {
	private TipoCobertura tipoCobertura;
	private BitacoraParametrosPoliza bitacoraParametros;
	
	private LocalDate fechaInicioVigencia;
	private LocalDate fechaFinVigencia;
	private Float valorPorcentual;
	
	/**
	 * @param tipoCobertura
	 * @param valorPorcentual
	 */
	public RiesgoTipoCobertura(TipoCobertura tipoCobertura,	Float valorPorcentual) {
		this.tipoCobertura = tipoCobertura;
		this.bitacoraParametros = null;
		this.fechaInicioVigencia = LocalDate.now();
		this.fechaFinVigencia = null;
		this.valorPorcentual = valorPorcentual;
	}
	
	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
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
	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
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
