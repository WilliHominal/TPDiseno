package isi.dds.tp.modelo;

import java.util.List;
import isi.dds.tp.enums.EnumTipoCobertura;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "tipo_cobertura")
public class TipoCobertura {
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_cobertura")
	@IndexColumn(name="idx")
	private List<RiesgoTipoCobertura> riesgo;
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cobertura")
	private EnumTipoCobertura tipoCobertura;

	@Column(nullable = false)
	private String descripcion;
		
	@Column(nullable = false)
	private String nombre;

	public TipoCobertura() { }
	
	//para los comboBox
	public TipoCobertura(String nombre) {
		this.nombre = nombre;
	}

	public List<RiesgoTipoCobertura> getRiesgo() {
		return riesgo;
	}

	public EnumTipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}

	public void setRiesgo(List<RiesgoTipoCobertura> riesgo) {
		this.riesgo = riesgo;
	}

	public void setTipoCobertura(EnumTipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
