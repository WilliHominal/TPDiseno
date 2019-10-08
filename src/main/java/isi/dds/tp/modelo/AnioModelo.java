package isi.dds.tp.modelo;

import javax.persistence.*;

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
	
	@Column(nullable = false)
	private Integer sumaAsegurada;

	public AnioModelo() {
		
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

	public Integer getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(Integer sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
