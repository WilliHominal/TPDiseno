package isi.dds.tp.modelo;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Modelo {

	@ManyToOne
	@JoinColumn(name="idMarca")
	private Marca marca;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idModelo")
	@IndexColumn(name="idx")
	private List<AnioModelo> anios;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idModelo")
	@IndexColumn(name="idx")
	private List<RiesgoModelo> riesgos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_modelo")
	@SequenceGenerator(name="id_modelo", sequenceName = "id_modelo_seq", initialValue = 1, allocationSize = 1)
	@Column(nullable = false)
	private Integer idModelo;
	
	@Column(nullable = false)
	private String nombre;
	
	public Modelo() {
		
	}
	
	public Modelo(Marca marca, Integer idModelo, String nombre, Float riesgo) {
		this.marca = marca;
		marca.getModelos().add(this);
		this.anios = new ArrayList<AnioModelo>();
		this.riesgos = new ArrayList<RiesgoModelo>();
		this.riesgos.add(new RiesgoModelo(this, riesgo));
		this.idModelo = idModelo;
		this.nombre = nombre;		
	}
	
	public Marca getMarca() {
		return marca;
	}
	public List<AnioModelo> getAnios() {
		return anios;
	}
	public List<RiesgoModelo> getRiesgos() {
		return riesgos;
	}
	public Integer getIdModelo() {
		return idModelo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public void setAnios(List<AnioModelo> anios) {
		this.anios = anios;
	}
	public void setRiesgos(List<RiesgoModelo> riesgos) {
		this.riesgos = riesgos;
	}
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}	
