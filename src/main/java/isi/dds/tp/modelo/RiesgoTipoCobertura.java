package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "riesgo_tipo_cobertura")
public class RiesgoTipoCobertura {	
	//optional permita que la relacion pueda ser nual, que seria el caso cuando se crea por primera vez una ciudad
	@ManyToOne (optional = true, fetch = FetchType.LAZY)
	@JoinColumn (name = "codigo_bitacora", foreignKey=@ForeignKey(name = "fk_codigo_bitacora"))
	private BitacoraParametrosPoliza bitacoraParametros;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_riesgo_cobertura")
	@SequenceGenerator(name="id_riesgo_cobertura", sequenceName = "id_riesgo_cobertura_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
		
	@Column(nullable = false, name = "inicio_vigencia")
	private LocalDate fechaInicioVigencia;
	
	@Column(name = "fin_vigencia")
	private LocalDate fechaFinVigencia;
	
	@Column(nullable = false, name = "valor_porcentual")
	private Float valorPorcentual;

	public RiesgoTipoCobertura() { }

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
	
	public void setId(Integer id) {
		this.id = id;
	}
}
