package isi.dds.tp.modelo;

import java.util.List;

public class Pais {
	
	private List<Provincia> provincias;
	
	private Integer idPais;
	private String nombre;
	
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
