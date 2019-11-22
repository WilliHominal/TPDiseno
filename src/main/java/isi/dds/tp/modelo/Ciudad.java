package isi.dds.tp.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
public class Ciudad {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_provincia", foreignKey=@ForeignKey(name = "fk_id_provincia"))
	private Provincia provincia;

	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_ciudad", foreignKey=@ForeignKey(name = "fk_id_ciudad"))
	@IndexColumn(name="idx")
	private List<RiesgoCiudad> riesgos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_ciudad")
	@SequenceGenerator(name="id_ciudad", sequenceName = "id_ciudad_seq", initialValue = 21700, allocationSize = 1)
	@Column(nullable = false, name = "id_ciudad")
	private Integer idCiudad;
	
	@Column(nullable = false)
	private String nombre;
	
	public Ciudad() { }

	public Provincia getProvincia() {
		return provincia;
	}
	
	public List<RiesgoCiudad> getRiesgos() {
		return riesgos;
	}
	
	public Integer getIdCiudad() {
		return idCiudad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public void setRiesgos(List<RiesgoCiudad> riesgos) {
		this.riesgos = riesgos;
	}
	
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
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
		result = prime * result + ((idCiudad == null) ? 0 : idCiudad.hashCode());
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
		Ciudad other = (Ciudad) obj;
		if (idCiudad == null) {
			if (other.idCiudad != null)
				return false;
		} else if (!idCiudad.equals(other.idCiudad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
