package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class RiesgoTipoCobertura {
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tipoCobertura")
	private TipoCobertura tipoCobertura;
	
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_riesgo_tipo_cobertura")
	@SequenceGenerator(name="id_riesgo_tipo_cobertura", sequenceName = "id_riesgo_tipo_cobertura_seq", initialValue = 1, allocationSize = 1)
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

	public RiesgoTipoCobertura() {
		
	}
	
	public RiesgoTipoCobertura(TipoCobertura tipoCobertura,	Float valorPorcentual) {
		this.tipoCobertura = tipoCobertura;
		this.bitacoraParametros = null;
		this.fechaInicioVigencia = LocalDate.now();
		this.fechaFinVigencia = null;
		this.valorPorcentual = valorPorcentual;
		this.ultimo = true;
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
	public Integer getId() {
		return id;
	}
	public Boolean getUltimo() {
		return ultimo;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUltimo(Boolean ultimo) {
		this.ultimo = ultimo;
	}
}
