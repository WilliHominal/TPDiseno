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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Pais {
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_pais")
	@IndexColumn(name="idx")
	private List<Provincia> provincias;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pais")
	@SequenceGenerator(name ="id_pais", sequenceName = "id_pais_seq", initialValue = 100, allocationSize = 1)
	@Column(name = "id_pais", nullable = false)
	private Integer idPais;
	
	@Column(nullable = false)
	private String nombre;
	
	public Pais(){ }
	
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

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPais == null) ? 0 : idPais.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (idPais == null) {
			if (other.idPais != null)
				return false;
		} else if (!idPais.equals(other.idPais))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
