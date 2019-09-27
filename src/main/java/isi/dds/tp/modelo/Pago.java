package isi.dds.tp.modelo;

import java.time.*;
import java.util.*;

public class Pago {
	private List<DatosPago> datosPago;
	
	private Integer numeroRecibo; 
	private Float importeParcial; 
	private Float importeTotal;
	private Float vuelto;
	private LocalDate fechaPago; 
	private OffsetDateTime hora;
	private Float premio; 
	private String operador;
	
	/**
	 * @param numeroRecibo
	 * @param importeParcial
	 * @param importeTotal
	 * @param vuelto
	 * @param fechaPago
	 * @param hora
	 * @param premio
	 * @param operador
	 */
	public Pago(Integer numeroRecibo, Float importeParcial, Float importeTotal, Float vuelto,
			LocalDate fechaPago, OffsetDateTime hora, Float premio, String operador) {
		this.datosPago = new ArrayList<DatosPago>();;
		this.numeroRecibo = numeroRecibo;
		this.importeParcial = importeParcial;
		this.importeTotal = importeTotal;
		this.vuelto = vuelto;
		this.fechaPago = fechaPago;
		this.hora = hora;
		this.premio = premio;
		this.operador = operador;
	}
	
	public List<DatosPago> getDatosPago() {
		return datosPago;
	}
	public Integer getNumeroRecibo() {
		return numeroRecibo;
	}
	public Float getImporteParcial() {
		return importeParcial;
	}
	public Float getImporteTotal() {
		return importeTotal;
	}
	public Float getVuelto() {
		return vuelto;
	}
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public OffsetDateTime getHora() {
		return hora;
	}
	public Float getPremio() {
		return premio;
	}
	public String getOperador() {
		return operador;
	}
	public void setDatosPago(List<DatosPago> datosPago) {
		this.datosPago = datosPago;
	}
	public void setNumeroRecibo(Integer numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public void setImporteParcial(Float importeParcial) {
		this.importeParcial = importeParcial;
	}
	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public void setVuelto(Float vuelto) {
		this.vuelto = vuelto;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	public void setHora(OffsetDateTime hora) {
		this.hora = hora;
	}
	public void setPremio(Float premio) {
		this.premio = premio;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	} 
	
}
