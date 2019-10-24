package isi.dds.tp.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "anio_modelo")
public class AnioModelo {
	
	@ManyToOne
	@JoinColumn (name = "id_modelo")
	private Modelo modelo;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_anio_modelo")
	@SequenceGenerator(name="id_anio_modelo", sequenceName = "id_anio_modelo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private Integer anio;
	
	@Column(name="suma_asegurada", nullable = false)
	private Float sumaAsegurada;

	public AnioModelo() { }
	
	public AnioModelo(Modelo modelo, Integer anio, Float suma) {
		modelo.getAnios().add(this);
		this.anio = anio;
		this.sumaAsegurada = suma;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Float getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(Float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return anio.toString();
	}
}

