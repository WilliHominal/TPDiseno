package isi.dds.tp.modelo;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Provincia {
	
	@ManyToOne
	@JoinColumn(name="idPais")
	private Pais pais;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idProvincia")
	@IndexColumn(name="idx")
	private List<Ciudad> ciudades;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_provincia")
	@SequenceGenerator(name="id_provincia", sequenceName = "id_provincia_seq", initialValue = 1, allocationSize = 1)
	@Column(nullable = false)
	private Integer idProvincia;
	
	@Column(nullable = false)
	private String nombre;
	
	
	public Provincia() {
		
	}
	
	public Provincia(Pais pais, Integer idProvincia, String nombre) {
		this.pais = pais;
		pais.getProvincias().add(this);
		this.ciudades = new ArrayList<Ciudad>();
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}
	
	public Pais getPais() {
		return pais;
	}
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	public Integer getIdProvincia() {
		return idProvincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
