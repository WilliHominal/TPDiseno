package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "riesgo_ciudad")
public class RiesgoCiudad {
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_ciudad")
	private Ciudad ciudad;
		
	//optional permita que la relacion pueda ser nual, que seria el caso cuando se crea por primera vez una ciudad
	@ManyToOne (optional = true, fetch = FetchType.LAZY)
	@JoinColumn (name = "codigo_bitacora")
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_riesgo_ciudad")
	@SequenceGenerator(name="id_riesgo_ciudad", sequenceName = "id_riesgo_ciudad_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false, name = "inicio_vigencia")
	private LocalDate fechaInicioVigencia;
	
	@Column(name = "fein_vigencia")
	private LocalDate fechaFinVigencia;
	
	@Column(nullable = false, name = "valor_porcentual")
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
	
	public RiesgoCiudad(BitacoraParametrosPoliza b,Ciudad ciudad,  Float valorPorcentual) {
		this.ciudad = ciudad;
		this.bitacoraParametros = b;
		this.fechaInicioVigencia = LocalDate.now();
		this.fechaFinVigencia = null;
		this.valorPorcentual = valorPorcentual;
		this.ultimo = true;
		b.getRiesgosCiudad().add(this);
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
