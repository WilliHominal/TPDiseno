package isi.dds.tp.modelo;

import java.util.List;

public class Provincia {

	private Pais pais;
	private List<Ciudad> ciudades;
	
	private Integer idProvincia;
	private String nombre;
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