package isi.dds.tp.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Modelo {

	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_modelo")
	@IndexColumn(name="idx")
	private List<AnioModelo> anios;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_modelo")
	@IndexColumn(name="idx")
	private List<RiesgoModelo> riesgos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_modelo")
	@SequenceGenerator(name="id_modelo", sequenceName = "id_modelo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_modelo")
	private Integer idModelo;
	
	@Column(nullable = false)
	private String nombre;
	
	public Modelo() { }
	
	//es para los combobox
	public Modelo(String nombre) {
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
	
	@Override
	public String toString() {
		return nombre;
	}

	public int compareTo(Modelo m2) {
		if (this.nombre.compareTo(m2.getNombre()) == 0) {
			//si son iguales
			   return 0;
		}
		else {
			if (this.nombre.compareTo(m2.getNombre()) < 0) {
				//si el primero es más grande que el segundo
			  return -1;
			}
			else {
				if (this.nombre.compareTo(m2.getNombre()) > 0) {
					//si el primero es más chico que el segundo
				  return 1;
				}
			}
		}
		return 0;
	}

}	
