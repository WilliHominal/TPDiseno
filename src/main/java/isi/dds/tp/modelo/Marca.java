package isi.dds.tp.modelo;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Marca {

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_marca")
	@IndexColumn(name="idx")
	private List<Modelo> modelos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_marca")
	@SequenceGenerator(name="id_marca", sequenceName = "id_marca_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_marca")
	private Integer idMarca;
	
	@Column(nullable = false)
	private String nombre;
	
	public Marca() {
		
	}
	
	public Marca(String nombre) {
		this.modelos = new ArrayList<Modelo>();
		this.nombre = nombre;
	}
	
	public List<Modelo> getModelos() {
		return modelos;
	}
	public Integer getIdMarca() {
		return idMarca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
