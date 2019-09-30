package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Pais {
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idPais")
	@IndexColumn(name="idx")
	private List<Provincia> provincias;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pais")
	@SequenceGenerator(name="id_pais", sequenceName = "id_pais_seq", initialValue = 1, allocationSize = 1)
	@Column(nullable = false)
	private Integer idPais;
	
	@Column(nullable = false)
	private String nombre;
	
	public Pais(){
		
	}
	
	public Pais(Integer idPais, String nombre) {
		this.provincias = new ArrayList<Provincia>();
		this.idPais = idPais;
		this.nombre = nombre;
	}
	
	public List<Provincia> getProvincias() {
		return provincias;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
