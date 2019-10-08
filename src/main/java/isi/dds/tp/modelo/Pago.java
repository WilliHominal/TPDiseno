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
	private List<Cuota> cuotas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numero_recibo")
	@SequenceGenerator(name="numero_recibo", sequenceName = "numero_recibo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "numero_recibo")
	private Integer numeroRecibo; 
	
	@Column(nullable = false, name = "importe_parcial")
	private Float importe;
	
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
	public List<Cuota> getCuotas() {
		return cuotas;
	}
	public Integer getNumeroRecibo() {
		return numeroRecibo;
	}
	public Float getImporte() {
		return importe;
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
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	} 
	public void setNumeroRecibo(Integer numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
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
