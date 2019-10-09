package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")


@Entity
@Table
public class Ciudad {
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_provincia")
	private Provincia provincia;
	

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_ciudad")
	@IndexColumn(name="idx")
	private List<RiesgoCiudad> riesgos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_ciudad")
	@SequenceGenerator(name="id_ciudad", sequenceName = "id_ciudad_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_ciudad")
	private Integer idCiudad;
	
	@Column(nullable = false)
	private String nombre;
	
	public Ciudad() {
		
	}
	
	public Ciudad(Provincia pr, String nombre, float f) {
		this.provincia = pr;
		this.nombre = nombre;
		this.riesgos = new ArrayList<RiesgoCiudad>();
		this.riesgos.add(new RiesgoCiudad(this, f));
		pr.getCiudades().add(this);
	}

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
}
