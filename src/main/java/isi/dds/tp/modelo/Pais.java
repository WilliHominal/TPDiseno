package isi.dds.tp.modelo;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;


@SuppressWarnings({ "serial", "deprecation" })
@Entity
@Table(name = "Pais")
public class Pais implements Serializable{
	

	/*@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idPais")
	@IndexColumn(name="idx")
	private List<Provincia> provincias;*/

	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pais")
	@SequenceGenerator(name="id_pais", sequenceName = "id_pais_seq", initialValue = 300, allocationSize = 2)*/
	@Column(name = "id",nullable = false)
	private Integer idPais;
	
	@Column(nullable = false)
	private String nombre;
	
	public Pais(){
		
	}
	
	public Pais(String nombre) {
		//this.provincias = new ArrayList<Provincia>();
		this.nombre = nombre;
	}
	
	/*public List<Provincia> getProvincias() {
		return provincias;
	}*/
	public Integer getIdPais() {
		return idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setProvincias(List<Provincia> provincias) {
		//this.provincias = provincias;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
