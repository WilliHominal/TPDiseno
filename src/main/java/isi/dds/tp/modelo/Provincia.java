package isi.dds.tp.modelo;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Provincia {
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_pais")
	private Pais pais;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_provincia")
	@IndexColumn(name="idx")
	private List<Ciudad> ciudades;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_provincia")
	@SequenceGenerator(name="id_provincia", sequenceName = "id_provincia_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_provincia")
	private Integer idProvincia;
	
	@Column(nullable = false)
	private String nombre;
	
	
	public Provincia() {
		
	}
	
	public Provincia(Pais pais, String nombre) {
		this.pais = pais;
		this.ciudades = new ArrayList<Ciudad>();
		this.nombre = nombre;
		pais.getProvincias().add(this);
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

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
