package isi.dds.tp.modelo;

import java.sql.Date;

public class RiesgoTipoCobertura {
	
	private TipoCobertura tipoCobertura;
	private BitacoraParametrosPoliza bitacoraParametros;
	
	private Date fechaInicioVigencia;
	private Date fechaFinVigencia;
	private Float valorPorcentual;
	
	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
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
	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
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
