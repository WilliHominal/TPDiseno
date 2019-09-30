package isi.dds.tp.modelo;

import java.time.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Pago {
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="numero_recibo")
	@IndexColumn(name="idx")
	private List<DatosPago> datosPago;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numero_recibo")
	@SequenceGenerator(name="numero_recibo", sequenceName = "numero_recibo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "numero_recibo")
	private Integer numeroRecibo; 
	
	@Column(nullable = false, name = "importe_parcial")
	private Float importeParcial;
	
	@Column(nullable = false, name = "importe_total")
	private Float importeTotal;
	
	@Column(nullable = false)
	private Float vuelto;
	
	@Column(nullable = false, name = "fecha_pago")
	private LocalDate fechaPago;
	
	@Column(nullable = false)
	private OffsetDateTime hora;
	
	@Column(nullable = false)
	private Float premio; 
	
	@Column(nullable = false)
	private String operador;
	
	public Pago() {
		
	}
	
	public Pago(Integer numeroRecibo, Float importeParcial, Float importeTotal, Float vuelto, Float premio, String operador) {
		this.datosPago = new ArrayList<DatosPago>();
		this.numeroRecibo = numeroRecibo;
		this.importeParcial = importeParcial;
		this.importeTotal = importeTotal;
		this.vuelto = vuelto;
		this.fechaPago = LocalDate.now();
		this.hora = OffsetDateTime.now();
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
