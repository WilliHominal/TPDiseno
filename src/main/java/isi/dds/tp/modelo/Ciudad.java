package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Ciudad {
	
	private Provincia provincia;
	private List<RiesgoCiudad> riesgos;
	
	private Integer idCiudad;
	private String nombre;
	
	public Ciudad(Provincia provincia, Integer idCiudad, String nombre) {
		this.provincia = provincia;
		/*AÑADIR CIUDAd A PROVINCIA FIJARSE METODO*/
		this.riesgos = new ArrayList<RiesgoCiudad>();
		this.idCiudad = idCiudad;
		this.nombre = nombre;
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
}
