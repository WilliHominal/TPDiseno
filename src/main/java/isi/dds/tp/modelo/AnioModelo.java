package isi.dds.tp.modelo;

import javax.persistence.*;

@Entity
@Table(name = "anio_modelo")
public class AnioModelo {
	
	@ManyToOne
	@JoinColumn (name = "id_modelo")
	private Modelo modelo;
	
	@Id
	@Column(nullable = false)
	private Integer anio;

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
}
