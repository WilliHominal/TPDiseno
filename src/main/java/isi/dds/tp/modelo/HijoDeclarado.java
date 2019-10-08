package isi.dds.tp.modelo;

import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCivil;
import javax.persistence.*;

@Entity
@Table(name = "hijo_declarado")
public class HijoDeclarado {

	@ManyToOne
	@JoinColumn(name = "numero_poliza")
	private Poliza poliza;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_numero_hijo")
	@SequenceGenerator(name="id_numero_hijo", sequenceName = "id_numero_hijo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer numeroHijo;
	
	@Column(nullable = false, name = "fecha_nacimiento")
	private LocalDate fechaNacimiento; 
	
	@Column(nullable = false)
	private String sexo; 
	
	@Column(nullable = false, name = "estado_civil")
	private EnumEstadoCivil estadoCivil;
	
	public HijoDeclarado () {
	
	}
	
	public Integer getNumeroHijo() {
		return numeroHijo;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setNumeroHijo(Integer numeroHijo) {
		this.numeroHijo = numeroHijo;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Poliza getPoliza() {
		return poliza;
	}
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
}
