package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class RiesgoCiudad {
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "idCiudad")
	private Ciudad ciudad;
		
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_riesgo_ciudad")
	@SequenceGenerator(name="id_riesgo_ciudad", sequenceName = "id_riesgo_ciudad_seq", initialValue = 1, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	
	@Column(nullable = false)
	private LocalDate fechaInicioVigencia;
	
	@Column
	private LocalDate fechaFinVigencia;
	
	@Column(nullable = false)
	private Float valorPorcentual;
	
	@Column(nullable = false)
	private Boolean ultimo;

	public RiesgoCiudad() {
		
	}
	
	public RiesgoCiudad(Ciudad ciudad,  Float valorPorcentual) {
		this.ciudad = ciudad;
		this.bitacoraParametros = null;
		this.fechaInicioVigencia = LocalDate.now();
		this.fechaFinVigencia = null;
		this.valorPorcentual = valorPorcentual;
		this.ultimo = true;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Boolean getUltimo() {
		return ultimo;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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
	public void setUltimo(Boolean ultimo) {
		this.ultimo = ultimo;
	}
}
