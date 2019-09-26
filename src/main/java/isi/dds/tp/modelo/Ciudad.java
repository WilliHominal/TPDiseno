package isi.dds.tp.modelo;

import java.util.List;

public class Ciudad {
	
	private Provincia provincia;
	private List<RiesgoCiudad> riesgos;
	
	private Integer idProvincia;
	private String nombre;
	
	public Provincia getProvincia() {
		return provincia;
	}
	public List<RiesgoCiudad> getRiesgos() {
		return riesgos;
	}
	public Integer getIdProvincia() {
		return idProvincia;
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
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
