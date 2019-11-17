package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Pago {
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
	private LocalTime hora;
	
	//TODO pago cambiar
	private Float premio; 
	
	private String operador;
	
	public Pago() { }
	
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
	
	public LocalTime getHora() {
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
	
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	public void setPremio(Float premio) {
		this.premio = premio;
	}
	
	public void setOperador(String operador) {
		this.operador = operador;
	}
}
