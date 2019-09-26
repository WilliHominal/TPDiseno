package isi.dds.tp.modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Pago {
	
	private List<DatosPago> datosPago;
	
	private Integer numeroRecibo; 
	private Float importeParcial; 
	private Float importeTotal;
	private Float vuelto;
	private Date fechaPago; 
	private Time hora;
	private Float premio; 
	private String operador;
	
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
	public Date getFechaPago() {
		return fechaPago;
	}
	public Time getHora() {
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
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public void setPremio(Float premio) {
		this.premio = premio;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	} 
	
}
